# Library Management System

A small console program I wrote while learning object-oriented programming in
Java. You can add books, register members, and borrow / return / search books
from a text menu.

## OOP concepts used

| Concept | Where |
|---|---|
| Classes and encapsulation | `Book`, `Member`, `Library` (private fields, getters) |
| Inheritance | `Student` and `Professor` extend `Member` |
| Abstract class | `Member` has abstract `getBorrowLimit()` and `getType()` |
| Polymorphism | `Library` calls `member.getBorrowLimit()` without knowing the member type (students get 3 books, professors 10) |
| Interface | `Book` implements `Borrowable` |
| Custom exceptions | `BookNotAvailableException`, `MemberNotFoundException` |
| Collections | `ArrayList` for the books, members and borrowed books |

## How to run

Needs a JDK (I used Java 21). From the project folder:

```
javac -d out $(find src -name "*.java")
java -cp out library.Main
```

The program starts with a few sample books (ISBN 111, 222, 333, 444) and two
members (ID 1 is a student, ID 2 is a professor).

## Example

```
===== Library Menu =====
1. Add a book
2. Register a member
3. Borrow a book
4. Return a book
5. Search for a book
6. List available books
7. List all books
8. List members
0. Exit
Choice: 3
Member ID: 1
ISBN: 111
Book borrowed.

Choice: 3
Member ID: 2
ISBN: 111
Error: "Clean Code" is already borrowed
```

## Project structure

```
src/main/java/library/
├── Main.java          menu and user input
├── Library.java       stores books and members, borrow/return/search logic
├── Book.java
├── Borrowable.java    interface
├── Member.java        abstract base class
├── Student.java
├── Professor.java
└── exceptions/
    ├── BookNotAvailableException.java
    └── MemberNotFoundException.java
```

## Limitations

- Nothing is saved. All data is gone when the program exits.
- No due dates or late fees.
- ISBNs are not checked for duplicates.
