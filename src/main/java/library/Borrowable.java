package library;

// Anything that can be borrowed from the library.
// Right now only Book implements this, but a DVD or magazine could too.
public interface Borrowable {
    void borrow();
    void returnItem();
    boolean isAvailable();
}
