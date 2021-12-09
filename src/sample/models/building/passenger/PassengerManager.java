package sample.models.building.passenger;

import sample.models.building.Building;
import sample.models.building.Floor;
import sample.models.building.Mediator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class PassengerManager {
    private static PassengerManager instance = null;
    private final List<Timer> timers = new ArrayList<>();
    private final Mediator mediator;
    private float maxWeight;
    public boolean isRunning = true;
    private List<PassengerCreator> creators;
    private PassengerManager(int minTimeToSpawn, int maxTimeToSpawn, float maxWeight, Mediator mediator) {
        Random random = new Random(LocalTime.now().toNanoOfDay());
        this.mediator = mediator;
        List<Floor> floors = Building.getInstance(null, null).getFloors();
        this.maxWeight = maxWeight;
        for (Floor floor : floors) {
            if (!isRunning) {
                break;
            }
            Timer timer = new Timer();
            timer.schedule(new PassengerCreator(floor, floors, maxWeight, mediator, isRunning), random.nextInt(maxTimeToSpawn - minTimeToSpawn + 1), random.nextInt(maxTimeToSpawn - minTimeToSpawn + 1) + minTimeToSpawn);
            timers.add(timer);
        }
    }

    public static PassengerManager getInstance(int minTimeToSpawn, int maxTimeToSpawn, float maxWeight, Mediator mediator) {
        if (instance == null) instance = new PassengerManager(minTimeToSpawn, maxTimeToSpawn, maxWeight, mediator);
        return instance;
    }

    public void setIsRunning(boolean state)
    {
        isRunning = state;
        PassengerCreator.setIsRunning(state);
    }

    public List<Timer> getTimers() {
        return timers;
    }
}
