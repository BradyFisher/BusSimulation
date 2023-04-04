/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class BusEvent implements IEvent {
    private int curStop;
    private Bus bus;
    private boolean direction;//true for west, and false for east

    public BusEvent(Bus b){
        this.curStop = b.getCurStop();
        this.bus = b;
        this.direction = b.getDirection();
    }
    //Method that changes the direction of the bus if it is at one of the two end stops.
    private void changeDirection(){
        if(curStop == 1){
            direction = false;
            bus.setDirection(false);
        }
        if(curStop == 10){
            direction = true;
            bus.setDirection(true);
        }
    }

    //Method to remove the correct passengers from the bus. Reschedules a bus event and returns true
    // if some passengers are removed otherwise just returns false.
    private boolean removePass(){
        Passenger[] removed = bus.removePassengers(curStop);
        bus.stat.updateWhenRemovingPass(removed, BusSim.agenda.getCurrentTime());
        if(removed.length != 0){
            bus.addTime(2*removed.length);
            BusSim.agenda.add(new BusEvent(bus), 2*removed.length);
            return true;
        }
        return false;
    }

    //Method to add passengers from the correct stop and west queue. Reschedules a bus event and returns true
    // if passengers are added, otherwise just returns false.
    private boolean addPassWest(){
        //checking if there there are passengers in the line and if the bus is full
        bus.stat.updateQueStats(BusSim.stops[curStop-1].getWestbound().length());
        if(BusSim.stops[curStop-1].getWestbound().length() != 0 && !bus.isFull()){
            int passAdded = 0;
            while(!bus.isFull() && BusSim.stops[curStop-1].getWestbound().length() != 0){
                bus.addPassenger(BusSim.stops[curStop-1].getWestbound().remove());
                passAdded++;
            }
            bus.addTime(3*passAdded);
            BusSim.agenda.add(new BusEvent(bus), 3*passAdded);
            return true;
        }
        return false;
    }

    //Method to add passengers from the correct stop and east queue. Reschedules a bus event and returns true
    // if passengers are added, otherwise just returns false.
    private boolean addPassEast(){
        //checking if there there are passengers in the line and if the bus is full
        bus.stat.updateQueStats(BusSim.stops[curStop-1].getEastbound().length());
        if(BusSim.stops[curStop-1].getEastbound().length() != 0 && !bus.isFull()){
            int passAdded = 0;
            while(!bus.isFull() && BusSim.stops[curStop-1].getEastbound().length() != 0){
                bus.addPassenger(BusSim.stops[curStop-1].getEastbound().remove());
                passAdded++;
            }
            bus.addTime(3*passAdded);
            BusSim.agenda.add(new BusEvent(bus), 3* passAdded);
            return true;
        }
        return false;

    }

    //Check if there is a bus either 1 or 2 stops ahead to prevent clumping. Reschedules a bus event and returns true
    //if there is, otherwise just returns false.
    private boolean preventClumpingWest(){
        boolean slowDown = false;//Indicates if the bus will need to slow down
        for(int i =0; bus.getBuses()[i] != null && i < bus.getBuses().length; i++){
            //Determines if there is a bus 1 or 2 stops ahead going in the same direction.
            if((bus.getBuses()[i].getCurStop() == curStop-1 || bus.getBuses()[i].getCurStop() == curStop-2)
                    && (bus.getBuses()[i].getDirection() == direction)){
                slowDown = true;
            }
            //If there are more than 10 buses preventing clumping will not work.
            if(i >= 9){
                slowDown =false;
            }
        }
        if(slowDown){
            BusSim.agenda.add(new BusEvent(bus), 15);
        }
        return slowDown;
    }

    //Check if there is a bus either 1 or 2 stops ahead to prevent clumping. Reschedules a bus event and returns true
    //if there is, otherwise just returns false.
    private boolean preventClumpingEast(){
        //Check if there are buses 1 or 2 stops ahead to prevent clumping
        boolean slowDown = false;
        for(int i =0; bus.getBuses()[i] != null && i < bus.getBuses().length; i++){
            //Determines if there is a bus 1 or 2 stops ahead going in the same direction.
            if((bus.getBuses()[i].getCurStop() == curStop+1 || bus.getBuses()[i].getCurStop() == curStop+2)
                    &&(bus.getBuses()[i].getDirection() == direction)){
                slowDown = true;
            }
            //If there are more than 10 buses preventing clumping will not work.
            if(i >= 9){
                slowDown =false;
            }
        }
        if(slowDown){
            BusSim.agenda.add(new BusEvent(bus), 15);
        }
        return slowDown;
    }

    private boolean noWait(){
        if(bus.getTimeAtStop() < 15){
            BusSim.agenda.add(new BusEvent(bus), 15 - bus.getTimeAtStop());
            bus.addTime(15 - bus.getTimeAtStop());
            return false;
        }
        return true;
    }


    public void run(){
        changeDirection();
        if(!removePass()){
            if(direction){
                if(!addPassWest()){
                    if(!preventClumpingWest()){
                        if(noWait()){
                            //If not removing passengers, adding passengers, or waiting at the stop, move to next stop.
                            bus.resetTime();
                            bus.setCurStop(curStop-1);
                            BusSim.agenda.add(new BusEvent(bus), 180);
                        }
                    }
                }
            }
            else{
                if(!addPassEast()){
                    if(!preventClumpingEast()){
                        if(noWait()){
                            //If not removing passengers, adding passengers, or waiting at the stop, move to next stop.
                            bus.resetTime();
                            bus.setCurStop(curStop+1);
                            BusSim.agenda.add(new BusEvent(bus), 180);
                        }
                    }
                }
            }
        }
    }
}