import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Application {
    private Elevator elevator;
    private Agenda agenda;

    // Initialize the application and create an elevator
    public void addElevator(int maxWeight, int width, int depth, int[] floors, int currentFloor) {
        this.elevator = new Elevator(IDGenerator.generateElevatorID(), maxWeight, width, depth, floors, currentFloor);
        this.agenda = new Agenda(elevator, new LinkedList<>(), new HashMap<>());
        OutputDevice.print("Elevator created on floor: " + elevator.getCurrentFloor());
    }

    // Add a person to the specified floor queue in Agenda
    public void addPersonToQueue(Person person, int startFloor, int destinationFloor) {
        if (agenda != null) {
            agenda.addPersonToQueue(person, startFloor, destinationFloor);
            OutputDevice.print(person.getClass().getSimpleName() + " added to queue on floor " + startFloor);
        } else {
            OutputDevice.print("Please add an elevator before adding people.");
        }
    }

    public void startSession() {
        if (agenda == null || elevator == null) {
            OutputDevice.print("Initialize the elevator and agenda before starting the session.");
            return;
        }

        OutputDevice.print("Starting elevator session...");
        while (true) {
            if (agenda.areAllQueuesEmpty()){
                OutputDevice.print("Session complete. No further destinations.");
                break;
            }

            OutputDevice.print("______________________________________");
            int currentFloor = elevator.getCurrentFloor();
            OutputDevice.print("Elevator at floor: " + currentFloor);
            printQueues();

            int nextFloor = agenda.determineNextDestination(elevator);
            while (nextFloor != currentFloor){
                if (nextFloor > currentFloor) {
                    elevator.moveUp();
                } else {
                    elevator.moveDown();
                }
                currentFloor = elevator.getCurrentFloor();
                OutputDevice.print("Elevator at floor: " + currentFloor);
            }
            agenda.unboardPassengers(elevator);
            agenda.boardPassengers(elevator);
        }
    }

    // Display the state of all queues (both floor queues and inside the elevator)
    private void printQueues() {
        // Print people inside the elevator
        OutputDevice.print("Inside Queue:");
        for (Person person : agenda.getQueueInside()) {
            OutputDevice.print(" - " + person.getClass().getSimpleName() + ", Dest: " + person.getDestinationFloor() +
                    ", Priority: " + person.getPriorityLevel());
        }

        // Print people waiting by floor
        OutputDevice.print("Queues by Floor:");
        for (Map.Entry<Integer, Queue<Person>> entry : agenda.getQueuesByFloor().entrySet()) {
            int floor = entry.getKey();
            Queue<Person> floorQueue = entry.getValue();
            OutputDevice.print("Floor " + floor + " Queue:");
            for (Person person : floorQueue) {
                OutputDevice.print(" - " + person.getClass().getSimpleName() + ", Dest: " + person.getDestinationFloor() +
                        ", Priority: " + person.getPriorityLevel());
            }
        }
    }
}
