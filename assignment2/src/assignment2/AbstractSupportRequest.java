public abstract class AbstractSupportRequest implements SupportRequest {
    private final int id;
    private final String title;
    private final int basePoints;
    private boolean resolved;

    public AbstractSupportRequest(int id, String title, int basePoints) {
        if (id <= 0) {
            throw new IllegalArgumentException("Request ID must be positive.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Request title cannot be blank.");
        }
        if (basePoints <= 0) {
            throw new IllegalArgumentException("Base points must be positive.");
        }
        this.id = id;
        this.title = title;
        this.basePoints = basePoints;
        this.resolved = false;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getTitle() { return title; }

    @Override
    public int getBasePoints() { return basePoints; }

    @Override
    public boolean isResolved() { return resolved; }

    // Shared resolution procedur -  subclasses provide their own point calculation.
    @Override
    public final int resolveFor(Technician technician) {
        if (technician == null) {
            throw new IllegalArgumentException("Technician cannot be null.");
        }
        // A request may only be resolved once.
        if (resolved) {
            throw new IllegalStateException("Request " + id + " is already resolved.");
        }
        int points = calculatePoints();
        if (points <= 0) {
            throw new IllegalStateException("Calculated service points must be positive.");
        }
        technician.addPoints(points);
        resolved = true;
        return points;
    }

    protected abstract int calculatePoints();

    @Override
    public String toString() {
        return "Request #" + id + ": " + title + " (base points: " + basePoints
                + ", resolved: " + resolved + ")";
    }
}
