package library;

public class Professor extends Member {

    public Professor(String name, int memberId) {
        super(name, memberId);
    }

    // professors can take out more books than students
    @Override
    public int getBorrowLimit() {
        return 10;
    }

    @Override
    public String getType() {
        return "Professor";
    }
}
