package com.example.animalcontrol;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class World {
    Actor World[][];
    Image defaultTile;
    ArrayList<Actor> masterArray = new ArrayList<>();


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

    public Actor getNearest(Actor a, String className){
        ArrayList<Actor> avaliableActor = getActortype(className);
        ArrayList<Location> nearestActor = getAllActorLoc(className);
        double minDis = 10000;
        double row = 0; double col = 0;
        double myRow = a.getMyLoc().getRow(); double myCol = a.getMyLoc().getCol();
        double disBeforeSquareRoot=0; double disAfterSquareRoot = 0;
        int where = 0;


        for(int i=0; i < nearestActor.size(); i++){
            row = nearestActor.get(i).getRow();
            col = nearestActor.get(i).getCol();
            disBeforeSquareRoot = Math.pow(myRow-row,2)+Math.pow(myCol-col,2);
            disAfterSquareRoot = Math.sqrt(disBeforeSquareRoot);



            if(disAfterSquareRoot<minDis) {
               minDis = disAfterSquareRoot;;
               where = i;
            }
        }


        return avaliableActor.get(where);
    }




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
    public ArrayList<Actor> getActortype(String name){
        ArrayList<Actor> ans = new ArrayList<>();

        for(int i = 0; i<masterArray.size(); i++){
            String n = masterArray.get(i).getClass().toString();
            if(n.contains(name)){
                ans.add(masterArray.get(i));

            }
        }
        return ans;
    }

    public ArrayList<Location> getAllActorLoc(String name){
        ArrayList<Actor> ans = new ArrayList<>();
        ArrayList<Location> loc = new ArrayList<>();
        ans = getActortype(name);
        for (int i=0; i< ans.size(); i++){
            loc.add(ans.get(i).getMyLoc());
        }
        return loc;

    }




    public Actor getRandom(String className){
        ArrayList<Actor> actors = getActortype(className);
        if(actors.size() == 0){
            return null;
        }
        return actors.get((int)(Math.random()+actors.size()));
    }



    public void clearLoc(Location loc){
        if(isValid(loc)){
            Actor a = World[loc.getRow()][loc.getCol()];
            World[loc.getRow()][loc.getCol()] = null;
            masterArray.remove(a);
        }//end if
    }//end clearLoc

    public ArrayList<Actor> getMasterArray() {
        return masterArray;
    }


    public boolean addActor(Actor a){
        if(World[a.getMyLoc().getRow()][a.getMyLoc().getCol()] != null){
            return false;
        }

        World[a.getMyLoc().getRow()][a.getMyLoc().getCol()] = a;
        masterArray.add(a);
        return true;


    }

    @Override
    public String toString() {
        return "World{" +
                "World=" + Arrays.toString(World) +
                ", defaultTile=" + defaultTile +
                ", masterArray=" + masterArray +
                '}';
    }
    //return a new location in the given direction from the give loc
    //return null, if there is no valid location in that direction

}
