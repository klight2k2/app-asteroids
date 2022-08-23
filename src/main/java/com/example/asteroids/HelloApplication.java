package com.example.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


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
        Sprite background=new Sprite("images/space.png");
        background.position.set(400,300);
        background.render(context);
        context.fillRect(0,0,800,600);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}