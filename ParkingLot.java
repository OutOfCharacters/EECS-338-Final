import java.awt.Color;

public class ParkingLot
{
   //sets a defualt value for x and y dimensions, which can be changed later
   private final int xLength = 20;
   private final int yLength = 20;
   
   private Car[][] myLotArray;
   private boolean[][] isLocked;
   
   public void setup()
   {
     myLotArray = new Car[xLength][yLength];
     isLocked = new boolean[xLength][yLength];
     for(int i = 0; i < xLength; i++)
     {
       //fills lot with a border of empty cars
       myLotArray[i][0] = new Car(Color.GRAY);
       myLotArray[i][yLength - 1] = new Car(Color.GRAY);
     }
     
     for(int i = 0; i < yLength; i++)
     {
       myLotArray[0][i] = new Car(Color.GRAY);
       myLotArray[xLength - 1][i] = new Car(Color.GRAY);
     }
     for(int x = 0; x < xLength; x++)
     {
       for(int y = 0; y < yLength; y++)
       {
         isLocked[x][y] = false;
       }
     }
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
}