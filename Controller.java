import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Controller
{
  public static void main(String[] args) throws InterruptedException
  {
    Car[] carz = {new Car(10, 10, 1, 1, 2, Color.BLUE, null),
    new Car(11, 11, 1, 1, 1, Color.RED, null),
    new Car(3, 4, 18, 18, 2, Color.ORANGE, null),
    new Car(7, 2, 1, 18, 1, Color.CYAN, null),
    //new Car(9, 12, 1, 18, 3, Color.GREEN, null),
    //new Car(3, 8, 18, 1, 1, Color.MAGENTA, null),
    //new Car(10, 4, 18, 1, 1, Color.YELLOW, null)
    };
    ParkingLot parkingLot = new ParkingLot(carz);
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
    //grid[0][0].setBackground(Color.red);
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
      grid[1][1].setBackground(Color.DARK_GRAY);
      grid[1][18].setBackground(Color.DARK_GRAY);
      grid[18][1].setBackground(Color.DARK_GRAY);
      grid[18][18].setBackground(Color.DARK_GRAY);
      
      TimeUnit.SECONDS.sleep(1);
    }
  }
  
  
}