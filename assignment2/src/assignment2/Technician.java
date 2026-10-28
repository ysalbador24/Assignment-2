package assignment2;

public class Technician {

    private final String name;
    private int servicePoints;

    public Technician(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Technician name cannot be null or blank.");
        }

        this.name = name;
        this.servicePoints = 0;
    }

    public String getName() {
        return name;
    }

    public int getServicePoints() {
        return servicePoints;
    }

    public void addPoints(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Points to add must be positive.");
        }

        // Prevent integer overflow from making the total negative.
        if (amount > Integer.MAX_VALUE - servicePoints) {
            throw new IllegalArgumentException("Service point total is too large.");
        }

        servicePoints += amount;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Technician technician = (Technician) other;
        // Names must match exactly, including capitalization and spaces.
        return name.equals(technician.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Technician: " + name + ", service points: " + servicePoints;
    }
}