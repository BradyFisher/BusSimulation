/**
 * Borrowed and slightly modified from the posted example of Lab 10.
 *
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class Passenger {

    private int pickupStop;// The stop that the passenger gets picked up at
    private int dropoffStop;// The stop that the passenger gets off the bus
    private double arrivalTime;//Time passenger arrives at stop.
    private double rideTime;//Time passenger gets on the bus.
    private boolean direction;//Direction passenger is heading: True for West, False for East


    public int getPickupStop(){
        return pickupStop;
    }

    public int getDropoffStop(){
        return dropoffStop;
    }

    public double getArrivalTime(){
        return arrivalTime;
    }
    public double getRideTime(){
        return rideTime;
    }
    public void setRideTime(double time){
        rideTime = time;
    }

    //Constructs a passenger with a given pickup and dropoff stop.
    public Passenger(int pickupStop, int dropoffStop){
        this.pickupStop = pickupStop;
        this.dropoffStop = dropoffStop;
        this.arrivalTime = BusSim.agenda.getCurrentTime();
        direction = (pickupStop > dropoffStop);
    }
}
