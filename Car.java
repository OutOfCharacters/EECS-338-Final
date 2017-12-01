/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car;

/**
 *
 * @author super
 */
public class Car {

    private final int xDestination;
    private final int yDestination;
    private final int numberOfSteps;
    private final String carColor;
    private int currentX;
    private int currentY;
    private boolean moveX;
    private boolean moveY;
    private boolean moveDiag;
    public Car(int x, int y, int speed, String colour){
        xDestination = x;
        yDestination = y;
        numberOfSteps = speed;
        carColor = colour;
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
    private int obstacleDetection(Car[][] parkingLot){
        //current location check 5 directions 
        int directionType = decideShortestPath();
        //north
        if(yPathLength()>0 && directionType == -1){
            
        }
        //south
        else if(yPathLength()<0 && directionType == -1){
            
        }
        //east
        else if(xPathLength()>0 && directionType == 1){
            
        }
        //west
        else if(xPathLength()<0 && directionType == 1){
            
        }
        return 0;
    }
    
}
