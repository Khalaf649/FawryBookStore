package factory;
import domain.*;

public final class BookFactory {
    private BookFactory() {}


    public static PaperBook createPaperBook(String title, int publicationYear, double price,
                                            String author, int stock, double weight){
        return new PaperBook(title, publicationYear, price, author, stock, weight);
    }

    public  static  EBook createEBook(String title, int publicationYear, double price,
                                      String author, String fileType){
        return new EBook(title, publicationYear, price, author, fileType);
    }

    public  static DemoBook createDemoBook(String title, int publicationYear, String author){
        return new DemoBook(title, publicationYear, author);
    }




}

