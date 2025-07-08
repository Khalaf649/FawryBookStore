package domain;

public class PaperBook extends Book implements Shippable {
    private int stock;
    private double weight;

    public PaperBook(String title, int publicationYear, double price,
                     String author, int stock, double weight) {
        super(title, publicationYear, price, author);
        setStock(stock);
        setWeight(weight);
    }

    @Override public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0)
            throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }

    @Override public double getWeight() { return weight; }
    public void setWeight(double weight) {
        if (weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative");
        this.weight = weight;
    }




}
