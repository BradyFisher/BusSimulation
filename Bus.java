/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class Bus {

    private int curStop;
    private boolean direction;
    private Passenger[] array;
    private int timeAtStop;
    private int mpg;
    private static int numberOfBuses;//The total number of buses created.
    public static Bus[] buses = new Bus[18];//An array of the buses
    public Statistics stat = new Statistics();

    //Constructs a bus, if typeNormal is true bus holds 40 passengers, otherwise it will hold 60.
    public Bus(boolean typeNormal, int stop, boolean dir){
        if(typeNormal){
            array= new Passenger[40];
            mpg = 6;
        }
        else{
            array= new Passenger[60];
            mpg = 4;
        }
        direction = dir;
        curStop = stop;
        //Add this bus to the array.
        buses[numberOfBuses]= this;
        numberOfBuses = numberOfBuses +1;
    }

    //Reset the time at the stop to 0, when it arrives at a new stop.
    public void resetTime(){
        timeAtStop = 0;
    }
    public void addTime(int seconds){
        timeAtStop = timeAtStop + seconds;
    }
    public int getTimeAtStop(){
        return timeAtStop;
    }
    public Bus[] getBuses(){
        return buses;
    }
    public int getCurStop(){
        return curStop;
    }
    public boolean getDirection(){
        return direction;
    }
    public void setCurStop(int currentStop){
        curStop = currentStop;
    }
    public void setDirection(boolean dir){
        direction = dir;
    }
    public int getMpg(){
        return mpg;
    }

    //Returns the current Occupancy of the Bus
    public int getCurOcc(){
        int occ = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                occ = occ + 1;
            }
        }
        return occ;
    }

    //Add passengers to the bus.
    public void addPassenger(Passenger p) {
        p.setRideTime(BusSim.agenda.getCurrentTime());
        boolean added = false;
        for(int i = 0; !added && i < array.length; i++) {
            if(array[i] == null){
                array[i] = p;
                added = true;
            }
        }
    }

    //Removes the correct passengers from the bus. Returns an array of all the removed passengers.
    public Passenger[] removePassengers(int stop){
        //Determines the size of the array(how many passengers will be removed).
        int removePassCount = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null && array[i].getDropoffStop() == stop ){
                removePassCount++;
            }
        }

        Passenger[] removedPass = new Passenger[removePassCount];
        int removedPassCount = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null && array[i].getDropoffStop() == stop){
                removedPass[removedPassCount] = array[i];
                array[i] = null;
                removedPassCount++;
            }
        }
        return removedPass;
    }

    //Determines if the bus is full.
    public boolean isFull() {
        boolean full = true;
        for(int i = 0; full && i < array.length; i++){
            if(array[i] == null){
                full = false;
            }
        }
        return full;
    }
}