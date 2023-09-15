package com.example.animalcontrol;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class FiveBug extends BoxBug {

    private int counter;


    public FiveBug(String name, World myWorld, int row, int col) throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\boxbugFixed.png")));
        setDir(Location.East);
    }

    @Override
    public void act() {
        Location nextLoc = getMyLoc().getLocInDirection(getDir());
        Location myLoc = getMyLoc();
        int testDir = getMyLoc().getDirectionEight(nextLoc);
        ArrayList<Actor> testLoc = new ArrayList<>();
        testLoc = getMyWorld().getActortype("grass");
        System.out.println("------------------------\n");
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
        System.out.println("Direction to closest actor type: " + getMyLoc().getDirectionToLoc(getMyWorld().getNearest(this, "grass").getMyLoc(), true));
        System.out.println("New Cycle");
        System.out.println("------------------------\n");
        Location newLoc = chooseMove((getPossibleMoves()));
        newLoc= getMyLoc().getLocInDirection((myLoc.getDirectionToLoc(getMyWorld().getNearest(this, "grass").getMyLoc(), true)));



        getMyWorld().clearLoc(getMyLoc());
        if(getMyWorld().isValid(newLoc) ==false){
            getMyWorld().clearLoc(getMyLoc().getDirectionToLoc(getMyWorld().getNearest(this, "grass")));
            setMyLoc(newLoc);
        }
            setMyLoc(newLoc);
        getMyWorld().addActor(this);
        /*if (counter <= 5) {
            if (getMyWorld().isValid((nextLoc)) && getMyWorld().getActor(nextLoc) == null) {
                super.act();
            counter++;
            } else {
                counter =0 ;
                setDir((getDir() + 2) % 8);

            }


        }
        else {
            setDir((getDir() + 2) % 8);
            counter = 0;
        }

         */


    }
}

