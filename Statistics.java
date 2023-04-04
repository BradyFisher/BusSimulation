/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class Statistics {
    //how full each bus is
    private static int maxQueLength;//Max queue length at the stops
    private static double totalTime;//Total time for all Passengers
    private static double totalRideTime;//Total ride time for all Passengers
    private static double totalWaitTime;//Total wait time for all Passengers
    private static double maxTimeAtStop;//Maximum time a Passenger spent at a stop
    private double aveOccupancy;//Total Occupancy when checked, used to calculate the average Occupancy of the bus.
    private int count;//Times checked the occupancy
    private static int numPassengers;//Number of passengers dropped off.
    private static int numBuses;


    public Statistics(){
        numBuses = numBuses + 1;
    }

    //Checks if any of the lines are larger than the current max.
    public void updateQueStats(int qLength){
        if(qLength > maxQueLength){
            maxQueLength = qLength;
        }
    }

    //Updates the times from the removed passengers, and the occupancy for the bus.
    public void updateWhenRemovingPass(Passenger[] passengers, double time){
        for(int i = 0; i<passengers.length; i++){
            totalTime = totalTime + (time - passengers[i].getArrivalTime());
            totalRideTime = totalRideTime + (time - passengers[i].getRideTime());
            totalWaitTime = totalWaitTime + (passengers[i].getRideTime() - passengers[i].getArrivalTime());
            numPassengers = numPassengers + 1;
            if((passengers[i].getRideTime() - passengers[i].getArrivalTime()) > maxTimeAtStop){
                maxTimeAtStop = (passengers[i].getRideTime() - passengers[i].getArrivalTime());
            }
        }
        for(int j = 0; j< numBuses; j++){
            Bus.buses[j].stat.aveOccupancy = Bus.buses[j].stat.aveOccupancy + Bus.buses[j].getCurOcc();
            Bus.buses[j].stat.count = Bus.buses[j].stat.count + 1;
        }


    }

    public void displayStatistics(){
        System.out.println("Statistics for the Simulation");
        System.out.println("Simulation Time: " + BusSim.agenda.getCurrentTime());
        System.out.println("Number of Passengers Dropped Off: " + numPassengers);
        System.out.println("Average Travel Time: " + totalTime/numPassengers);
        System.out.println("Average Ride Time: " + totalRideTime/numPassengers);
        System.out.println("Average Wait Time: " + totalWaitTime/numPassengers);
        System.out.println("Maximum time waiting at a stop: " + maxTimeAtStop);
        double totalPMPG = 0;
        for(int i = 0; i < numBuses; i++){
            totalPMPG = totalPMPG + ((Bus.buses[i].stat.aveOccupancy/Bus.buses[i].stat.count) * Bus.buses[i].getMpg());
            System.out.println("Passenger Miles Per Gallon(PMPG) for Bus " + (i+1) + ": " + ((Bus.buses[i].stat.aveOccupancy/Bus.buses[i].stat.count) * Bus.buses[i].getMpg()));
            System.out.println("Average Occupancy for Bus " + (i+1) + ": " + Bus.buses[i].stat.aveOccupancy/Bus.buses[i].stat.count);
        }
        System.out.println("Average Passenger Miles Per Gallon(PMPG) across all buses " + totalPMPG/numBuses);
        System.out.println("Maximum Line Length at a stop: " + maxQueLength);
    }


}
