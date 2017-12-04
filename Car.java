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
    
    //check where to go
    private int[] nextDestination(Boolean[][] parkingLot){
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
            if(north>0 && !parkingLot[getX()][north]){
                return new int[] {getX(), north};
            }
            
            //check northeast
            else if(north>0 && east <parkingLot.length && !parkingLot[east][north]){
                return new int[] {east, north};
            }
            
            //check northwest
            else if(north>0 && west >= 0 && !parkingLot[west][north]){
                return new int[] {west, north};
            }
            
            
        }
        //south
        else if(yPathLength()<0 && directionType == -1){

            //within the bounds + available space
            //default case: Go straight down
            if(south<parkingLot[0].length && !parkingLot[getX()][south]){
                return new int[] {getX(), south};
            }
            
            //check southeast
            else if(south<parkingLot[0].length && east <parkingLot.length && !parkingLot[east][south]){
                return new int[] {east, south};
            }
            
            //check southwest
            else if(south<parkingLot[0].length && west >= 0 && !parkingLot[west][south]){
                return new int[] {west, south};
            }
        }
        //east
        else if(xPathLength()>0 && directionType == 1){
            //default case go east
            if(east<parkingLot.length && !parkingLot[east][getY()]){
                return new int[] {east, getY()};
            }
            
            //check southeast
            else if(south<parkingLot[0].length && east <parkingLot.length && !parkingLot[east][south]){
                return new int[] {east, south};
            }
            
            //check northeast
            else if(north>=0 && east <parkingLot.length && !parkingLot[east][north]){
                return new int[] {east, north};
            }
        }
        //west
        else if(xPathLength()<0 && directionType == 1){

            //default case go west
            if(west>=0 && !parkingLot[west][getY()]){
                return new int[] {west, getY()};
            }
            
            //check southwest
            else if(south<parkingLot[0].length && west>=0 && !parkingLot[west][south]){
                return new int[] {west, south};
            }
            
            //check northwest
            else if(north>=0 && west>=0 && !parkingLot[west][north]){
                return new int[] {west, north};
            }
        }
        
         //northeast
        else if(yPathLength()>0 && xPathLength()>0 && directionType == 0){
            //default case: northeast
            if(north>0 && east <parkingLot.length && !parkingLot[east][north]){
                return new int[] {east, north};
            }
            //check north
            else if(north>0 && !parkingLot[getX()][north]){
                return new int[] {getX(), north};
            }
            
            //check east
            else if(east<parkingLot.length && !parkingLot[east][getY()]){
                return new int[] {east, getY()};
            }
            
            
        }
         //northwest
        else if(yPathLength()>0 && xPathLength()<0 && directionType == 0){

            //check northwest
            if(north>0 && west >= 0 && !parkingLot[west][north]){
                return new int[] {west, north};
            }
            //check north
            else if(north>0 && !parkingLot[getX()][north]){
                return new int[] {getX(), north};
            }
            //check west
            else if(west>=0 && !parkingLot[west][getY()]){
                return new int[] {west, getY()};
            }
        }
         //southeast
        else if(yPathLength()<0 && xPathLength()>0 && directionType == 0){
            //default check southeast
            if(south<parkingLot[0].length && east <parkingLot.length && !parkingLot[east][south]){
                return new int[] {east, south};
            }
            
            //check south
            else if(south<parkingLot[0].length && !parkingLot[getX()][south]){
                return new int[] {getX(), south};
            }
            //check east
            else if(east<parkingLot.length && !parkingLot[east][getY()]){
                return new int[] {east, getY()};
            }
        }
         //southwest
        else if(yPathLength()<0 && xPathLength()<0 && directionType == 0){
            //check southwest
            if(south<parkingLot[0].length && west >= 0 && !parkingLot[west][south]){
                return new int[] {west, south};
            }
            //check south
            else if(south<parkingLot[0].length && !parkingLot[getX()][south]){
                return new int[] {getX(), south};
            }
            //check west
            else if(west>=0 && !parkingLot[west][getY()]){
                return new int[] {west, getY()};
            }
        }
        
        //if the case doesn't apply for the current direction return current location
        return new int[] {getX(), getY()};
        
    }
    
}
