public class NetworkIssueRequest extends AbstractSupportRequest {
    private final int affectedUsers;

    public NetworkIssueRequest(int id, String title, int basePoints, int affectedUsers) {
        super(id, title, basePoints);
        if (affectedUsers <= 0) {
            throw new IllegalArgumentException("Affected users must be positive.");
        }
        this.affectedUsers = affectedUsers;
    }

    public int getAffectedUsers() { return affectedUsers; }

    @Override
    protected int calculatePoints() {
        // Linear rule: award base points plus one point per affected user.
        try {
            return Math.addExact(getBasePoints(), affectedUsers);
        } catch (ArithmeticException exception) {
            throw new IllegalStateException("Network issue points exceed integer range.", exception);
        }
    }

    @Override
    public String toString() {
        return "NetworkIssueRequest{" + super.toString() + ", affected users: " + affectedUsers + "}";
    }
}
