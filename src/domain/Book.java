package domain;
import java.util.Calendar;
public abstract class Book {
    private String isbn;
    private String title;
    private int publicationYear;
    private double price;
    private String author;


    public Book(String title, int publicationYear, double price, String author) {
        setIsbn(ISBNGenerator.generate());
        setTitle(title);
        setPublicationYear(publicationYear);
        setPrice(price);
        setAuthor(author);
    }
     // Setters and Getters
    public String getIsbn() { return isbn; }
    protected void setIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty())
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        this.title = title;
    }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (publicationYear <= 0 || publicationYear > currentYear)
            throw new IllegalArgumentException("Invalid publication year");
        this.publicationYear = publicationYear;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be null or empty");
        this.author = author;
    }


    public  boolean isSellable(){
        return true;
    }
}

