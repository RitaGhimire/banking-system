

public class Main { // This is the main class where it calls the class admin details.
    public static void main(String[] args) {
        System.out.println("Welcome to the RP Book Inventory Management System ");
        Admindetails admindetails = new Admindetails();
        admindetails.InformationFile();
        String [] userLoginInfo = admindetails.Login();
        String email = userLoginInfo[0];
        String userType = userLoginInfo[1];

        if (userType.equals("1")){
            userAccess userInstance = new userAccess();
            userInstance.setEmail(email);
            userInstance.userInterface();
        } else if (userType.equals("0")) {
            Adminworkchoice adminworkchoice = new Adminworkchoice();
            adminworkchoice.manageAdmin();

        }

    }
}
