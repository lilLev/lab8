package sample.models.building;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.building.passenger.Passenger;

import java.util.logging.Level;

public class Floor {
    private final ObservableList<ObservableList<Passenger>> passengers;
    private final int maxQueueSize;
    private final int id;

    public Floor(int amountOfElevators, int maxQueueSize, int id) {
        passengers = FXCollections.observableArrayList();
        this.id = id;
        this.maxQueueSize = maxQueueSize;

        for (int i = 0; i < amountOfElevators; ++i) {
            passengers.add(FXCollections.observableArrayList());
        }

    }

    public ObservableList<ObservableList<Passenger>> getPassengers() {
        return passengers;
    }

    public int getQueueNumber(ObservableList<Passenger> queue) {
        return passengers.indexOf(queue);
    }

    public void addPassengerToQueue(Passenger passenger) {
        if (passengers.get(passenger.getQueue()).size() < maxQueueSize) {
            passengers.get(passenger.getQueue()).add(passenger);
        }
    }

    public void clear()
    {
        passengers.clear();
    }

    public int getId() {
        return id;
    }
}
