import java.util.Scanner;
public class userAccess {
    Scanner scanner = new Scanner(System.in);
    adminAccess forUser = new adminAccess();
    UserDetails userWork = new UserDetails();
    String email;
    public void setEmail(String email){
        this.email = email;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void userInterface(){
        boolean reLoop = true;
       do {
            System.out.println("What would you like to do today?\n1.List Book\n2.Burrow Book\n3.Return Book \n4.Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    forUser.listBook();
                    break;
                case 2:
                    userWork.burrowBook(email);
                    break;
                case 3:
                    userWork.returnBook(email);
                    break;
                case 4:
                    reLoop = false;
                    System.out.println("Thankyou for your time..... ");
                    break;
                default:
                    System.out.println("Please try a correct number.");
                    break;
            }
        }while(reLoop);
    }
}


