package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.fixedentity.*;


abstract public class AnimatedCharacter extends Entity {
    protected double duration = 0.10;
    protected double velocX;
    protected double velocY;
    protected double speed;
    protected Image[][] framesMove = new Image[4][];
    protected Image[] dead;
    protected boolean moving;
    protected boolean alive = true;
    protected int movestatus = 0; //0: down, 1: left, 2: right, 3: up
    protected int lastTime = 0;

    public AnimatedCharacter(){
        super();
        velocX = 0;
        velocY = 0;
        speed = 200;
    }
    public void setDeadAnimation(String dead, int _number){
        this.dead = Sprite.getListImage(dead, _number, scalefactor);
    }
    public Image getFrame(double time, Image[] frames){
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
    
    public void setFrame(String down, String left, String right, String up, int _number) {
        for (int i = 0; i < _number; i++)
            framesMove[0] = Sprite.getListImage(down, _number, scalefactor);
        for (int i = 0; i < _number; i++)
            framesMove[1] = Sprite.getListImage(left, _number, scalefactor);
        for (int i = 0; i < _number; i++)
            framesMove[2] = Sprite.getListImage(right, _number, scalefactor);
        for (int i = 0; i < _number; i++)
            framesMove[3] = Sprite.getListImage(up, _number, scalefactor);
    }

    

    

    public void setVelocity(double x, double y) {
        velocX = x;
        velocY = y;
    }

    public void setStatusMove(String statusMove){
        setVelocity(0, 0);
        switch (statusMove) {
            case "DOWN" -> {
                this.movestatus = 0;
                velocY += speed;
                moving = true;
            }
            case "LEFT" -> {
                this.movestatus = 1;
                velocX -= speed;
                moving = true;
            }
            case "RIGHT" -> {
                this.movestatus = 2;
                velocX += speed;
                moving = true;
            }
            case "UP" -> {
                this.movestatus = 3;
                velocY -= speed;
                moving = true;
            }
            default -> moving = false;
        }
    }

    public void setMovestatus(int movestatus){
        moving = true;
        setVelocity(0, 0);
        switch (movestatus) {

            case 0 -> velocY += speed;
            case 1 -> velocX -= speed;
            case 2 -> velocX += speed;
            case 3 -> velocY -= speed;
            default -> moving = false;
        }
        if(moving)
            this.movestatus = movestatus;
    }
    public void update(double time) {
        if(!alive)
            return;
        if(movable((int) (x + velocX * time), (int)(y + velocY * time))){
            x += velocX * time;
            y += velocY * time;
        }
    }

    public void render(GraphicsContext gc, double time) {
        if (alive) {
            if(moving) {
                gc.drawImage(getFrame(time, framesMove[movestatus]), x, y);
            }
            else gc.drawImage(framesMove[movestatus][0], x, y);
        } else if (lastTime < dead.length * 10) {
            gc.drawImage(dead[lastTime / 10], x, y);
            lastTime++;
        }
    }
    /*
    
    */
    public boolean movable(int x, int y){
        x += w/3;
        y += h/3;

        Entity upperleft = BoardGame.getEntityAt((x - w/4)/w, y/h);
        Entity upperright = BoardGame.getEntityAt((x + w*3/5)/w, y/h);
        Entity lowerleft = BoardGame.getEntityAt((x - w/4)/w, (y+ 2*h/3)/h);
        Entity lowerright = BoardGame.getEntityAt((x + w*3/5)/w, (y+ 2*h/3)/h);
        
        if((upperleft instanceof Brick && !((Brick) upperleft).isDestroyed())||
                (upperright instanceof Brick && !((Brick) upperright).isDestroyed())||
                (lowerleft instanceof Brick && !((Brick) lowerleft).isDestroyed())||
                (lowerright instanceof Brick && !((Brick) lowerright).isDestroyed())){
            return false;
        }
        
        if(upperleft instanceof Wall || upperright instanceof Wall ||
                lowerleft instanceof Wall || lowerright instanceof Wall){
            return false;
        }
        return true;
    }

    public void killed(){
        alive = false;
    }

    public boolean isKilled(){
        return !alive;
    }

    public double getSpeed(){
        return speed;
    }
}
