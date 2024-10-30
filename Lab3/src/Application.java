import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Application {
    private Agenda agenda;
    private Elevator elevator;

    public Application() {
        agenda = new Agenda(new LinkedList<>(), new HashMap<>());
    }

    public void addElevator(int maxWeight, int width, int depth, int[] floors, int currentFloor) {
        elevator = new Elevator(IDGenerator.generateElevatorID(), maxWeight, width, depth, floors, currentFloor);
        OutputDevice.print("Elevator created: " + elevator.getCurrentFloor());
    }

    public void addPerson(String type, int weight, int height, int destinationFloor) {
        Person person;
        switch (type) {
            case "Patient":
                person = new Patient(weight, height);
                break;
            case "Visitor":
                person = new Visitor(weight, height);
                break;
            case "Nurse":
                person = new Nurse(weight, height);
                break;
            case "Doctor":
                person = new Doctor(weight, height);
                break;
            default:
                OutputDevice.print("Unknown person type: " + type);
                return;
        }
        agenda.addPersonToQueue(person, elevator.getCurrentFloor(), destinationFloor);
        OutputDevice.print("Added " + type + " to queue with ID: " + person.getPriorityLevel());
    }
}
