
public class Main {
    public static void main(String[] args) {
        Application app = new Application();

        if (args.length > 0) {
            switch (args[0]) {
                case "addElevator":
                    if (args.length >= 5) {
                        int maxWeight = Integer.parseInt(args[1]);
                        int width = Integer.parseInt(args[2]);
                        int depth = Integer.parseInt(args[3]);
                        int[] floors = {0, 1, 2, 3, 4, 5, 6, 7}; // Sample floors
                        int currentFloor = Integer.parseInt(args[4]);
                        app.addElevator(maxWeight, width, depth, floors, currentFloor);
                    } else {
                        OutputDevice.print("Usage: addElevator maxWeight width depth currentFloor");
                    }
                    break;
                case "addPerson":
                    if (args.length >= 5) {
                        String personType = args[1];
                        int weight = Integer.parseInt(args[2]);
                        int height = Integer.parseInt(args[3]);
                        int destinationFloor = Integer.parseInt(args[4]);
                        app.addPerson(personType, weight, height, destinationFloor);
                    } else {
                        OutputDevice.print("Usage: addPerson type weight height destinationFloor");
                    }
                    break;
                default:
                    OutputDevice.print("Invalid command. Use addElevator or addPerson.");
                    break;
            }
        } else {
            OutputDevice.print("Please provide a command: addElevator or addPerson");
        }
    }
}
