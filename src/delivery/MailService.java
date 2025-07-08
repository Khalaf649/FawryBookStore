package delivery;

import domain.Book;
import domain.EBook;

public class MailService implements DeliveryService {
    @Override
    public boolean supports(Book book) {
        return book instanceof EBook;
    }

    @Override
    public void deliver(Book book, String email) {
        EBook ebook = (EBook) book;
        System.out.println("Sending " + ebook.getTitle() + " to email " + email);
    }
}
