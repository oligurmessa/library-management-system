package library.exceptions;

// Thrown when a book can't be borrowed (already borrowed, or no such ISBN).
public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
