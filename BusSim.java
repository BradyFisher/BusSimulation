/**
 * Brady Fisher
 * ID# 5235642
 * x500: fishe835
 */
public class BusSim {
    public static PQ agenda = new PQ();
    public static Stop[] stops = new Stop[]{new Stop(1), new Stop(2),new Stop(3), new Stop(4),
                new Stop(5), new Stop(6),new Stop(7), new Stop(8), new Stop(9), new Stop(10)};

    public static void main(String[] args){
        //Create the buses anywhere from 1-18
        Bus bus1 = new Bus(true, 1, false);
        Bus bus2 = new Bus(true, 4, true);
        Bus bus3 = new Bus(true,6, false);
        Bus bus4 = new Bus(true,9,true);
//        Bus bus5 = new Bus(true,10,true);
//        Bus bus6 = new Bus(true,8,true);
//        Bus bus7 = new Bus(true,6,true);
//        Bus bus8 = new Bus(true,3,true);
//        Bus bus9 = new Bus(true,9,false);
//        Bus bus10 = new Bus(true,10,false);

        //Instantiate PassengerEvents for each stop
        agenda.add(new PassengerEvent(1),2);
        agenda.add(new PassengerEvent(2),2);
        agenda.add(new PassengerEvent(3),2);
        agenda.add(new PassengerEvent(4),2);
        agenda.add(new PassengerEvent(5),2);
        agenda.add(new PassengerEvent(6),2);
        agenda.add(new PassengerEvent(7),2);
        agenda.add(new PassengerEvent(8),2);
        agenda.add(new PassengerEvent(9),2);
        agenda.add(new PassengerEvent(10),2);

        //Instantiate the Bus Events for each bus
        agenda.add(new BusEvent(bus1), 3);
        agenda.add(new BusEvent(bus2), 3);
        agenda.add(new BusEvent(bus3), 3);
        agenda.add(new BusEvent(bus4), 3);
//        agenda.add(new BusEvent(bus5), 3);
//        agenda.add(new BusEvent(bus6), 3);
//        agenda.add(new BusEvent(bus7), 3);
//       agenda.add(new BusEvent(bus8), 3);
//        agenda.add(new BusEvent(bus9), 3);
//        agenda.add(new BusEvent(bus10), 3);


        while (agenda.getCurrentTime() <= 7200) {
            agenda.remove().run();
        }
        for(int i =0; i < stops.length;i++){
            bus1.stat.updateQueStats(stops[i].getEastbound().length());
            bus1.stat.updateQueStats(stops[i].getEastbound().length());
        }
        bus1.stat.displayStatistics();
    }
}
