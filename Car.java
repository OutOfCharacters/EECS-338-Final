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
    
    private boolean startFlag = false;
    private boolean stepFlag = false;
   
    
    public Car(int x, int y, int speed, Color colour, ParkingLot newLot)
    {
        xDestination = x;
        yDestination = y;
        numberOfSteps = speed;
        carColor = colour;
        myLot = newLot;
    }
    
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
        //waits for input from parkingLot signaling a new turn
        while(!startFlag)
        {
        }
        startFlag = false;

        //car moves as many steps as it is allocated
        for(int n = 0; n < numberOfSteps; n++)
        {
          
          //checks each space in the loop
          int[][] spacesToCheck = nextDestination();
          for(int i = 0; i < 3; i++)
          {
            //waits for signal from ParkingLot for a new step
            while(!stepFlag)
            {
            }
            stepFlag = false;
            
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
              }
              myLot.setLock(spacesToCheck[i][0], spacesToCheck[i][1], false);
            }
          }
        }
      }
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
        return this.xDestination;
    }
    
    public int getY(){
        return this.yDestination;
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
        int east = getX()+1;
        int west = getX()-1;
        
        //north
        if(yPathLength()>0 && directionType == -1){
            //within the bounds + available space
            //default case: Go straight up
            return new int[][] {{getX(), north}, {east, north}, {west, north}};
        }
        //south
        else if(yPathLength()<0 && directionType == -1){
            return new int[][] {{getX(), south},{east, south}, {west, south}};           
        }
        //east
        else if(xPathLength()>0 && directionType == 1){
            //default case go east
            return new int[][]{{east,getY()},{east,south},{east,north}};
        }
        //west
        else if(xPathLength()<0 && directionType == 1){
            //default case go west
            return new int[][] {{west, getY()},{west, south}, {west, north}};
        }      
         //northeast
        else if(yPathLength()>0 && xPathLength()>0 && directionType == 0){
            //default case: northeast
            return new int[][] {{east, north}, {getX(), north}, {east, getY()}};                     
        }
         //northwest
        else if(yPathLength()>0 && xPathLength()<0 && directionType == 0){
            //check northwest
            return new int[][]{{west, north},{getX(), north}, {west, getY()}};
        }
         //southeast
        else if(yPathLength()<0 && xPathLength()>0 && directionType == 0){
            //default check southeast
            return new int[][]{{east, south},{getX(), south}, {east, getY()}};
        }
         //southwest
         //else if(yPathLength()<0 && xPathLength()<0 && directionType == 0)
        else{
            //check southwest
            return new int[][]{{west, south},{getX(), south},{west, getY()}};
        }              
    }
    
}