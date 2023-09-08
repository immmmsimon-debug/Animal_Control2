package com.example.animalcontrol;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
