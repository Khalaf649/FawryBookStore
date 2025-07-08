import domain.Book;
import factory.BookFactory;
import service.BookStoreService;

import java.util.List;

public class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        BookStoreService store = new BookStoreService();


        // create books


        Book cleanCode = BookFactory.createPaperBook(
                "Clean Code", 2008, 45.0, "Robert C. Martin", 5, 1.2
        );

        Book javaLegacy = BookFactory.createPaperBook(
                "Java 1.4 Legacy", 2001, 10.0, "Sun Microsystems", 3, 1.0
        );

        Book effectiveJava = BookFactory.createEBook(
                "Effective Java", 2018, 25.0, "Joshua Bloch", "PDF"
        );

        Book quantumIntro = BookFactory.createDemoBook(
                "Intro to Quantum Programming", 2024, "Q Master"
        );
        // add to the book to the inventory

        store.addBook(cleanCode);
        store.addBook(javaLegacy);
        store.addBook(effectiveJava);
        store.addBook(quantumIntro);


        System.out.println("\n Inventory BEFORE:");
        List<Book> booksBefore = store.printAllBooks();
        for (Book book : booksBefore) {
            System.out.printf("- [%s] [%s] \"%s\" by %s (%d) - $%.2f\n",
                    book.getIsbn(),
                    book.getClass().getSimpleName(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getPrice()
            );
        }
        // remove books for age>20

        List<Book> removed = store.removeOutdatedBooks(20);
        System.out.println("\n Removed outdated books:");
        for (Book b : removed) {
            System.out.println("- " + b.getTitle());
        }

        // buy paperBook
        try {
            double paid = store.buyBook(cleanCode.getIsbn(), 2, "user1@mail.com", "123 Street");
            System.out.println("\n Bought 2x PaperBook. Total paid: $" + paid);
        } catch (Exception e) {
            System.out.println(" Error buying PaperBook: " + e.getMessage());
        }

        // buy Ebook
        try {
            double paid = store.buyBook(effectiveJava.getIsbn(), 1, "reader@mail.com", null);
            System.out.println(" Bought EBook. Total paid: $" + paid);
        } catch (Exception e) {
            System.out.println(" Error buying EBook: " + e.getMessage());
        }

          // buy 3 e books should fail
        try {
            store.buyBook(effectiveJava.getIsbn(), 3, "cheater@mail.com", null);
            System.out.println("⚠ Unexpected success buying 3 EBooks!");
        } catch (Exception e) {
            System.out.println(" Expected error: " + e.getMessage());
        }

        // print  inventory
        System.out.println("\n Inventory BEFORE:");
         booksBefore = store.printAllBooks();
        for (Book book : booksBefore) {
            System.out.printf("- [%s] [%s] \"%s\" by %s (%d) - $%.2f\n",
                    book.getIsbn(),
                    book.getClass().getSimpleName(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getPrice()
            );
        }



        //   Test Adding Duplicate Book
        try {
            store.addBook(cleanCode);
            System.out.println("\n Added duplicate book 'Clean Code'. Check if this is allowed!");
        } catch (Exception e) {
            System.out.println(" Error adding duplicate book: " + e.getMessage());
        }

        //   Test Buying Book with Insufficient Stock
        try {
            store.buyBook(cleanCode.getIsbn(), 10, "user2@mail.com", "456 Avenue");
            System.out.println("️ Unexpected success buying more than available stock!");
        } catch (Exception e) {
            System.out.println("\n Error buying book with insufficient stock: " + e.getMessage());
        }

        //   Test Buying DemoBook (should fail)
        try {
            double paid = store.buyBook(quantumIntro.getIsbn(), 1, "demoUser@mail.com", null);
            System.out.println(" Bought DemoBook. Total paid: $" + paid);
        } catch (Exception e) {
            System.out.println(" Error buying DemoBook: " + e.getMessage());
        }

        //   Test Removing Outdated Books with Different Threshold
        List<Book> removedAgain = store.removeOutdatedBooks(10);
        System.out.println("\n Removed books older than 10 years:");
        for (Book b : removedAgain) {
            System.out.println("- " + b.getTitle());
        }

        //   Test Buying Book with Invalid ISBN
        try {
            store.buyBook("INVALID-ISBN", 1, "invalid@mail.com", null);
            System.out.println("️ Unexpected success with invalid ISBN!");
        } catch (Exception e) {
            System.out.println(" Error buying book with invalid ISBN: " + e.getMessage());
        }
    }
}