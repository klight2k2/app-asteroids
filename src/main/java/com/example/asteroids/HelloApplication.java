package com.example.asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage mainStage) {

        mainStage.setTitle("Asteroids");
        BorderPane root=new BorderPane();
        Scene mainScene=new Scene(root);
        mainStage.setScene(mainScene);

        Canvas canvas=new Canvas(800,600);
        GraphicsContext context=canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        ArrayList<String> keyPressedList=new ArrayList<String>();
        ArrayList<Sprite> laserList=new ArrayList<Sprite>();
        ArrayList<Sprite> asteroidList=new ArrayList<Sprite>();

        mainScene.setOnKeyPressed((KeyEvent event)->{
            String keyName=event.getCode().toString();
            if(!keyPressedList.contains(keyName))
                keyPressedList.add(keyName);

        });  mainScene.setOnKeyReleased((KeyEvent event)->{
            String keyName=event.getCode().toString();
            if(keyPressedList.contains(keyName))
                keyPressedList.remove(keyName);

        });

        Sprite background=new Sprite("file:///D:/java/Asteroids/src/main/java/com/example/asteroids/image/space.png");
        background.position.set(400,300);
        background.render(context);
        Sprite spaceShip=new Sprite("file:///D:/java/Asteroids/src/main/java/com/example/asteroids/image/spaceship.png");
        spaceShip.position.set(100,300);
        spaceShip.velocity.set(50,0);

        spaceShip.render(context);

        AnimationTimer gameloop=new AnimationTimer() {
            @Override
            public void handle(long nanotime) {
                if(keyPressedList.contains("LEFT"))
                    spaceShip.velocity.setAngle(0);
                  if(keyPressedList.contains("RIGHT")){
                      spaceShip.velocity.setAngle(180);

                  }
             if(keyPressedList.contains("UP"))
             {
                 spaceShip.velocity.setLength(50);
                 spaceShip.velocity.setAngle(-90);

             }
             if (keyPressedList.contains("DOWN")){
                 spaceShip.velocity.setAngle(90);
             }
             if (keyPressedList.contains("SPACE")){
                 Sprite laser=new Sprite("file:///D:/java/Asteroids/src/main/java/com/example/asteroids/image/laser.png");
                 laser.position.set(spaceShip.position.x,spaceShip.position.y);
                 laser.velocity.setLength(400);
                 laser.velocity.setAngle(spaceShip.rotation);
                laserList.add(laser);
             }
                spaceShip.update(1/60.0);
                for (Sprite laser:laserList){
                    laser.update(1/60.0);
                };
                background.render(context);
                spaceShip.render(context);
                for (Sprite laser:laserList){
                    laser.render(context);
                }
            }
        };
        gameloop.start();
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}