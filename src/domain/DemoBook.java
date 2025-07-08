package domain;

public class DemoBook extends Book {
    public DemoBook(String title, int publicationYear, String author) {
        super(title, publicationYear, 0, author);
    }

    @Override public boolean isSellable() { return false; }
}

