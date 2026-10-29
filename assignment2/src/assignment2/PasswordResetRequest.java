public class PasswordResetRequest extends AbstractSupportRequest {
    private final String accountName;

    public PasswordResetRequest(int id, String title, int basePoints, String accountName) {
        super(id, title, basePoints);
        if (accountName == null || accountName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be blank.");
        }
        this.accountName = accountName;
    }

    public String getAccountName() { return accountName; }

    @Override
    protected int calculatePoints() {
        return getBasePoints();
    }

    @Override
    public String toString() {
        return "PasswordResetRequest{" + super.toString() + ", account: " + accountName + "}";
    }
}
