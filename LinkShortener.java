import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public LinkShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shortenLink(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    public String getOriginalLink(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "Short URL not found");
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            shortUrl.append(randomChar);
        }

        return shortUrl.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        // Example usage
        String longUrl = "https://www.example.com";
        String shortUrl = linkShortener.shortenLink(longUrl);

        System.out.println("Original URL: " + longUrl);
        System.out.println("Shortened URL: " + shortUrl);

        String originalLink = linkShortener.getOriginalLink(shortUrl);
        System.out.println("Original Link for " + shortUrl + ": " + originalLink);
    }
}