package datamodel;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Database is a class that specifies the interface to the 
 * movie database. Uses JDBC and the MySQL Connector/J driver.
 */
public class Database {
    /** 
     * The database connection.
     */
    private Connection conn;
        
    /**
     * Create the database interface object. Connection to the database
     * is performed later.
     */
    public Database() {
        conn = null;
    }
       
    /* --- TODO: Change this method to fit your choice of DBMS --- */
    /** 
     * Open a connection to the database, using the specified user name
     * and password.
     *
     * @param userName The user name.
     * @param password The user's password.
     * @return true if the connection succeeded, false if the supplied
     * user name and password were not recognized. Returns false also
     * if the JDBC driver isn't found.
     */
    public boolean openConnection(String userName, String password) {
        try {
        	// Connection strings for included DBMS clients:
        	// [MySQL]       jdbc:mysql://[host]/[database]
        	// [PostgreSQL]  jdbc:postgresql://[host]/[database]
        	// [SQLite]      jdbc:sqlite://[filepath]
        	
        	// Use "jdbc:mysql://puccini.cs.lth.se/" + userName if you using our shared server
        	// If outside, this statement will hang until timeout.
            conn = DriverManager.getConnection 
                ("jdbc:sqlite://C:\\Users\\Christoffer\\SQLite\\Lab2.db", userName, password);
        }
        catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }

        return true;
    }
        
    /**
     * Close the connection to the database.
     */
    public void closeConnection() {
        try {
            if (conn != null){
                conn.close();
            }
        }
        catch (SQLException e) {
        	e.printStackTrace();
        }
        conn = null;
        
        System.err.println("Database connection closed.");
    }
        
    /**
     * Check if the connection to the database has been established
     *
     * @return true if the connection has been established
     */
    public boolean isConnected() {
        return conn != null;
    }
	
  	public Show getShowData(String mTitle, String mDate) {
        String queryString = "SELECT Show.movieplayed, Show.date, Show.theatername, Show.bookedseats, Theater.seats\n" +
                "FROM Show\n" +
                "JOIN Theater\n" +
                "on Show.theatername = Theater.name\n" +
                "WHERE Show.movieplayed = ? and Show.date = ?;";

        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            statement.setString(1, mTitle);
            statement.setString(2, mDate);
            ResultSet set = statement.executeQuery();

            if (set.next()){
                return new Show(mTitle, mDate, set.getString(3), set.getInt(5) - set.getInt(4));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
	}

    public boolean login(String username){
        String queryString = "select * from user where user.username = ?";
        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();
            if (set.next()){
                return  true;
            } else{
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public LinkedList<Reservation> getReservation(String username){
        String queryString = "SELECT Ticket.ticket_id, Ticket.username, Ticket.date, Ticket.movieplayed, Show.theatername\n" +
                "FROM Ticket\n" +
                "JOIN show \n" +
                "on Show.date = Ticket.date and Show.movieplayed = Ticket.movieplayed\n" +
                "WHERE username = ?;";
        LinkedList<Reservation> rs = new LinkedList<>();

        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            statement.setString(1,username);

            ResultSet set = statement.executeQuery();
            while (set.next()){
                rs.add(new Reservation(set.getInt(1), set.getString(4), set.getString(3), set.getString(5)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rs;
    }

    public LinkedList<String> movies(){
        String queryString = "select * from movie";
        LinkedList<String> movies = new LinkedList<>();

        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            ResultSet set = statement.executeQuery();
            while (set.next()){
                movies.add(set.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movies;
    }

    public LinkedList<String> moviesDates(String movie){
        String queryString = "select date from show where show.movieplayed = ?";
        LinkedList<String> dates = new LinkedList<>();

        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            statement.setString(1, movie);

            ResultSet set = statement.executeQuery();
            while (set.next()){
                dates.add(set.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dates;
    }

    public int bookShow(String movie, String date){

        if (doesReservationExist(CurrentUser.instance().getCurrentUserId(), movie,date))
            return -1;

        String queryString = "SELECT Show.bookedseats, Theater.seats, Theater.name\n" +
                "FROM Show\n" +
                "JOIN Theater\n" +
                "on show.theatername = Theater.name\n" +
                "WHERE Show.movieplayed = ? and Show.date = ?;";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(queryString);
            statement.setString(1, movie);
            statement.setString(2, date);
            ResultSet set = statement.executeQuery();
            if (set.next()){
                if (set.getInt(1) < set.getInt(2)){
                    statement = conn.prepareStatement("Update show set bookedseats = ? + 1 where show.movieplayed = ? and show.date = ?");
                    statement.setString(1, set.getString(1));
                    statement.setString(2, movie);
                    statement.setString(3, date);
                    statement.executeUpdate();
                } else{
                    return -1;
                }
            }

            queryString = "INSERT INTO Ticket (username, date, movieplayed)\n" +
                    "VALUES (?, ?, ?);";
            statement = conn.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, CurrentUser.instance().getCurrentUserId());
            statement.setString(2, date);
            statement.setString(3, movie);

            if (statement.executeUpdate() == 1){
                set = statement.getGeneratedKeys();
                set.next();
                return set.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return -1; // om det inte gick
    }

    private boolean doesReservationExist(String username, String movie, String date){
        String queryString = "SELECT Ticket.username, Ticket.movieplayed, Ticket.date\n" +
                "FROM Ticket\n" +
                "WHERE Ticket.username = ? and Ticket.movieplayed = ? and Ticket.date = ?;";

        try (PreparedStatement statement = conn.prepareStatement(queryString)){
            statement.setString(1, username);
            statement.setString(2, movie);
            statement.setString(3, date);

            ResultSet set = statement.executeQuery();
            if (set.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
