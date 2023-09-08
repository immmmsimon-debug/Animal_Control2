package com.example.animalcontrol;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.util.ArrayList;
import java.util.List;

public class World {
    Actor World[][];
    Image defaultTile;


    public World(int numRows,int numCols){
        World = new Actor[numRows][numCols];
        try {
            loadTile();
        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }

    }

    public void loadTile() throws FileNotFoundException {
       defaultTile = new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\floorTile.jpg"));
    }

    public void draw(GraphicsContext gc){
        int numOfRow =0;
        int numOfCol =0;
        for(int row = 0; row < World.length; row++){
            numOfRow++;
            numOfCol++;
            for(int col = 0; col< World[row].length; col++){
                gc.drawImage(defaultTile,col*defaultTile.getWidth(),row*defaultTile.getHeight());
                if(World[row][col] != null){
                    World[row][col].draw(gc);
                }
            }
        }
    }

    public boolean isValid(Location loc){
        return loc.getRow() >= 0 && loc.getRow() < World.length && loc.getCol() >= 0 && loc.getCol() < World[(loc.getRow())].length;
    }

    public Actor getNearest(Actor a){
        return null;
    }

   /* private ArrayList<Actor> getAllActorOfType(String className){
        ArrayList<Actor> allActorType = new ArrayList<>();
        Location current = new Location(0,0);

        for(int row=0; row<World.length; row++ ){
            for(int col=0; col<World[row].length; col++){
                if(World[row][col] != null){
                    current.setCol(row);
                    current.setRow(col);
                    if(getActor(current).getName().equals(className)){
                        allActorType.add(getActor(current));
                    }
                }
            }
        }
        return allActorType;
    }

    */
    public void step(){
       for(int row = 0; row < World.length; row++){
           for(int col = 0; col < World[row].length;col++){
               //grab actor
               Actor a = World[row][col];
               //if a is an actor and has not acted
               if(a != null && !a.hasActed()){
                   a.act();//let the actor act
                   a.setHasActed(true); // flag the actor as already act
               }// end if
           }//inner for
       }//outer for
        clearActedFlag();
    }// end method


    public void clearActedFlag (){
        for(int row = 0; row < World.length; row++){
            for(int col = 0; col < World[row].length;col++){
                //grab actor
                Actor a = World[row][col];
                //if a is an actor and has not acted
                if(a != null ){
                    a.setHasActed(false); // flag the actor as already act
                }
            }
        }
    }
    //return an ArrayList of Location that contain other Actors in: North; South; East; West
    public ArrayList<Location> getFourNeighbors(Location loc) {
        ArrayList<Location> neighbors = new ArrayList<>();
        if (isValid(loc.getNorth()) && getActor(loc.getNorth()) != null) {
            neighbors.add(loc.getNorth());
        }
        if (isValid(loc.getSouth()) && getActor(loc.getSouth()) != null) {
            neighbors.add(loc.getSouth());
        }
        if (isValid(loc.getEast()) && getActor(loc.getEast()) != null) {
            neighbors.add(loc.getEast());
        }
        if (isValid(loc.getWest()) && getActor(loc.getWest()) != null) {
            neighbors.add(loc.getWest());
        }
        return neighbors;
    }

    public ArrayList<Location> getEightNeighbors(Location loc){
        ArrayList<Location> allNeighbors = new ArrayList<>();

        for(int row = loc.getRow()-1; row <= loc.getRow()+1; row++){
            for(int col=loc.getCol()-1; col <= loc.getCol()+1; col++){
                Location tempLoc = new Location(row,col);
                if(isValid(tempLoc) && getActor(tempLoc) != null){
                    allNeighbors.add(tempLoc);
                }
            }

        }
        allNeighbors.remove(loc);

        return allNeighbors;
    }
    public Actor getActor(Location loc){
        if(isValid(loc)){
           return World[loc.getRow()][loc.getCol()];
        }
            return null;
    }
   /* public Actor getRandom(String className){
        ArrayList<Actor> actors = getAllActorOfType(className);
        if(actors.size() == 0){
            return null;
        }
        return actors.get((int)(Math.random()+actors.size()));
    }


    */
    public void clearLoc(Location loc){
        if(isValid(loc)){
            Actor a = World[loc.getRow()][loc.getCol()];
            World[loc.getRow()][loc.getCol()] = null;

        }//end if
    }//end clearLoc
    public boolean addActor( Actor a){
        if(World[a.getMyLoc().getRow()][a.getMyLoc().getCol()] != null){
            return false;
        }

        World[a.getMyLoc().getRow()][a.getMyLoc().getCol()] = a;
        return true;
    }
    //return a new location in the given direction from the give loc
    //return null, if there is no valid location in that direction

}
