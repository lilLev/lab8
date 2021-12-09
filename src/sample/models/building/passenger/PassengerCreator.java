package sample.models.building.passenger;

import sample.models.building.Floor;
import sample.models.building.Mediator;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class PassengerCreator extends TimerTask {
    private final Floor sourceFloor;
    private final List<Floor> floors;
    private final float maxWeight;
    private final Mediator mediator;
    private static boolean isRunning = true;

    public PassengerCreator(Floor floor, List<Floor> floors, float maxWeight, Mediator mediator, boolean state) {
        this.sourceFloor = floor;
        this.floors = floors;
        this.maxWeight = maxWeight;
        this.mediator = mediator;
        isRunning = state;
    }

    @Override
    public void run() {
        if(isRunning) {
            Random random = new Random(LocalTime.now().toNanoOfDay());
            Floor destFloor = floors.get(random.nextInt(floors.size() - 1));
            while (destFloor == sourceFloor) destFloor = floors.get(random.nextInt(floors.size() - 1));
            if(isRunning)
            sourceFloor.addPassengerToQueue(new Passenger(sourceFloor, destFloor, random.nextInt(60) + 40 * random.nextFloat(), mediator));
        }
    }

    public static void setIsRunning(boolean state)
    {
        isRunning = state;
    }
}
