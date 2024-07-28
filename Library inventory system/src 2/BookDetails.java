import java.util.Date; // this is a getter-setter class where we have

public class BookDetails {

    private String title;
    private String authors;
    private String ISBN;
    private int yearOfPublication;
    private boolean isAvailable;
    public String borrowedUser;
    private Date DateBorrowed;
    private Date dateToBeReturned;


    public BookDetails(String title, String authors, String ISBN, int yearOfPublication, boolean isAvailable, String borrowedUser) {
        this.title = title;
        this.authors = authors;
        this.ISBN = ISBN;
        this.yearOfPublication = yearOfPublication;
        this.isAvailable = true;
        this.DateBorrowed = null;
        this.dateToBeReturned = null;
        this.borrowedUser = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    //////////////////////////////////////////////////////////////////
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public String getAuthors() {
        return authors;
    }
    //////////////////////////////////////////////////////////////////

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    //////////////////////////////////////////////////////////////////
    public void setYearOfPublication(int yearOfPublication) {

        this.yearOfPublication = yearOfPublication;
    }
    public int getYearOfPublication()
    {
        return yearOfPublication;
    }
    //////////////////////////////////////////////////////////////////
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    //////////////////////////////////////////////////////////////////
    public String getBorrowedUser()
    {
        return borrowedUser;
    }
    public void setBorrowedUser(String borrowedUser) {
        this.borrowedUser = null;
    }
    //////////////////////////////////////////////////////////////////
    public void setDateBorrowed(Date dateBorrowed){

        this.DateBorrowed = dateBorrowed;
    }
    public Date getDateBorrowed()
    {
        return DateBorrowed;
    }
    //////////////////////////////////////////////////////////////////
   public void setDateToBeReturned(Date dateToBeReturned){

        this.dateToBeReturned = dateToBeReturned;
   }
    public Date getDateToBeReturned()
    {

        return dateToBeReturned;
    }
    //////////////////////////////////////////////////////////////////
}