package library;

import java.util.ArrayList;

import library.exceptions.BookNotAvailableException;
import library.exceptions.MemberNotFoundException;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private int nextMemberId;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        nextMemberId = 1;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    // type is "student" or "professor". Returns the new member so the
    // caller can show the ID.
    public Member registerMember(String name, String type) {
        Member member;
        if (type.equalsIgnoreCase("professor")) {
            member = new Professor(name, nextMemberId);
        } else {
            member = new Student(name, nextMemberId);
        }
        nextMemberId++;
        members.add(member);
        return member;
    }

    public void borrowBook(int memberId, String isbn)
            throws MemberNotFoundException, BookNotAvailableException {
        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("\"" + book.getTitle() + "\" is already borrowed");
        }
        if (!member.canBorrow()) {
            // works for Student and Professor without checking which one it is
            throw new BookNotAvailableException(member.getName() + " has reached the limit of "
                    + member.getBorrowLimit() + " books");
        }

        book.borrow();
        member.borrowBook(book);
    }

    public void returnBook(int memberId, String isbn)
            throws MemberNotFoundException, BookNotAvailableException {
        Member member = findMember(memberId);
        Book book = findBook(isbn);

        if (!member.returnBook(book)) {
            throw new BookNotAvailableException(member.getName() + " did not borrow \"" + book.getTitle() + "\"");
        }
        book.returnItem();
    }

    // searches title and author, ignoring upper/lower case
    public ArrayList<Book> searchBook(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(key)
                    || book.getAuthor().toLowerCase().contains(key)) {
                results.add(book);
            }
        }
        return results;
    }

    public ArrayList<Book> listAvailableBooks() {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                results.add(book);
            }
        }
        return results;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member findMember(int memberId) throws MemberNotFoundException {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new MemberNotFoundException(memberId);
    }

    private Book findBook(String isbn) throws BookNotAvailableException {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new BookNotAvailableException("No book with ISBN " + isbn);
    }
}
