package com.example.animalcontrol;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BoxBug extends Actor {
    int counter = 0 ;
    public BoxBug(String name, World myWorld, int row, int col) throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\boxbugFixed.png")));
        setDir(Location.East);
    }
    @Override
    public void act(){

        Location nextLoc= getMyLoc().getLocInDirection(getDir());
        int testDir = getMyLoc().getDirectionEight(nextLoc);
        ArrayList<Actor> testLoc = new ArrayList<>();
        testLoc = getMyWorld().getActortype("grass");
        System.out.println("My name: " + getName());
        System.out.println("Current dir: " + getDir());
        System.out.println("testDir in act: " + testDir);
        System.out.println("-----------------------\n");
        System.out.println("Master Array: " + getMyWorld().getMasterArray());
        System.out.println("All Actor type: " + getMyWorld().getActortype("grass"));
        System.out.println("All Actor Location: " + getMyWorld().getAllActorLoc("grass"));
        System.out.println("-----------------------\n");
        System.out.println("Clost Actor Type: " + getMyWorld().getNearest(this, "grass"));
        System.out.println("-----------------------\n");
        System.out.println("New Cycle");
        System.out.println("///////////////////////////\n");





        if(getMyWorld().isValid((nextLoc))&& getMyWorld().getActor(nextLoc) == null){

            counter++;
            getMyWorld().clearLoc(getMyLoc());
            setMyLoc(nextLoc);
            getMyWorld().addActor(this);
        }
        else{
            setDir((getDir()+2)%8);
        }
    }

}
