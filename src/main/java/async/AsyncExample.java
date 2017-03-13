package async;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class AsyncExample {

    private static final String API_KEY = "AIzaSyDd3sICzBWilfA9vV5uERysF-FEDFu5_mI";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public static void main(String[] args) {
        getBooksByCategoryAsync()
                .thenAcceptAsync(System.out::println);
        System.out.println("Processing...");
    }

    private static String getUrlOfRequest(String query) {
        return BASE_URL + query + "&key=" + API_KEY;
    }

    private static CompletableFuture<List<Book>> getBooksByCategoryAsync() {
        return CompletableFuture
                    .supplyAsync(() -> {
                                try {
                                    return fetchBooksByCategory("science");
                                } catch (IOException | HttpNoSuccessException e) {
                                    e.printStackTrace();
                                }
                                return new ArrayList<>();
                            },
                            Executors.newFixedThreadPool(1));
    }

    private static List<Book> fetchBooksByCategory(String categoryName) throws IOException, HttpNoSuccessException {
        final String route = getUrlOfRequest("subject:" + categoryName);
        JsonNode root = HttpUtils.get(route);
        return extractBooksFromJsonPayload(root);
    }

    private static List<Book> extractBooksFromJsonPayload(JsonNode root) {
        List<Book> books = new ArrayList<>();

        JsonNode items = root.get("items");
        items.forEach(item -> {
            JsonNode volumeInfo = item.get("volumeInfo");
            String title = volumeInfo.get("title").asText();

            Book book = new Book(title);
            books.add(book);
        });
        return books;
    }
}
