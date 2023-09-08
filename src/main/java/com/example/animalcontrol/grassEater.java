package com.example.animalcontrol;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class grassEater extends Actor {
    public grassEater(String name, World myWorld, int row, int col) throws FileNotFoundException {
        super(name, myWorld, row, col);
        setImage(new Image(new FileInputStream("C:\\Users\\KVo24\\IdeaProjects\\Animal Control\\src\\main\\resources\\happyface.png")));

    }

    @Override
    public void act(){
        Location newLoc = chooseMove(getPossibleMoves());

        if(getMyWorld().getActor(newLoc) instanceof grass){
            getMyWorld().clearLoc(getMyLoc());//remove myself from the world
            setMyLoc(newLoc);//change my internal location
            getMyWorld().clearLoc(newLoc);//remove grass from the world
            getMyWorld().addActor(this);//add myself back into the world
        }
        else{
            super.act();
        }


    }
    @Override
    protected Location chooseMove(ArrayList<Location> emptyLocs){
        //locs is a list of EMPTY LOCATION ARROUND ME
        ArrayList<Location> neighbors = getMyWorld().getFourNeighbors(getMyLoc());

        for(Location l: neighbors){
            Actor a = getMyWorld().getActor(l);
            if (a instanceof grass){
                return l;
            }
        }
        //IF WE GET THIS FAR, WE DID NOT FIND GRASS
        return super.chooseMove(emptyLocs);
    }//end chooseMove

}
