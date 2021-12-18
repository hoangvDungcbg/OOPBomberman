package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.fixedentity.*;


abstract public class AnimatedEntity extends Entity {
    public Image[] frames;
    protected double duration = 0.10;

    public AnimatedEntity(){
        super();
    }

    public void setFrame(Image[] listFrame){
        frames = listFrame;
    }

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void render(GraphicsContext gc, double time) {
        gc.drawImage(getFrame(time), x, y);
    }
}
