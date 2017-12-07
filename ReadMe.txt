12/3/2017 - worked on Car class.  The nextDestination() method should be
retooled to return three spaces to look at, which will then be evaluated
for whether or not they are locked, and then empty.  

The parkinglot will have borders on the edges with destinations embedded in them, 
so arrayOutOfBounds exceptions will not be something the Car needs to check for.

Jonathan added a graphical display to the Controller class, which is a JFrame with
a grid of JLabels that can be altered with color and text to represent the ParkingLot
with Cars in it.

Goals: a replay feature of a 3d array storing the parkingLot array after every turn
is needed, which can be displayed via the graphical display in Controller.

Car class should be implemented as thread, either by extending the thread class
or implementing the runnable interface.


12/6/2017 - worked on Car class and ParkingLot class.  Overwrote the Run() method in
car in accordance with the extends Thread requirement.  Car now uses logic to determine
which space to go to, and locks the space before deciding whether or not to move.

ParkingLot now triggers flags in order to permit Cars from going to the next turn via
the method RunTurns().  THIS METHOD STILL NEEDS TO SAVE THE ARRAY FOR REPLAYABILITY.

Replay features implemented in Controller, making use of the previously made gui.

Goals:  Making something to declare the car threads and putting them in parkinglot is
now necessary.  Implementing saving the snapshot of each parkinglot to the replay is 
and important thing to remember.
