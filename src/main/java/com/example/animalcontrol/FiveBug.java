package com.example.animalcontrol;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


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

        if (counter <= 5) {
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


    }
}

