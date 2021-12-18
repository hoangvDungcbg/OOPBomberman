package main.bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import javafx.scene.input.KeyEvent;
import main.bomberman.graphics.SetHud;

public class Game {
    private final int WIDTH = 1366;
    private final int HEIGHT = 768;
    private static SetHud sethud;
    public Game(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.GRAY);
        stage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Input.setScene(scene);
        BoardGame BG = new BoardGame();
        Timeline gloop = new Timeline();
        gloop.setCycleCount( Timeline.INDEFINITE );
        final long timeStart = System.currentTimeMillis();
        final long[] lastNanoTime = {System.nanoTime()};

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.019),                // 60 FPS
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {          
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;
                        double elapsedTime = (System.nanoTime() - lastNanoTime[0]) / 1000000000.0;
                        lastNanoTime[0] = System.nanoTime();
                        BG.update(elapsedTime);
                        // render
                        gc.clearRect(0, 0, WIDTH,HEIGHT);
                        BG.render(gc, t);
                        if(BoardGame.endGame()){
                            sethud.renderGameOver(gc);
                            BoardGame.wait(3000);
                            BoardGame.restart();
                            
                        }
                    }
                    
                });
        gloop.getKeyFrames().add(kf);
        gloop.play();
    }
}
