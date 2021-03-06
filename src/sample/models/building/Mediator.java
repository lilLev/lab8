package sample.models.building;

import javafx.collections.ObservableList;
import sample.models.building.elevator.DirectionEnum;
import sample.models.building.elevator.Elevator;
import sample.models.building.passenger.Passenger;

public class Mediator {

    public void notify(Object obj) {
        if (obj.getClass() == Passenger.class) {
            reactOnPassenger((Passenger) obj);
        } else if (obj.getClass() == Elevator.class) {
            reactOnElevator((Elevator) obj);
        }
    }

    private void reactOnPassenger(Passenger passenger) {
        Elevator elevator = Building.getInstance(null, null).
                getElevators().
                get(passenger.getQueue());


        elevator.AddNewDestination(passenger.getCurrentFloor().getId());
        if (elevator.getCurrentDirection() == DirectionEnum.Wait) {
            synchronized (elevator) {
                elevator.notify();
            }
        }
    }

    private void reactOnElevator(Elevator elevator) {
        ObservableList<Passenger> passengers = Building.getInstance(null, null).
                getFloors().
                get(elevator.getCurrentFloor()).
                getPassengers().
                get(elevator.getIdNum());

        while (!passengers.isEmpty() && elevator.canEnter(passengers.get((0)))) {
            elevator.addPassenger(passengers.get(0));
            passengers.remove(0);
        }
    }
}
