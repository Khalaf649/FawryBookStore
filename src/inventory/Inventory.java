package inventory;
import domain.*;

import java.time.Year;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private static volatile Inventory instance;
    private final Map<String, Book> books = new ConcurrentHashMap<>();

    private Inventory() {}

    public static Inventory getInstance() {
        if (instance == null) {
            synchronized (Inventory.class) {
                if (instance == null) instance = new Inventory();
            }
        }
        return instance;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null");
        }
        String isbn = book.getIsbn();
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Book ISBN must not be null or blank");
        }
        if (books.containsKey(isbn)) {
            throw new IllegalArgumentException("A book with ISBN '" + isbn + "' already exists");
        }
        books.put(isbn, book);
    }

    public List<Book> removeOutdatedBooks(int maxAgeInYears) {
        int cutoff = Year.now().getValue() - maxAgeInYears;
        List<Book> removed = new ArrayList<>();
        books.values().removeIf(b -> {
            if (b.getPublicationYear() <= cutoff) {
                removed.add(b);
                return true;
            }
            return false;
        });
        return removed;
    }

    public Book getBook(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN must not be null or blank");
        }
        Book found = books.get(isbn);
        if (found == null) {
            throw new NoSuchElementException("No book found with ISBN '" + isbn + "'");
        }
        return found;
    }
}
