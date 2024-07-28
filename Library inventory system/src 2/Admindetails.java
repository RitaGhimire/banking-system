import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Admindetails { // this class has methods for sign-in and sign-up
    private static Scanner scanner = new Scanner(System.in);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void InformationFile(){ // this is the CSV file that has the data of a User
    try {
        new File("path_to/data").mkdirs();
        String filePath = "data2.csv";
        File file = new File(filePath);
        if(!file.exists()){
            String[] data = {"Rita","Ghimire","g2@nku.edu","Nepal12@"};
            FileWriter csvWriter = new FileWriter(filePath, true);

            for (int j = 0; j < data.length; j++) {
                csvWriter.append(data[j]);
                if (j != data.length - 1) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();

        }
    }catch(Exception e){
        System.out.println();
    }
}
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public String[] Login() {// this method asks if user wants to signin signin or exit
    String email = null;
    String userType = null;
    boolean keeprunning = true;
    while (keeprunning) {
        System.out.println("Choose an option: \n1.SignIn \n2.SignUp \n3.Exit");
        int options = scanner.nextInt();
        switch (options) {
            case 1:
                String[] SignIn = signIn();
                email = SignIn[0];
                userType = SignIn[1];
                break;
            case 2:
                email = signUp();
                userType = "1";
                break;
            case 3:
                System.out.println("Thank you for your time");
                System.exit(0);
                break;
            default:
                System.out.println("Sorry,the number you have entered is not valid.");
        }
        if (email != null){
            keeprunning = false;
        }
    }
    return new String[]{email, userType};
}
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static String[] signIn() {//this is the sign-In method where User enters email and password, whoever sign's in first is the admin

    boolean isSignedIn = false;
    String email= null;
    String userType = null;

     while (!isSignedIn) {
            System.out.println("Please enter your Email: ");
            email = scanner.next();
            System.out.println("Please enter your Password: ");
            String password = scanner.next();

            try {
                File file = new File("data2.csv");
                Scanner filescanner = new Scanner(file);
                //Try to input a number for the admin and try to call number//Check number//If 0, its admin//If 1, its user

                while (filescanner.hasNextLine()) {
                    String row = filescanner.nextLine();
                    String[] data = row.split(",");
                    if (email.equals(data[2]) && password.equals(data[3])) {//this checks if the email and password matches with the System or not
                        System.out.println("You have successfully signIn");// if it does user is signed in
                        System.out.println(email);
                        isSignedIn = true;
                        userType = data[4];
                        break;
                        }

                    }
                filescanner.close();
                if (!isSignedIn) {
                    System.out.println("The email or password you have entered are incorrect");
                    System.out.println("Choose an option: \n 1.Try Sign-In again \n 2.Sign Up \n 3.Exit");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            break;
                        case 2:
                            signUp();
                            break;
                        case 3:
                            System.out.println("Thankyou for your time");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("The number you have entered is invalid.Please try again");
                            break;
                    }
                }
            } catch (FileNotFoundException e) { //the try-catch expection is used to catch any error that occurs
                System.out.println("An error occured");//if user sign's in they are assigned 1 as admin
                e.printStackTrace();
                System.exit(1);//if user sign's up and then sign's in they are assigned 1 as normal users
            } catch(Exception e) {
                System.out.println("An error occured:");
                e.printStackTrace();
                System.exit(1);
            }
     }
        return new String[]{email, userType};
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static String signUp(){      // this method sign's up a user
        String email = null;
        System.out.println("Please enter prefered firstname : ");
         String Firstname = scanner.next();
         System.out.println("Please enter prefered lastname:");
         String Lastname = scanner.next();
         System.out.println("Please enter prefered email: ");
        String Email = scanner.next();
        System.out.println("Please enter prefered password: ");
        String password = scanner.next();
        File file= new File("data2.csv");
        boolean emailexists = false;
        try {
            Scanner filescanner = new Scanner(file);

            while (filescanner.hasNextLine()) {
                String row = filescanner.nextLine();
                String[] data = row.split(",");
                if (Email.equals(data[2])) {
                    emailexists = true;
                    break;
                }
            }
            filescanner.close();
            if (!emailexists) {
                FileWriter FileCSV = new FileWriter(file, true);
                FileCSV.append(Firstname);
                FileCSV.append(",");
                FileCSV.append(Lastname);
                FileCSV.append(",");
                FileCSV.append(Email);
                FileCSV.append(",");
                FileCSV.append(password);
                FileCSV.append(",");
                FileCSV.append("1"); //it also assigns as 1 stating it is a regular user and later regular user options are displayed
                FileCSV.append("\n");
                FileCSV.flush();
                FileCSV.close();
                System.out.println("You have successfully signed-up");
            } else {
                System.out.println("This email already exists.You want to Sign-in?");
            }
        }catch(FileNotFoundException e){
                System.out.println("The file was not found");
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("An error occured");
                e.printStackTrace();
            }
        return email;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Just a Try code to check what's been printing
    public static void printFileContents() {
        try {
            File file = new File("data2.csv");
            Scanner filescanner = new Scanner(file);

            while (filescanner.hasNextLine()) {
                String row = filescanner.nextLine();
                System.out.println(row);
            }
            filescanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



