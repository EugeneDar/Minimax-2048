package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    long counter = 0;

    @FXML
    public Canvas canvas;

    public Affine affine;

    Game game;

    boolean isGameOver = false;

    @FXML
    public Text textView;

    public Controller() {

        canvas = new Canvas(600, 600);

        affine = new Affine();
        affine.appendScale(150, 150);

        game = new Game();
    }


    public void draw() {

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setTransform(affine);

        // fill all Rectangle
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, 600, 600);

        // paint lines
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.2);
        for (int i = 150;i < 600;i += 150) {
            g.strokeLine(0, i, 600, i);
        }
        for (int i = 150;i < 600;i += 150) {
            g.strokeLine(i, 0, i, 600);
        }

        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                g.setFill(getColorByNumber(game.field[i][j]));
                g.fillRect(i,j,1,1);
            }
        }


    }


    private Color getColorByNumber (int num) {
        switch (num) {
            case 0 -> {return Color.rgb(255, 255, 255);}
            case 2 -> {return Color.rgb(255, 255, 0);}
            case 4 -> {return Color.rgb(255, 155, 0);}
            case 8 -> {return Color.rgb(255, 65, 0);}
            case 16 -> {return Color.rgb(255, 0, 0);}
            case 32 -> {return Color.rgb(255, 0, 65);}
            case 64 -> {return Color.rgb(255, 0, 255);}
            case 128 -> {return Color.rgb(145, 0, 255);}
            case 256 -> {return Color.rgb(0, 85, 255);}
            case 512 -> {return Color.rgb(0, 125, 125);}
            case 1024 -> {return Color.rgb(0, 255, 125);}
            case 2048 -> {return Color.rgb(0, 255, 0);}
            default -> {return Color.BLACK;}
        }
    }

    public void generateGame () {
        game = new Game();
        draw();
        new AppThread(this);

//        canvas.getScene().setOnKeyTyped(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                if (keyEvent.getCharacter().equals("w")) {
//                    swipeUp();
//                } else if (keyEvent.getCharacter().equals("a")) {
//                    swipeLeft();
//                } else if (keyEvent.getCharacter().equals("s")) {
//                    swipeDown();
//                } else if (keyEvent.getCharacter().equals("d")) {
//                    swipeRight();
//                }
//            }
//        }) ;
    }

    public void swipeLeft () {
        game = game.swipeUp();
        game = game.makeRandomBlock();
        if (game.isGameOver()) {
            System.out.println("GAME OVER");
        }
        draw();
    }

    public void swipeRight () {
        game = game.swipeDown();
        game = game.makeRandomBlock();
        if (game.isGameOver()) {
            System.out.println("GAME OVER");
        }
        draw();
    }

    public void swipeUp () {
        game = game.swipeLeft();
        game = game.makeRandomBlock();
        if (game.isGameOver()) {
            System.out.println("GAME OVER");
        }
        draw();
    }

    public void swipeDown () {
        game = game.swipeRight();
        game = game.makeRandomBlock();
        if (game.isGameOver()) {
            System.out.println("GAME OVER");
        }
        draw();
    }

}
