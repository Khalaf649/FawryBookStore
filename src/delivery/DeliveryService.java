package delivery;

import domain.Book;

public interface DeliveryService {
    boolean supports(Book book);
    void deliver(Book book, String contactInfo);
}