package library.exceptions;

// Thrown when a member ID does not exist.
public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(int memberId) {
        super("No member with ID " + memberId);
    }
}
