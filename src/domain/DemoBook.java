package domain;

public class DemoBook extends Book {
    public DemoBook(String title, int publicationYear, double price, String author) {
        super(title, publicationYear, price, author);
    }

    @Override public boolean isSellable() { return false; }
}

