# BusSimulation
A project where I use a simulation of Buses and Bus Stops to help a fake bus company decide how to best invest in their bus.


 There are a total of 13 classes need for this project. To run the project create anywhere from 1 to 18 buses in the BusSim class using the constructor, and make sure each bus is either at a different stop or heading a different direction. Also in the constructor you can specify what type(normal or extended) each bus where true is for normal, and false is for extended. Then you need to instantiate a bus event for each bus in the BusSim class. You can also change the load or average inter-arrival rate of passengers by manually changing the integer for the load variable in the PassengerEvent class. In the main method of the BusSim class you can also change the length of the simulation and run the main method to observe the data. 


In this project I borrowed a few classes from the lecture examples, and left a comment at the top of those classes indicating this.


 For this project we are simulating Buses, so there are obviously passengers. These passengers are placed into queues representing lines at each stop, and arrays in the Bus class. There are also two event class(BusEvent and PassengerEvent) that inherits from the IEvent class, meaning they have a run() method. These events are scheduled in the agenda of the BusSim class and run in order.
 
 
For this project a priority queue was used to represent the agenda, since it allows us to keep track of time, and properly schedule events. The stops were contained in array in the BusSim class so each stop could easily be accessed by its index. These stops also contained two queues of passengers that represented a real first come, first serve line. The buses were contained in an array in the Bus class to allow statistics to be taken on each created bus, and allow each bus to look at the other buses when preventing clumping. These buses also contain an array of passengers that, when they had null spots this indicated an open spot on the bus. The Statistics class keeps track of statistics like the total time for all passengers traveling, and the average occupancy of each bus. This average occupancy is updated every time a bus leaves a stop, since the occupancy we are interested in is when the bus is moving from one stop to another.

The only bugs or issues that can occur with this project would be if the programmer created buses that they did not instantiate in the BusSim class, or created buses that were at the same stop in the same direction for the middle 8 stops, or created two or more buses at stop 1 or 10.
