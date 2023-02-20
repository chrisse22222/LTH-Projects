import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Database {

    private Connection conn;

    public Database(){
        conn = null;
    }

    public boolean openConnection(String userName, String password){
        try{
            // Connection strings for included DBMS clients:
            // [MySQL]       jdbc:mysql://[host]/[database]
            // [PostgreSQL]  jdbc:postgresql://[host]/[database]
            // [SQLite]      jdbc:sqlite://[filepath]

            // Use "jdbc:mysql://puccini.cs.lth.se/" + userName if you using our shared server
            // If outside, this statement will hang until timeout.
            conn = DriverManager.getConnection
                    ("jdbc:sqlite://C:\\Users\\Christoffer\\SQLite\\StoffeTest.db", userName, password);
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

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

    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO User VALUES (?, ?, ?)";
        String salt = Encrypt.getSalt();
        String newPassword = Encrypt.sha256(password, salt);

        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, username);
            statement.setString(2, newPassword);
            statement.setString(3, salt);

            int update = statement.executeUpdate();
            if (update == 1){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT * FROM User WHERE username = ?";

        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();
            if (set.next()){
                String salt = set.getString(3);
                String guessedPassword = Encrypt.sha256(password, salt);
                if (guessedPassword.equals(set.getString(2))){
                    return true;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
