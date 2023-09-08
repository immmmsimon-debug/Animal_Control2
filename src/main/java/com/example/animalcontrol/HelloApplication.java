package com.example.animalcontrol;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static final int TILE_SIZE = 25;
    private World myWorld;
    private AnimationTimer animTimer;
    
    private boolean isAnimationRunning;
    @Override
    public void start(Stage stage) throws IOException {
        //
        //HelloController - both the view and the controller for at least, our default view
        //
        HelloController hc = new HelloController(this);

        //World myWorld = new World(5,3);
        myWorld = new World((int)hc.getCanvasheight()/TILE_SIZE,(int)hc.getCanvasWidth()/TILE_SIZE);
        myWorld.addActor(new wolf("pog", myWorld, 4,2));
        myWorld.addActor(new BoxBug("700", myWorld, 4,4));
        myWorld.addActor(new FiveBug("700", myWorld, 18,18));
        myWorld.addActor(new grass("800", myWorld, 20,18));
        myWorld.addActor(new grass("900", myWorld, 11,18));
        myWorld.addActor(new grass("1000", myWorld, 15,18));







        Scene rootScene = new Scene(hc.getAnchorPane(), 1250, 926);
        stage.setTitle("Animal Sim");
        stage.setScene(rootScene);


         animTimer = new AnimationTimer(){
             private long lastUpdate = 0;
            public void handle(long currentNanoTime){
                if (currentNanoTime - lastUpdate >= 50_000_000)
                myWorld.step();
                myWorld.draw(hc.getGraphicContext());
                lastUpdate = currentNanoTime;

            }
        };


        myWorld.draw(hc.getGraphicContext());
        stage.show();
    }

    public void startAnimation(){
        animTimer.start();
        isAnimationRunning = true;
    }
    
    public void stopAnimation(){
        animTimer.stop();
        isAnimationRunning = false;
    }

    
    public boolean isAnimationRunning(){
        return isAnimationRunning;
    }

    public static void main(String[] args) {
        launch();
    }
}