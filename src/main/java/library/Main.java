package library;

import java.util.ArrayList;
import java.util.Scanner;

import library.exceptions.BookNotAvailableException;
import library.exceptions.MemberNotFoundException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        addSampleData();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addBook(); break;
                case "2": registerMember(); break;
                case "3": borrowBook(); break;
                case "4": returnBook(); break;
                case "5": searchBook(); break;
                case "6": printBooks(library.listAvailableBooks()); break;
                case "7": printBooks(library.getBooks()); break;
                case "8": listMembers(); break;
                case "0": running = false; break;
                default: System.out.println("Please pick a number from the menu.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Library Menu =====");
        System.out.println("1. Add a book");
        System.out.println("2. Register a member");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Search for a book");
        System.out.println("6. List available books");
        System.out.println("7. List all books");
        System.out.println("8. List members");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    // a few books and members so the program isn't empty when it starts
    private static void addSampleData() {
        library.addBook(new Book("Clean Code", "Robert C. Martin", "111"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "222"));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "333"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "444"));
        library.registerMember("Alice", "student");
        library.registerMember("Dr. Brown", "professor");
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        if (title.isEmpty() || isbn.isEmpty()) {
            System.out.println("Title and ISBN can't be empty.");
            return;
        }
        library.addBook(new Book(title, author, isbn));
        System.out.println("Book added.");
    }

    private static void registerMember() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Type (student/professor): ");
        String type = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name can't be empty.");
            return;
        }
        Member member = library.registerMember(name, type);
        System.out.println("Registered: " + member);
    }

    private static void borrowBook() {
        int memberId = readMemberId();
        if (memberId == -1) {
            return;
        }
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        try {
            library.borrowBook(memberId, isbn);
            System.out.println("Book borrowed.");
        } catch (MemberNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        int memberId = readMemberId();
        if (memberId == -1) {
            return;
        }
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        try {
            library.returnBook(memberId, isbn);
            System.out.println("Book returned.");
        } catch (MemberNotFoundException | BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchBook() {
        System.out.print("Title or author: ");
        String keyword = scanner.nextLine().trim();
        printBooks(library.searchBook(keyword));
    }

    private static void listMembers() {
        for (Member member : library.getMembers()) {
            System.out.println(member);
            for (Book book : member.getBorrowedBooks()) {
                System.out.println("    - " + book.getTitle());
            }
        }
    }

    private static void printBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // returns -1 if the user didn't type a number
    private static int readMemberId() {
        System.out.print("Member ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Member ID must be a number.");
            return -1;
        }
    }
}
