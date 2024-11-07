public class OutputDevice {
    public static void print(String message) {
        System.out.println(message);
    }

    public static void printElevatorStatus(Elevator elevator) {
        print("Elevator at floor: " + elevator.getCurrentFloor());
    }
}
