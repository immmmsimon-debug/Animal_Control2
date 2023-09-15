package com.example.animalcontrol;

import javafx.scene.image.Image;

public class Location {
    private int row;
    private int col;

    private Image image;

    public static final int North=0, NorthEast=1,East=2,
                            SouthEast=3,South=4,SouthWest=5,
                            West=6,NorthWest=7;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Location getLocInDirection( int dir){

        switch(dir){
            case North:
                return getNorth();
            case South:
                return getSouth();
            case West:
                return getWest();
            case East:
                return getEast();
            case NorthWest:
                return getNorthWest();
            case NorthEast:
                return getNorthEast();
            case SouthWest:
                return getSouthWest();
            case SouthEast:
                return getSouthEast();
            default:
                return null;
        }


    }
    public Location getNorth(){
        return new Location(row-1, col);
    }
    public Location getSouth(){
        return new Location(row+1,col);
    }

    public Location getWest(){
        return new Location(row, col-1);
    }

    public Location getEast(){
        return new Location(row, col+1);
    }
    public Location getNorthEast() {
        return new Location(row - 1, col + 1);
    }
    public Location getSouthEast(){
        return new Location(row+1,col+1);
    }
    public Location getSouthWest(){
        return new Location(row+1, col-1);
    }
    public Location getNorthWest(){
        return new Location(row-1, col-1);
    }

    public boolean equals(Location other){
        return row == other.row && col== other.col;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    //eightWay -> true, method return direction using 8 naughticol
    //         -> false, method returns direction using 4 naughticol

    public int getDirectionToLoc(Location destination, boolean eightWay){
        Location myLoc = new Location(row, col);
        if(eightWay == false){
            return getDirectionFour(destination);
        }
        else{
            return getDirectionEight(destination);
        }
    }

    public int getDirectionFour(Location destination) {
        Location myLoc = new Location(row, col);
        int dir = 0;

        int rowDis = destination.getRow() - myLoc.getRow();
        int colDis = destination.getCol() - myLoc.getCol();

        System.out.println("Distance from row: " + rowDis);
        System.out.println("Distance from col: " + colDis);

        if(Math.abs(rowDis)>Math.abs(colDis)) {
            if (rowDis > 0) {
                dir = 4;
            }else
                dir=0;
        }else {
            if(colDis>0){
                dir=2;
            }else
                dir=6;
        }

        if(rowDis==0){
            if(colDis>0){
                dir=2;
            }else
                dir=6;
        }
        if(colDis==0){
            if(rowDis>0){
                dir=4;
            }else
                dir=0;
        }
        return dir;
    }
/*
        if (rowDis != 0) {
            if (rowDis > 0) {
                dir = South;
            } else if (rowDis < 0) {
                dir = North;
            }
        } else if (colDis != 0) {
            if (colDis > 0) {
                dir = East;
            } else if (colDis < 0) {
                dir = West;
            }
        }
        return dir;
    }

 */


    public int getDirectionEight(Location destination) {
        Location myLoc = new Location(row, col);
        int dir =0;

        int rowDis = destination.getRow() - myLoc.getRow();
        int colDis = destination.getCol() - myLoc.getCol();
        double row = 0;
        double col = 0;
        System.out.println("Distance from row: " + rowDis);
        System.out.println("Distance from col: " + colDis);

         if(rowDis!=0 && colDis!=0){
             if(rowDis<0 && colDis>0){
                 dir=1;
             }
             else if(rowDis>0 && colDis >0){
                 dir=3;
             }
             else if(rowDis>0 && colDis<0){
                 dir=5;
             }
             else if(rowDis<0 && colDis<0){
                 dir=7;
             }
         }else {
            dir=getDirectionFour(destination);
         }

        return dir;
    }



/*
        if(rowDis == colDis && colDis != 0 && rowDis !=0){
            //when the destination is to the right of the object
            if(destination.getCol() > myLoc.getCol()){
                //when the target is below the object
                if(destination.getRow() > myLoc.getRow()){
                    dir = SouthEast;
                }
                else {//when target is above the object
                    dir = NorthEast;
                }
            }
            else {
                if (destination.getRow() > myLoc.getRow()) {
                    return SouthWest;
                } else {//when target is above the object
                    dir = NorthWest;
                }
            }

        }else{
            getDirectionFour(destination);
        }
        return dir;

 */





    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
