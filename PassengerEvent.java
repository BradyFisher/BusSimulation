import java.util.Random;

/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class PassengerEvent implements IEvent {
    private static int load = 240;//the average inter-arrival rate
    private int busStop;//The stop where the passenger is created

    //Constructor for a PassengerEvent
    public PassengerEvent(int stop){
        this.busStop = stop;
    }

    public int getStop(){
        return busStop;
    }

    public void run() {
        Random rand = new Random();
        //Array to determine dropOff location for the Passenger, with the more popular stops appearing twice.
        int[] dropOffArray = {1,2,3,4,5,6,7,8,8,9,9,10,10};
        int dropOffStop = dropOffArray[rand.nextInt(13)];
        while(busStop == dropOffStop){
            dropOffStop = dropOffArray[rand.nextInt(13)];
        }

        double[] arrivalIntArray = {.75,.75,.50,.50,.50,.20,.20,.20,.20,0,0,-.20,-.20,-.20,-.20,-.50,-.50,-.50,-.75,-.75};
        double arrivalInt = load + arrivalIntArray[rand.nextInt(20)]*load;

        //Create a Passenger and determine if they are going west or east.
        if(dropOffStop < busStop){
            BusSim.stops[busStop-1].getWestbound().add(new Passenger(busStop,dropOffStop));
        }
        else{
            BusSim.stops[busStop-1].getEastbound().add(new Passenger(busStop,dropOffStop));
        }

        //Reschedule itself, with a higher rate at downtown stops.
        if(busStop == 8 || busStop == 9|| busStop == 10){
            BusSim.agenda.add(new PassengerEvent(busStop), arrivalInt*.66);
        }
        else {
            BusSim.agenda.add(new PassengerEvent(busStop), arrivalInt);
        }
    }
}