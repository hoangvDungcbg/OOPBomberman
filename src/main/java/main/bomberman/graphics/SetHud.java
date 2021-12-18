package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import main.bomberman.board.BoardGame;
import main.bomberman.level.LoadLevel;

import java.util.Random;

public class SetHud{
    private final Image whengameislost;
    
    private final int MAX_TIME = 60*60*2;
    private int time;
    private final Random random = new Random();

    public SetHud(){
        whengameislost = new Image(SetImage.set("sprites\\gameover.png"));
    
        time = MAX_TIME;
    }
    public void render(GraphicsContext gc){
        gc.setFont(new Font("Helvetica", 30));
        gc.fillText("LEVEL " + LoadLevel.getlev(), 30, 35);
        gc.fillText("TIME: " + time/60, 1170, 35);

        if(time <= 0) {
            BoardGame.setGameOver(true);
        }
        else if(!BoardGame.isPaused()){
            time--;
        }
    }
    public void renderGameOver(GraphicsContext gc){
        gc.drawImage(whengameislost, 447, 200);
    }
    public void resetTime(){
        time = MAX_TIME;
    }
}
