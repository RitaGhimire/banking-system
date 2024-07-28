import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class adminAccess {
    private static List<BookDetails> bookDetailsList = new ArrayList<>(); // esma chai addbook deletebook sabai ko detail cha
    private Scanner scanner = new Scanner(System.in);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addBook() { //This method adds book to the system
        System.out.println("-----------------------------------------");
        System.out.println("Enter the book title");
        String title = scanner.next();
        System.out.println("Enter name of the author");
        String author = scanner.next();
        System.out.println("Enter ISBN for the Book");
        String ISBN = scanner.next();
        System.out.println("Enter the year of the publication");
        int yearOfPublication = scanner.nextInt();

        //missing
        bookDetailsList.add(new BookDetails(title, author, ISBN, yearOfPublication, true, null));
        System.out.println("The book has been added successfully in the System");
        try {
            FileWriter bookList = new FileWriter("BookList.csv", true); // it appends the title, author, ISBN, Integer.toString(yearOfPublication) to add to the Booklist file
            bookList.append(title);
            bookList.append(",");
            bookList.append(author);
            bookList.append(",");
            bookList.append(ISBN);
            bookList.append(",");
            bookList.append(Integer.toString(yearOfPublication));
            bookList.append(",");
            bookList.append("true");
            bookList.append(",");
            bookList.append("null");
            bookList.append("\n");
            bookList.flush();
            bookList.close();

        } catch (IOException e) {
            System.out.println("An error to add book has occured");
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteBook() {//This method deletes book to the system
        System.out.println("-------------------------------");
        System.out.println("Enter ISBN of book you want to delete");
        String ISBN = scanner.next().trim();
        List<BookDetails> books = new ArrayList<>();
        boolean bookFound = false;
        try (Scanner fileScanner = new Scanner(new File("BookList.csv"))) {
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String[] fileData = fileLine.split(",");
                if (fileData.length >= 5 && !fileData[2].trim().equals(ISBN)) {
                    books.add(new BookDetails(fileData[0].trim(), fileData[1].trim(), fileData[2].trim(), Integer.parseInt(fileData[3].trim()), Boolean.parseBoolean(fileData[4].trim()), null));
                } else {
                    bookFound = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found to delete the book");
            e.printStackTrace();
        }
        if (bookFound) {
            try (FileWriter fileWrite = new FileWriter("BookList.csv", false)) {
                for (BookDetails book : books) {
                    fileWrite.write(book.getTitle() + "," + book.getAuthors() + "," + book.getISBN() + "," + book.getYearOfPublication() + "\n");
                }
            } catch (IOException e) {
                System.out.println("An error to delete a book has occured");
                e.printStackTrace();
            }
            System.out.println("Book has been successfully deleted from the system");
        } else {
            System.out.println("There is no book in the system with provided ISBN");
        }
        System.out.println("-------------------------------------");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateBook() { //This method update any of the book details such as title, author, ISBN or the year
        System.out.println("-------------------------------");
        System.out.println("Enter the ISBN of the book you want to update:");
        String ISBN = scanner.next().trim();
        List<BookDetails> books = new ArrayList<>();
        boolean bookFound = false;
        try (Scanner fileScanner = new Scanner(new File("BookList.csv"))) {
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String[] fileData = fileLine.split(",");
                if (fileData.length >= 5) {
                    if (fileData[2].trim().equals(ISBN)) {
                        System.out.println("Enter new title of the book");
                        String newTitle = scanner.next();
                        System.out.println("Enter new author of the book");
                        String newAuthor = scanner.next();
                        System.out.println("Enter new ISBN of the book");
                        String newISBN = scanner.next();
                        System.out.println("Enter new Year of the book");
                        int newYear = scanner.nextInt();
                        books.add(new BookDetails(newTitle, newAuthor, newISBN, newYear, true, null));
                        bookFound = true;
                    } else {
                        books.add(new BookDetails(fileData[0].trim(), fileData[1].trim(), fileData[2].trim(), Integer.parseInt(fileData[3].trim()), Boolean.parseBoolean(fileData[4].trim()), null));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found to update the book");
            e.printStackTrace();
        }
        if (bookFound) {
            try (FileWriter fileWrite = new FileWriter("BookList.csv", false)) {
                for (BookDetails book : books) {
                    fileWrite.write(book.getTitle() + "," + book.getAuthors() + "," + book.getISBN() + "," + book.getYearOfPublication() + "\n");
                }
            } catch (IOException e) {
                System.out.println("An error to update a book has occured");
                e.printStackTrace();
            }
            System.out.println("Book has been successfully updated from the system");
        } else {
            System.out.println("There is no book in the system with provided ISBN");
        }
        System.out.println("-------------------------------------");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void accessBooks() { //This method accesses the book information to  list the books
        String filePath = "BookList.csv";
        File books = new File(filePath);

        try {
            Scanner scanner = new Scanner(books);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] bookData = data.split(",");
                String title = bookData[0];
                String author = bookData[1];
                String ISBN = bookData[2];
                int yearOfPublication = Integer.parseInt(bookData[3]);
                boolean isAvailable = Boolean.parseBoolean(bookData[4]);
                String userBorrow = "none";

                if (!isAvailable){
                    File userBorrowFile = new File("userTrackBooks.csv");
                    Scanner scanner1 = new Scanner(userBorrowFile);
                    while (scanner1.hasNextLine()){
                        String userBorrowData = scanner1.nextLine();
                        String [] userBorrowDatas = userBorrowData.split(",");
                        if (userBorrowDatas[0].equals(ISBN)){
                            userBorrow = userBorrowDatas[1];
                            break;
                        }
                    }
                    scanner1.close();

                }
                bookDetailsList.add(new BookDetails(title, author, ISBN, yearOfPublication, isAvailable, userBorrow));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error to access a book has occured");
            e.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void listBook() { //this methods lists all the book
        accessBooks();
        LocalDate todayDate = LocalDate.now();
        LocalDate dateToBeReturned = todayDate.plusMonths(1);
        for (BookDetails book : bookDetailsList) {
            System.out.println("Title: " + book.getTitle() + "\n" + "Authors: " + book.getAuthors() + "\n" + "ISBN: " + book.getISBN() + "\n" + "Year of Publication: " + book.getYearOfPublication() + "\n" + "Availability: " + book.isAvailable() + "\n" + "User who borrowed: " + book.getBorrowedUser() + "\n" + "Date borrowed: " + todayDate + "\n" + "Date to be Returned: " + dateToBeReturned + "\n" + "------------------------------");
        }
    }
    private String getFirstNameFromFile(String borrowedUser){
        try {
            File file = new File("data2.csv");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(borrowedUser)) {
                    return data[1].trim();
                }
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return "";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void promoteDemoteUser() { //this method promotes or demotes a user.To promote it changes the 4th element of an array from 1 to 0.To demote it reverses from 0 to 1.
        System.out.println("-------------------------------");
        System.out.println("Enter the Firstname of User you want to promote to Admin or Demote to User");
        String username = scanner.nextLine().trim();
        System.out.println("Enter the Email of User you want to promote to Admin or Demote to User");
        String email = scanner.nextLine().trim();

        List<String> user = new ArrayList<>();
        boolean userFound = false;

        try (Scanner fileScanner = new Scanner(new File("data2.csv"))) {
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                user.add(fileLine);
                String[] fileData = fileLine.split(",");
                if (fileData.length > 4 && fileData[0].trim().equals(username) && fileData[3].equals(email)) {
                    userFound = true;
                    System.out.println("Enter (1) to Promote the user (2) to Demote the user");
                    int choice = scanner.nextInt();
                    String role;
                    switch (choice) {
                        case 1:
                            role = "0";
                            break;
                        case 2:
                            role = "1";
                            break;
                        default:
                            System.out.println("Invalid cgoce");
                            return;
                    }
                    fileLine = fileData[0] + "," + fileData[1] + "," + fileData[2] + "," + fileData[3] + "," + role;
                    user.set(user.size() - 1, fileLine);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found to update the book");
            e.printStackTrace();
            return;
        }
        if (!userFound) {
            System.out.println("....");
            return;
        }
        try (FileWriter fileWrite = new FileWriter("data2.csv", false)) {
            for (String users : user) {
                fileWrite.write(users + "\n");
            }
            System.out.println("user role updated");
        } catch (IOException e) {
            System.out.println("An error to update a book has occured");
            e.printStackTrace();
            System.out.println("----------------------------");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteUser(){ //this methods deletes a user.
       System.out.println("Enter the username of the User you want to delete");
       String username = scanner.next().trim();
       System.out.println("Enter the email of the User you want to delete");
       String email = scanner.next().trim();
       try {
           File userDetails = new File("data2.csv");
           List<String> userList = new ArrayList<>();
           Scanner fileScanner = new Scanner(userDetails);
           while (fileScanner.hasNextLine()) {
               String listUser = fileScanner.nextLine();
               String[] userData = listUser.split(",");
               if (!userData[0].trim().equals(username) && !userData[3].equals(email)) {
                   userList.add(listUser);
               }
           }
           fileScanner.close();
           FileWriter writer = new FileWriter(userDetails);
           for (String listUser : userList) {
               writer.write(listUser + System.lineSeparator());
           }
           writer.close();
           System.out.println("User has been deleted from the list");
       }catch(IOException e)   {
           System.out.println("An error occured when trying to delete the User");
           e.printStackTrace();
       }
   }
}








