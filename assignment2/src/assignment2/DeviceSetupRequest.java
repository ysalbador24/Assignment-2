public class DeviceSetupRequest extends AbstractSupportRequest {
    private final int deviceCount;

    public DeviceSetupRequest(int id, String title, int basePoints, int deviceCount) {
        super(id, title, basePoints);
        if (deviceCount <= 0) {
            throw new IllegalArgumentException("Device count must be positive.");
        }
        this.deviceCount = deviceCount;
    }

    public int getDeviceCount() { return deviceCount; }

    @Override
    protected int calculatePoints() {
        try {
            return Math.multiplyExact(getBasePoints(), deviceCount);
        } catch (ArithmeticException exception) {
            throw new IllegalStateException("Device setup points exceed integer range.", exception);
        }
    }

    @Override
    public String toString() {
        return "DeviceSetupRequest{" + super.toString() + ", devices: " + deviceCount + "}";
    }
}
