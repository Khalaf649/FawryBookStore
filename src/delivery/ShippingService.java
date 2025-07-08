package delivery;

import domain.Book;
import domain.PaperBook;

public class ShippingService implements DeliveryService {
    @Override
    public boolean supports(Book book) {
        return book instanceof PaperBook;
    }

    @Override
    public void deliver(Book book, String address) {
        PaperBook paperBook = (PaperBook) book;
        System.out.println("Shipping " + paperBook.getTitle() + " to " + address);
    }
}
