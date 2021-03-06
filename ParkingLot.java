import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ParkingLot
{
  //sets a defualt value for x and y dimensions, which can be changed later
  private final int xLength = 20;
  private final int yLength = 20;
  private final int MAX_STEPS = 4;
  private final Object startNotifier;
  private final Object stepNotifier;
  
  private int countDone;
  
  //keeps track of the positions of every car
  private Car[][] myLotArray;
  //keeps track of if the space is being looked at
  private boolean[][] isLocked;
  //keeps track of the replays
  private ArrayList<Car[][]> replay = new ArrayList<>();
  private Car[] myCars;
  
  public ParkingLot(Car[] carList, Object startNotifier, Object stepNotifier)
  {
    this.startNotifier = startNotifier;
    this.stepNotifier = stepNotifier;
    
    countDone = 0;
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
    
    myLotArray[6][6] = new Car(Color.GRAY, this);
    myLotArray[12][12] = new Car(Color.GRAY, this);
    myLotArray[6][12] = new Car(Color.GRAY, this);
    myLotArray[12][6] = new Car(Color.GRAY, this);    
    
    for(int x = 0; x < xLength; x++)
    {
      for(int y = 0; y < yLength; y++)
      {
        isLocked[x][y] = false;
      }
    }
    //sets the cars 
    for(Car cars: carList){
        cars.setLot(this);
        myLotArray[cars.getX()][cars.getY()] = cars;
    }

    myCars = carList;

    for(Car car : myCars)
    {
        car.start();
    }
  }
  
  //starts to run the simulation
  public void RunTurns() throws InterruptedException
  {
    int runCounter = 0;
    
    //iterates until every car has reached its destination
    while(countDone < myCars.length && runCounter < 50)
    {
      synchronized(startNotifier)
      {
        startNotifier.notifyAll();
      }
      //iterates for all cars to trigger step flag, as many times as the max number of steps possible
      for(int x = 0; x < MAX_STEPS; x++)
      {

        synchronized(stepNotifier)
        {
         stepNotifier.notifyAll(); 
        }
       TimeUnit.MILLISECONDS.sleep(20);
      }
      //saves current snapshot into the array
      TimeUnit.MILLISECONDS.sleep(20);
      replay.add(snapshot(myLotArray));
      runCounter++;
      System.out.println(runCounter);
    }    

  }
  
  //saves each snapshot as a new array, which is then passed to the arraylist
  public Car[][] snapshot(Car[][] car){
      Car[][] carArray = new Car[xLength][yLength];
      for(int i = 0; i<carArray.length; i++)
      {
          for(int j = 0; j<carArray[0].length; j++)
          {
              carArray[i][j] = car[i][j];
          }
      }
      return carArray;
  }
  
  
  public ArrayList<Car[][]> getReplay()
  {
    return replay;
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
