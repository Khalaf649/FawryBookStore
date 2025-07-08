package service;

import delivery.*;

import domain.*;
import inventory.Inventory;

import java.util.List;


public class BookStoreService {
    private final Inventory inventory = Inventory.getInstance();
    private final List<DeliveryService> deliveryServices = List.of(
            new ShippingService(),
            new MailService()

    );

    public double buyBook(String isbn, int quantity, String email, String address) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN must not be null or blank");
        }

        Book book = inventory.getBook(isbn);

        if (book instanceof Shippable stockBook) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive for stock-tracked books");
            }
            synchronized (book) {
                if (stockBook.getStock() < quantity) {
                    throw new IllegalStateException("Not enough stock");
                }
                stockBook.setStock(stockBook.getStock() - quantity);
            }
        } else {

            if (quantity != 1) {
                throw new IllegalArgumentException("You can only buy 1 copy of this digital product");
            }
        }

        double total = book.getPrice() * quantity;

        String contactInfo = (book instanceof Shippable) ? address : email;

        boolean delivered = false;
        for (DeliveryService service : deliveryServices) {
            if (service.supports(book)) {
                service.deliver(book, contactInfo);
                delivered = true;
                break;
            }
        }

        if (!delivered) {
            throw new UnsupportedOperationException("No delivery service for: " + book.getClass().getSimpleName());
        }

        return total;
    }


    public void addBook(Book book) {
        inventory.addBook(book);
    }
}
