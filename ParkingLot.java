import java.awt.Color;

public class ParkingLot
{
   //sets a defualt value for x and y dimensions, which can be changed later
   private final int xLength = 20;
   private final int yLength = 20;
   private final int MAX_STEPS = 5;
   
   private int countDone;
   
   private Car[][] myLotArray;
   private boolean[][] isLocked;
   private Car[][][] replay;
   private Car[] myCars;
   
   public void ParkingLot(int turns)
   {
     countDone = 0;
     replay = new Car[turns][xLength][yLength];
     myLotArray = new Car[xLength][yLength];
     isLocked = new boolean[xLength][yLength];
     for(int i = 0; i < xLength; i++)
     {
       //fills lot with a border of empty cars
       myLotArray[i][0] = new Car(Color.GRAY, this);
       myLotArray[i][yLength - 1] = new Car(Color.GRAY, this);
     }
     
     for(int i = 0; i < yLength; i++)
     {
       myLotArray[0][i] = new Car(Color.GRAY, this);
       myLotArray[xLength - 1][i] = new Car(Color.GRAY, this);
     }
     
     myLotArray[10][10] = new Car(10, 10, 1, Color.BLUE, this);
     
     for(int x = 0; x < xLength; x++)
     {
       for(int y = 0; y < yLength; y++)
       {
         isLocked[x][y] = false;
       }
     }
   }
   
   //starts to run the simulation
   public void RunTurns()
   {
     int runCounter = 0;
     //iterates until every car has reached its destination
     while(countDone < myCars.length)
     {
      //iterates for all cars to trigger turn 
      for(int x = 0; x < myCars.length; x++)
      {
        myCars[x].triggerStartFlag();
      }
      
      //iterates for all cars to trigger step flag, as many times as the max number of steps possible
      for(int x = 0; x < MAX_STEPS; x++)
      {
       for(int y = 0; y < myCars.length; x++)
       {
        myCars[x].triggerStepFlag();
       }
      }
      replay[runCounter] = myLotArray;
     }    
   }
   
   public Car[][][] getReplay(){
    return replay;
   }
   
   //adds a snapshot of the car array to the replay
   public void addToReplay(int turn){
     replay[turn] = myLotArray;
   }
   
   public boolean isOccupied(int x, int y)
   {
    if (myLotArray[x][y] == null)
      return false;
    else
      return true;
   }
   
   public boolean isLocked(int x, int y)
   {
    return isLocked[x][y]; 
   }
   
   public void setPosition(int x, int y, Car car)
   {
    myLotArray[x][y] = car; 
   }
   
   public void setLock(int x, int y, boolean bool)
   {
    isLocked[x][y] = bool; 
   }
   
   public void increment()
   {
    countDone++; 
   }
}