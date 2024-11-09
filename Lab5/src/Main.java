import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            OutputDevice.print("\n=== Main Menu ===");
            OutputDevice.print("Select an option:");
            OutputDevice.print("[1] Run demo");
            OutputDevice.print("[2] Load existing elevator");
            OutputDevice.print("[3] Create new elevator");
            OutputDevice.print("[4] Exit");
            OutputDevice.print("Enter your choice:");

            String option = InputDevice.getInput().trim();

            switch (option) {
                case "1":
                    runDemo(app);
                    break;
                case "2":
                    OutputDevice.print("[INFO] 'Load' option is not available yet.");
                    break;
                case "3":
                    OutputDevice.print("[INFO] 'New elevator' option is not available yet.");
                    break;
                case "4":
                    OutputDevice.print("[INFO] Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    OutputDevice.print("[ERROR] Invalid option. Please select 1, 2, 3, or 4.");
                    break;
            }
        }
    }

    private static void runDemo(Application app) {
        OutputDevice.print("\n=== Demo Mode ===");
        OutputDevice.print("Available commands:");
        OutputDevice.print(" - addElevator maxWeight width depth currentFloor");
        OutputDevice.print(" - addPerson type weight height startFloor destinationFloor [extra]");
        OutputDevice.print(" - startSession");
        OutputDevice.print("Enter a command (or type 'exit' to quit demo mode):");

        while (true) {
            String input = InputDevice.getInput().trim();

            if (input.equalsIgnoreCase("exit")) {
                OutputDevice.print("[INFO] Exiting demo mode.");
                break;
            }

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
                            OutputDevice.print("[ERROR] Invalid number format. Ensure maxWeight, width, depth, and currentFloor are integers.");
                        }
                    } else {
                        OutputDevice.print("[USAGE] addElevator maxWeight width depth currentFloor");
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

                            Person person = createPerson(type, weight, height, inputParts);
                            if (person != null) {
                                app.addPersonToQueue(person, startFloor, destinationFloor);
                            }
                        } catch (NumberFormatException e) {
                            OutputDevice.print("[ERROR] Invalid number format. Please check that weight, height, startFloor, and destinationFloor are integers.");
                        }
                    } else {
                        OutputDevice.print("[USAGE] addPerson type weight height startFloor destinationFloor [extra]");
                    }
                    break;

                case "startSession":
                    app.startSession();
                    break;

                default:
                    OutputDevice.print("[ERROR] Unknown command: '" + command + "'. Type 'addElevator', 'addPerson', or 'startSession'.");
                    break;
            }
        }
    }

    private static Person createPerson(String type, int weight, int height, String[] inputParts) {
        try {
            switch (type) {
                case "Visitor":
                    return new Visitor(weight, height);
                case "Patient":
                    Patient patient = new Patient(weight, height);
                    if (inputParts.length >= 7) {
                        String walkingAid = inputParts[6];
                        patient.setWalkingAid(WalkingAid.valueOf(walkingAid));
                    }
                    return patient;
                case "Nurse":
                    Nurse nurse = new Nurse(weight, height);
                    if (inputParts.length >= 7) {
                        int emergencyLevel = Integer.parseInt(inputParts[6]);
                        nurse.setEmergencyLevel(emergencyLevel);
                    }
                    return nurse;
                case "Doctor":
                    Doctor doctor = new Doctor(weight, height);
                    if (inputParts.length >= 7) {
                        int emergencyLevel = Integer.parseInt(inputParts[6]);
                        doctor.setEmergencyLevel(emergencyLevel);
                    }
                    return doctor;
                default:
                    OutputDevice.print("[ERROR] Invalid person type. Use 'Visitor', 'Patient', 'Nurse', or 'Doctor'.");
                    return null;
            }
        } catch (IllegalArgumentException e) {
            OutputDevice.print("[ERROR] Invalid argument: " + e.getMessage());
            return null;
        }
    }
}
