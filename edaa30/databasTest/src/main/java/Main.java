public class Main {

    public static void main(String [] args){
        Database db = new Database();

        if (!db.openConnection("dwdw", "dwdw")){
            System.out.println("User does not exist");
        } else{
            db.addUser("chrisse22222", "Hej");
            /*if (db.loginUser("chrisse22222", "fred101120")){
                System.out.println("SUCCESS");
            } else{
                System.out.println("Error, wrong username or password");
            }*/
        }
    }
}
