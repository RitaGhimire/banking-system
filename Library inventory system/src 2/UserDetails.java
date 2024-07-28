import java.awt.print.Book;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class UserDetails {
    File BookList = new File("BookList.csv");
    Scanner scanner = new Scanner(System.in);
    String bookLine;

    public void burrowBook(String email) {
        LocalDate todayDate = LocalDate.now();
        LocalDate dateToBeReturned = todayDate.plusMonths(1);
        System.out.println("Enter the ISBN number of the book you want:");
        String ISBN = scanner.nextLine();
        List<String> readLines = new ArrayList<>();
        boolean bookFound = false;

        try {
            Scanner fileScanner = new Scanner(BookList);

            while (fileScanner.hasNextLine()) {
                bookLine = fileScanner.nextLine();
                String[] bookLines = bookLine.split(",");

                if (bookLines[2].equals(ISBN)) {
                    if (bookLines[4].equals("true")) {
                        bookLines[4] = "false";
                        bookLine = String.join(",", bookLines);

                        FileWriter bookBorrow = new FileWriter("userTrackBooks.csv", true);
                        bookBorrow.append(ISBN);
                        bookBorrow.append(",");
                        bookBorrow.append(email);
                        bookBorrow.append("\n");
                        bookBorrow.flush();
                        bookBorrow.close();

                        System.out.println("You have borrowed the book. Don't lose it!");
                        System.out.println("Today's due date is: " +todayDate);
                        System.out.println(" ");
                        System.out.println("Your due date is: " + dateToBeReturned);
                        bookFound = true;
                    }
                }
                readLines.add(bookLine);
            }
            fileScanner.close();

            // Now that we've finished reading from the file, let's write to it
            FileWriter writer = new FileWriter(BookList, false); // Use FileWriter instead of PrintWriter
            for (String readLine : readLines) {
                writer.write(readLine + "\n");
            }
            writer.close();
            if(!bookFound){
                System.out.println("The book you want to borrow is not accessible in the System");
            }

        } catch (FileNotFoundException e) {
            System.out.println("There is error in file  while borrowing the book");
        } catch (IOException e) {
            System.out.println("There is error in input while borrowing the book");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void returnBook(String email) { // This h
        List<String> readLines = new ArrayList<>();
        List<String> userTrackBookLines = new ArrayList<>();
        System.out.println("Enter the ISBN number: ");
        String ISBN = scanner.nextLine();
        boolean isBookReturned = false;
        boolean bookFound = false;

        try {
            Scanner fileScanner = new Scanner(BookList);

            while (fileScanner.hasNextLine()) {
                String bookLine = fileScanner.nextLine();
                String[] bookLines = bookLine.split(",");
                if (bookLines[2].equals(ISBN)) {
                    bookFound = true;
                    if (bookLines[4].equals("false")) {
                        bookLines[4] = "true";
                        bookLine = String.join(",", bookLines);
                        System.out.println("You have successfully returned the book.");
                        isBookReturned = true;
                    }
                }
                readLines.add(bookLine);
            }
            fileScanner.close();
            if (!bookFound) {
                System.out.println("No book with given ISBN found in the System");
            } else if (!isBookReturned) {
                System.out.println("The book you want to return is not borrowed");
            }
            FileWriter writer = new FileWriter(BookList);
            for (String readLine : readLines) {
                writer.write(readLine + "\n");
            }
            writer.close();

            if (isBookReturned) {

                Scanner trackUserWrite = new Scanner(new File("userTrackBooks.csv"));
                while (trackUserWrite.hasNextLine()) {
                    String lineToTrack = trackUserWrite.nextLine();
                    String[] trackUser = lineToTrack.split(",");
                    if (!(trackUser[0].equals(ISBN) && trackUser[1].equals(email))) {
                        userTrackBookLines.add(lineToTrack);
                    }
                }
                trackUserWrite.close();

                FileWriter trackBook = new FileWriter("userTrackBooks.csv");
                for (String trackLine : userTrackBookLines) {
                    trackBook.write(trackLine + "\n");
                }
                trackUserWrite.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found for returning book");
        } catch (IOException e) {
            System.out.println("Error occured while returning the book");
        }
    }
}
