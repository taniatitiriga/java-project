import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        Scanner scanner = new Scanner(System.in);

        // Main loop to accept input
        while (true) {
            OutputDevice.print("Enter a command (or 'exit' to quit): ");
            String input = InputDevice.getInput();

            if (input.equalsIgnoreCase("exit")) {
                break;  // Exit the program
            }

            // Split the input into commands and parameters
            String[] inputParts = input.split(" ");
            String command = inputParts[0];

            switch (command) {
                case "addElevator":
                    if (inputParts.length >= 5) {
                        try {
                            int maxWeight = Integer.parseInt(inputParts[1]);
                            int width = Integer.parseInt(inputParts[2]);
                            int depth = Integer.parseInt(inputParts[3]);
                            int currentFloor = Integer.parseInt(inputParts[4]);
                            int[] floors = {0, 1, 2, 3, 4, 5, 6, 7};  // Example floors
                            app.addElevator(maxWeight, width, depth, floors, currentFloor);
                        } catch (NumberFormatException e) {
                            OutputDevice.print("Invalid number format.");
                        }
                    } else {
                        OutputDevice.print("Usage: addElevator maxWeight width depth currentFloor");
                    }
                    break;

                case "addPerson":
                    if (inputParts.length >= 6) {
                        String type = inputParts[1];
                        try {
                            int weight = Integer.parseInt(inputParts[2]);
                            int height = Integer.parseInt(inputParts[3]);
                            int startFloor = Integer.parseInt(inputParts[4]);
                            int destinationFloor = Integer.parseInt(inputParts[5]);

                            Person person;
                            switch (type) {
                                case "Visitor":
                                    person = new Visitor(weight, height);
                                    break;
                                case "Patient":
                                    person = new Patient(weight, height);
                                    if (inputParts.length >= 7) {
                                        String walkingAid = inputParts[6];
                                        ((Patient) person).setWalkingAid(WalkingAid.valueOf(walkingAid));
                                    }
                                    break;
                                case "Nurse":
                                    person = new Nurse(weight, height);
                                    if (inputParts.length >= 7) {
                                        int emergencyLevel = Integer.parseInt(inputParts[6]);
                                        ((Nurse) person).setEmergencyLevel(emergencyLevel);
                                    }
                                    break;
                                case "Doctor":
                                    person = new Doctor(weight, height);
                                    if (inputParts.length >= 7) {
                                        int emergencyLevel = Integer.parseInt(inputParts[6]);
                                        ((Doctor) person).setEmergencyLevel(emergencyLevel);
                                    }
                                    break;
                                default:
                                    OutputDevice.print("Invalid person type.");
                                    return;
                            }

                            app.addPersonToQueue(person, startFloor, destinationFloor);
                        } catch (NumberFormatException e) {
                            OutputDevice.print("Invalid number format.");
                        }
                    } else {
                        OutputDevice.print("Usage: addPerson type weight height startFloor destinationFloor");
                    }
                    break;

                case "startSession":
                    app.startSession();
                    break;

                default:
                    OutputDevice.print("Unknown command.");
                    break;
            }
        }

        OutputDevice.print("Exiting the application.");
        scanner.close();
    }
}
