package domain;
import java.util.UUID;
public final class ISBNGenerator {
    private ISBNGenerator() {}

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
