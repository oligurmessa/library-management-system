package library;

import java.util.ArrayList;

// Abstract because every member is either a Student or a Professor.
// The subclasses decide how many books they are allowed to borrow.
public abstract class Member {
    private String name;
    private int memberId;
    private ArrayList<Book> borrowedBooks;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public abstract int getBorrowLimit();

    public abstract String getType();

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < getBorrowLimit();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // returns false if the member didn't have this book
    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "#" + memberId + " " + name + " (" + getType() + ", "
                + borrowedBooks.size() + "/" + getBorrowLimit() + " books)";
    }
}
