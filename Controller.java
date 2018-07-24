import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Controller
{
  public static void main(String[] args) throws InterruptedException
  {
    Object startNotifier = new Object();
    Object stepNotifier = new Object();
    
    //create all the cars
    Car[] cars = {new Car(10, 10, 1, 1, 2, Color.BLUE, null, startNotifier, stepNotifier),
    new Car(11, 11, 1, 1, 1, Color.RED, null, startNotifier, stepNotifier),
    new Car(3, 4, 18, 18, 2, Color.ORANGE, null,startNotifier, stepNotifier),
    new Car(12,17,18,18,1, Color.PINK, null, startNotifier, stepNotifier),
    new Car(7, 2, 1, 18, 1, Color.CYAN, null,startNotifier, stepNotifier),
    new Car(9, 12, 1, 18, 3, Color.GREEN, null,startNotifier, stepNotifier),
    new Car(3, 8, 18, 1, 1, Color.MAGENTA, null,startNotifier, stepNotifier),
    new Car(10, 4, 18, 1, 1, Color.YELLOW, null,startNotifier, stepNotifier),
    new Car(11, 10, 1, 18, 2, Color.BLUE, null, startNotifier, stepNotifier),
    new Car(11, 10, 18, 1, 1, Color.RED, null, startNotifier, stepNotifier),
    new Car(7, 4, 1, 1, 2, Color.ORANGE, null,startNotifier, stepNotifier),
    new Car(3,17,18,1,1, Color.PINK, null, startNotifier, stepNotifier),
    new Car(13, 12, 1, 1, 3, Color.GREEN, null,startNotifier, stepNotifier),
    new Car(6, 8, 18, 1, 1, Color.MAGENTA, null,startNotifier, stepNotifier),
    new Car(8, 6, 1, 18, 1, Color.YELLOW, null,startNotifier, stepNotifier),
    new Car(2, 10, 18, 18, 2, Color.RED, null, startNotifier, stepNotifier),
    new Car(10, 2, 18, 1, 1, Color.YELLOW, null, startNotifier, stepNotifier),
    new Car(8, 13, 1, 18, 3, Color.MAGENTA, null, startNotifier, stepNotifier),
    new Car(4, 2, 18, 18, 2, Color.CYAN, null, startNotifier, stepNotifier)
    
    };
    ParkingLot parkingLot = new ParkingLot(cars, startNotifier, stepNotifier);
    parkingLot.RunTurns();
    
    JFrame frame = new JFrame();   
    JPanel panel = new JPanel();
    frame.add(panel);
    
    int row = 20;
    int col = 20;
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 500, 500);
    panel.setLayout(new GridLayout(row, col));
    
    JLabel[][] grid= new JLabel[row][col];
    for (int i = 0; i < row; i++){
      for (int j = 0; j < col; j++){
        if((i + j) % 2 == 0)
        {
          grid[i][j] = new JLabel("", SwingConstants.CENTER);
          grid[i][j].setBackground(Color.red);
        }
        else
        {
          grid[i][j] = new JLabel("", SwingConstants.CENTER);
          grid[i][j].setBackground(Color.orange);
        }
        grid[i][j].setOpaque(true);
        panel.add(grid[i][j]);
      }
    }

    frame.setVisible(true);
    
    getReplay(parkingLot, grid);
  }
  //Animates the replay
  public static void getReplay(ParkingLot parkingLot, JLabel[][] grid) throws InterruptedException{
    ArrayList<Car[][]> replay = parkingLot.getReplay();
    //puts current snapshot into the JFrame
    for(Car[][] snapshot : replay)
    {
      for(int j=0; j<snapshot.length; j++)
      {
        for(int k=0; k<snapshot[0].length; k++)
        {
          if(snapshot[j][k] == null){
              grid[j][k].setBackground(Color.BLACK);
          }
          else{
              grid[j][k].setBackground(snapshot[j][k].getColor());    
          }         
        }
      }
      //set exits in dark gray
      grid[1][1].setBackground(Color.DARK_GRAY);
      grid[1][18].setBackground(Color.DARK_GRAY);
      grid[18][1].setBackground(Color.DARK_GRAY);
      grid[18][18].setBackground(Color.DARK_GRAY);
      
      TimeUnit.SECONDS.sleep(1);
    }
  }
  
  
}
