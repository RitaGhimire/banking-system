import java.util.Scanner;

public class Adminworkchoice {
    private static Scanner scanner = new Scanner(System.in);
    private static adminAccess adminAccess = new adminAccess();
    UserDetails userWork = new UserDetails();
    String email;
    public void setEmail(String email){
        this.email = email;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    void manageAdmin() { // this class includes option such as adding deleting updating and listing the books
        int choice;
        do {
            System.out.println("What would you like to do today?Enter \n1.Add Book \n2.Delete Book \n3.Update Book \n4.List Book \n5.Promote User \n6.Delete User \n7.Burrow Book \n8.Return Book \n9.Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminAccess.addBook();
                    break;
                case 2:
                    adminAccess.deleteBook();
                    break;
                case 3:
                    adminAccess.updateBook();
                    break;
                case 4:
                    adminAccess.listBook();
                    break;
                case 5:
                    adminAccess.promoteDemoteUser();
                    break;
                case 6:
                    adminAccess.deleteUser();
                    break;
                case 7:
                    userWork.burrowBook(email);
                    break;
                case 8:
                    userWork.returnBook(email);
                    break;
                case 9:
                    System.out.println("Would you like to do any of these again?");
                    Admindetails admindetails = new Admindetails();//The admin details has all the information regarding signing in user and signing up or exiting the user
                    admindetails.InformationFile();
                    admindetails.Login();
                    break;

            }
        } while (choice != 5);//Loop continues until the admin chooses to exit
    }
}


