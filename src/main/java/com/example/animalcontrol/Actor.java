package com.example.animalcontrol;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Actor {

    private String name;
    private Image image;

    private World myWorld;

    private Location myLoc;

    private boolean hasActed;

    private int dir; //default is 0->North

    public Actor(String name) throws FileNotFoundException {
        this.name = name;
        this.myWorld = myWorld;
        image = new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\happyface.png"));
        myLoc = new Location(0,0);
    }
    public Actor() throws FileNotFoundException {
        this.name = null;
        this.myWorld = null;
        image = new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\happyface.png"));
        myLoc = new Location(0,0);
    }
    public Actor(String name, World myWorld, int row, int col) throws FileNotFoundException{
        this.name = name;
        this.myWorld = myWorld;
        image = new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\happyface.png"));
        myLoc = new Location(row,col);
    }

    public Actor(String name, World myWorld, int row, int col, Image image) {
        this.name = name;
        this.myWorld = myWorld;
        myLoc = new Location(row,col);
        this.image = image;
    }
    //the act method will do Exactly one action
    public void act(){
        Location newLoc = chooseMove((getPossibleMoves()));


        myWorld.clearLoc(myLoc);
        myLoc = newLoc;
        myWorld.addActor(this);
    }

    protected ArrayList<Location> getPossibleMoves(){
        ArrayList<Location> locs = new ArrayList<>();

        Location above = myLoc.getNorth();
        Location below = myLoc.getSouth();
        Location left  = myLoc.getWest();
        Location right = myLoc.getEast();

        if(myWorld.isValid(above) && myWorld.getActor(above) == null){
            locs.add(above);
        }
        if(myWorld.isValid(below) && myWorld.getActor(below) == null){
            locs.add(below);
        }
        if(myWorld.isValid(left) && myWorld.getActor(left) == null){
            locs.add(left);
        }
        if(myWorld.isValid(right) && myWorld.getActor(right) == null){
            locs.add(right);
        }
        return locs;


    }

    protected Location chooseMove(ArrayList<Location> locs){
        locs = getPossibleMoves();
        int randomIndex= (int)(Math.random()*locs.size());
        return locs.get(randomIndex);
    }

    public void draw(GraphicsContext gc){
        gc.save(); //save the settings
        Rotate r = new Rotate(dir*45,
                myLoc.getCol() *HelloApplication.TILE_SIZE + HelloApplication.TILE_SIZE/2,
                myLoc.getRow() * HelloApplication.TILE_SIZE + HelloApplication.TILE_SIZE/2);
        gc.setTransform(r.getMxx(),r.getMyx(),r.getMxy(),r.getMyy(),r.getTx(),r.getTy());

        gc.drawImage(image,
                myLoc.getCol() *HelloApplication.TILE_SIZE,
                myLoc.getRow() * HelloApplication.TILE_SIZE);
        gc.restore();
    }

    public Location getMyLoc() {
        return myLoc;
    }

    public void setMyLoc(Location myLoc) {
        this.myLoc = myLoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public World getMyWorld() {
        return myWorld;
    }

    public void setMyWorld(World myWorld) {
        this.myWorld = myWorld;
    }

    @Override
    public String toString() {
        return getName();
    }
}

