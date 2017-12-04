import javax.swing.*;
import java.awt.*;

class Controller
{
  public static void main(String[] args)
  {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setup();
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
            grid[i][j] = new JLabel("kevin", SwingConstants.CENTER);
            grid[i][j].setBackground(Color.red);
          }
          else
          {
            grid[i][j] = new JLabel("chang", SwingConstants.CENTER);
            grid[i][j].setBackground(Color.orange);
          }
            grid[i][j].setOpaque(true);
            panel.add(grid[i][j]);
        }
    }
    //grid[0][0].setBackground(Color.red);
    frame.setVisible(true);
  }
}