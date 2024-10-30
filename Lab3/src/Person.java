public abstract class Person implements Surface {

    private static final double BSA_BUFFER_FACTOR = 1.2; // 20% extra space for breathing room

    private String ID;
    private int weight;
    private int height;
    private int destinationFloor;

    public Person(String ID, int weight, int height) {
        this.ID = ID;
        this.weight = weight;
        this.height = height;
    }

    public double getSurface() {
        // approx. body surface area (BSA)
        double bsa = 0.007184 * Math.pow(weight, 0.425) * Math.pow(height, 0.725);
        return bsa * BSA_BUFFER_FACTOR;
    }

    public abstract int getPriorityLevel();
    public abstract int getWeight();

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int floor) {
        this.destinationFloor = floor;
    }

}
