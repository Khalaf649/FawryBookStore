package domain;

public class EBook extends Book implements Digital {
    private String fileType;

    public EBook(String title, int publicationYear, double price,
                 String author, String fileType) {
        super(title, publicationYear, price, author);
        setFileType(fileType);
    }

    @Override public String getFileType() { return fileType; }
    public void setFileType(String fileType) {
        if (fileType == null || fileType.isEmpty())
            throw new IllegalArgumentException("File type cannot be null or empty");
        this.fileType = fileType;
    }

}
