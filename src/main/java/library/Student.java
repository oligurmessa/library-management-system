package library;

public class Student extends Member {

    public Student(String name, int memberId) {
        super(name, memberId);
    }

    @Override
    public int getBorrowLimit() {
        return 3;
    }

    @Override
    public String getType() {
        return "Student";
    }
}
