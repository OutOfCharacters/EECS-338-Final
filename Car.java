import java.awt.Color;

public class Car extends Thread {
  
  private final int xDestination;
  private final int yDestination;
  private final int numberOfSteps;
  private final Color carColor;
  private ParkingLot myLot;
  private int currentX;
  private int currentY;
  private boolean moveX;
  private boolean moveY;
  private boolean moveDiag;
  
  private Object startNotifier;
  private Object stepNotifier;
  
  private boolean startFlag = false;
  private boolean stepFlag = false;
  
  //creates a car that moves
  public Car(int x, int y, int xDest, int yDest, int speed, Color colour, ParkingLot newLot, Object startNotifier, Object stepNotifier)
  {
    currentY = y;
    currentX = x;
    xDestination = xDest;
    yDestination = yDest;
    numberOfSteps = speed;
    carColor = colour;
    myLot = newLot;
    this.startNotifier = startNotifier;
    this.stepNotifier = stepNotifier;
  }
  
  //creates a car that just occupies the border
  public Car(Color colour, ParkingLot newLot)
  {
    xDestination = 0;
    yDestination = 0;
    numberOfSteps = 0;
    carColor = colour;
    myLot = newLot;
  }
  
  //this method is called when the thread is ran, overides the thread class
  public void run()
  {
    
    //stops when car has reached it's destination, car will then be deleted when it's reference is deleted by parkingLot
    while(currentX != xDestination || currentY != yDestination)
    {
      synchronized(startNotifier)
      {
        try
        {
        startNotifier.wait();
        }
        catch(InterruptedException E)
        {
        }
      }
      
      
      //car moves as many steps as it is allocated
      for(int n = 0; n < numberOfSteps && (xDestination != currentX && yDestination != currentY); n++)
      {
        synchronized(stepNotifier)
        {
          try
          {
         stepNotifier.wait(); 
          }
          catch(InterruptedException E)
          {
          }
        }
        //checks each space in the loop
        int[][] spacesToCheck = nextDestination();
        //checks to see if the car has moved
        boolean moveFlag = false;
        for(int i = 0; i < 3 && !moveFlag; i++)
        {
          //checks if space is currently locked
          if(!myLot.isLocked(spacesToCheck[i][0], spacesToCheck[i][1]))
          {
            
            //locks current space being looked at, checks if it's occupied.  If yes, put car in space and delete old car reference.
            myLot.setLock(spacesToCheck[i][0], spacesToCheck[i][1], true);
            if(!myLot.isOccupied(spacesToCheck[i][0], spacesToCheck[i][1]))
            {
              myLot.setPosition(spacesToCheck[i][0], spacesToCheck[i][1], this);
              myLot.setPosition(currentX, currentY, null);
              currentX = spacesToCheck[i][0];
              currentY = spacesToCheck[i][1];
              moveFlag = true;             
            }
            myLot.setLock(spacesToCheck[i][0], spacesToCheck[i][1], false);
          }
        }
      }
    }
    System.out.println("deleted the " + carColor.toString() + " car");
    myLot.setPosition(currentX, currentY, null);
    myLot.increment();
  }
  
  public Color getColor(){
    return carColor;
  }
  public void triggerStartFlag()
  {
    startFlag = true;
  }
  
  public void triggerStepFlag()
  {
    stepFlag = true;
  }
  
  public int getX(){
    return this.currentX;
  }
  
  public int getY(){
    return this.currentY;
  }
  
  public int getSteps(){
    return this.numberOfSteps;
  }
  
  public void setCurrentSpace(int x, int y){
    currentX = x;
    currentY = y;
  }
  
  private int xPathLength(){
    return currentX - xDestination;
  }
  
  private int yPathLength(){
    return currentY - yDestination;
  }
  //helps to decide a general direction
  private int decideShortestPath(){
    int xLength = Math.abs(xPathLength());
    int yLength = Math.abs(yPathLength());
    int difference = xLength - yLength;
    //move in the x direction
    if(difference>0){
      return 1;
    }
    //move in the y direction
    else if(difference<0){
      return -1;
    }
    //diagonal case
    else{
      return 0;
    }
  }
  
  //check where to go
  private int[][] nextDestination(){
    //current location check 3 directions 
    int directionType = decideShortestPath();
    int north = getY()-1;
    int south = getY()+1;
    int east = getX()-1;
    int west = getX()+1;
    
    //north
    if(yPathLength() > 0 && directionType == -1)
    {
      //within the bounds + available space
      //default case: Go straight up      
      return new int[][] {{getX(), north}, {east, north}, {west, north}};
    }
    
    //south
    else if(yPathLength() < 0 && directionType == -1)
    { 
      return new int[][] {{getX(), south},{east, south}, {west, south}};           
    }
            
    //west
    else if(xPathLength() > 0 && directionType == 1)
    {     
      return new int[][]{{east,getY()},{east,south},{east,north}};
    }
            
    //east
    else if(xPathLength() < 0 && directionType == 1)
    {
      return new int[][] {{west, getY()},{west, south}, {west, north}};
    }     
            
    //northeast
    else if(yPathLength() > 0 && xPathLength() > 0 && directionType == 0)
    {
      return new int[][] {{east, north}, {getX(), north}, {east, getY()}};                     
    }
            
    //northwest
    else if(yPathLength() > 0 && xPathLength() < 0 && directionType == 0)
    {  
      return new int[][]{{west, north},{getX(), north}, {west, getY()}};
    }
            
    //southeast
    else if(yPathLength() < 0 && xPathLength() > 0 && directionType == 0)
    {   
      return new int[][]{{east, south},{getX(), south}, {east, getY()}};
    }
            
    //southwest
    else
    {   
      return new int[][]{{west, south},{getX(), south},{west, getY()}};
    }              
  }
  
  //setter for the parkingLot
  public void setLot(ParkingLot parkingLot)
  {
      this.myLot = parkingLot;
  }
  
}
