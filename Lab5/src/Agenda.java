import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.DoubleStream;

import static java.lang.Long.sum;

public class Agenda {
    private Elevator elevator;
    private Queue<Person> queueInside;
    private Map<Integer, Queue<Person>> queuesByFloor;

    public Agenda(Elevator elevator, Queue<Person> queueInside, Map<Integer, Queue<Person>> queuesByFloor) {
        //an agenda handles a single elevator, and the respective queues
        this.elevator = elevator;
        this.queueInside = queueInside;
        this.queuesByFloor = queuesByFloor;
    }

    public void addPersonToQueue(Person person, int floor, int destinationFloor) {
        //assign destination
        person.setDestinationFloor(destinationFloor);

        //initialize floor queue if necessary
        queuesByFloor.computeIfAbsent(floor, k -> new LinkedList<>()).add(person);

        //sort queue by priority
        sortAndGroup();
    }

    public Comparator<Person> comparePriority() {
        //compare by priority (bigger priority comes first)
        return Comparator.comparingInt(Person::getPriorityLevel).reversed();
    }

    public Person getNextPerson() {
        // get next person inside elevator by priority
        if (!queueInside.isEmpty()) {
            List<Person> sortedList = new ArrayList<>(queueInside);
            sortedList.sort(comparePriority());
            return sortedList.get(0);
        }
        return null;
    }
    public boolean isFull() {
        return getAvailableSpace() <= 0 || getAvailableWeight() <= 0;
    }

    public void boardPassengers(Elevator elevator, int floor) {
        //nobody to board
        Queue<Person> floorQueue = queuesByFloor.get(floor);
        if (floorQueue == null || floorQueue.isEmpty()) return;

        while (!floorQueue.isEmpty() && !isFull()) {
            Person person = floorQueue.peek();

            int personWeight = person.getWeight();

            if (getAvailableSpace() >= personWeight) {
                queueInside.add(floorQueue.poll());
            } else {
                break;
            }
        }
        sortAndGroup();
    }

    public int determineNextDestination(Elevator elevator) {
        // no destination if no passengers
        if (queueInside.isEmpty()) return elevator.getCurrentFloor();

        Person topPriorityPerson = queueInside.peek();
        int destination = topPriorityPerson.getDestinationFloor();

        if (topPriorityPerson.getPriorityLevel() >= 6) {
            //if doctor or nurse with lvl. 3 emergency, go straight there
            return destination;
        } else {
            //otherwise have stops on the way
            return getNextIntermediateStop(elevator.getCurrentFloor(), destination);
        }
    }

    public int getNextIntermediateStop(int currentFloor, int destinationFloor) {
        int direction = Integer.compare(destinationFloor, currentFloor);
        int nextStop = destinationFloor;

        for (int floor = currentFloor + direction; floor != destinationFloor; floor += direction) {
            //stop for outside queue
            if (queuesByFloor.containsKey(floor) && !queuesByFloor.get(floor).isEmpty()) {
                return floor;
            }
            //stop for inside people
            for (Person person : queueInside) {
                if (person.getDestinationFloor() == floor) {
                    return floor;
                }
            }
        }
        return nextStop;
    }


    public void sortAndGroup() {
        for (Queue<Person> floorQueue : queuesByFloor.values()) {
            List<Person> sortedList = new ArrayList<>(floorQueue);
            sortedList.sort(comparePriority());
            floorQueue.clear();
            floorQueue.addAll(sortedList);
        }
    }

    public double getAvailableSpace() {
        double usedSpace = queueInside.stream()
                .mapToDouble(person -> person.getSurface())
                .sum();
        return elevator.getSurface() - usedSpace;
    }

    public double getAvailableWeight() {
        double usedWeight = queueInside.stream()
                .mapToInt(person -> person.getWeight())
                .sum();
        return Math.max(0, elevator.getMaxWeight() - usedWeight);
    }


    public boolean evaluateStop(Elevator elevator, int floor) {
        Queue<Person> floorQueue = queuesByFloor.get(floor);

        if (floorQueue == null || floorQueue.isEmpty()) {
            return false;
        }
        // check if the highest priority on outside queue is greater than the one inside
        return floorQueue.peek().getPriorityLevel() > queueInside.peek().getPriorityLevel();
    }

}