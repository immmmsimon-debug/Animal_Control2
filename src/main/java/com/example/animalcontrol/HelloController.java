package com.example.animalcontrol;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class HelloController {
    private AnchorPane anchorPane;

    private Button StartStopButton;
    private Button testButton2;

    private Canvas canvas;

    private GraphicsContext gc;

    private HelloApplication app;

    public HelloController(HelloApplication act){
        anchorPane = new AnchorPane();
        this.app= act;
        createGUI();
        attachListeners();
    }

    private void attachListeners() {
        StartStopButton.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleButtonClick(actionEvent);
            }
        }));
        testButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleButtonClick(actionEvent);
            }
        });
    }


    public void handleButtonClick(ActionEvent actionEvent){
        if(actionEvent.getSource() == StartStopButton){
            if(app.isAnimationRunning()){
                app.stopAnimation();
                StartStopButton.setText("Start Sim");
            }
            else{
                app.startAnimation();
                StartStopButton.setText("Pause Sim");
            }
        }
        else if (actionEvent.getSource() == testButton2){
            System.out.println(("testButton2 Clicked"));
        }

    }

    private void createGUI(){
        StartStopButton = new Button("Start Sim");
        AnchorPane.setTopAnchor(StartStopButton,100.0 );
        AnchorPane.setRightAnchor(StartStopButton,10.0);
        anchorPane.getChildren().add(StartStopButton);

        testButton2 = new Button("Test Button2");
        AnchorPane.setTopAnchor(testButton2, 200.0);
        AnchorPane.setRightAnchor(testButton2,10.0);
        anchorPane.getChildren().add(testButton2);

        canvas = new Canvas(1000,900);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0,0,1000,1000);
        AnchorPane.setTopAnchor(canvas, 10.0);
        AnchorPane.setLeftAnchor(canvas, 10.0);
        AnchorPane.setBottomAnchor(canvas, 10.0);
        anchorPane.getChildren().add(canvas);

    }
    public GraphicsContext getGraphicContext(){
        return gc;
    }
    public double getCanvasheight(){
        return canvas.getHeight();
    }
    public double getCanvasWidth(){
        return canvas.getWidth();
    }
    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}