package main.bomberman.entities.character;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.bomb.Bomb;
import main.bomberman.entities.fixedentity.*;
import main.bomberman.graphics.AnimatedCharacter;


import java.util.ArrayList;

public class Bomber extends AnimatedCharacter {
    private ArrayList<Bomb> bomblist = new ArrayList<Bomb>();
    private Input input = new Input();
    private int timeToPlaceNextBomb = 0;
    private int numberBomb = 1;
    private int powerFlames = 0;

    public Bomber() {
        setScaleFactor(3);
        setFrame("sprites\\player_down_", "sprites\\player_left_",
                "sprites\\player_right_", "sprites\\player_up_", 3);
        setDeadAnimation("sprites\\player_dead", 3);
        setPosition(45, 45);
        alive = true;
    }
    
    public boolean powerUp(double[] properties){
        this.speed *= properties[0];
        this.powerFlames += properties[1];
        this.numberBomb += properties[2];
        return true;
    }
    
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(x + w/5, y + h/5, w/2, h/2);
    }

    @Override
    public void killed(){
        BoardGame.setGameOver(true);
        alive = false;
    }
    /*
    keyboard type
    */
    public void keyboardinput(){
        setVelocity(0,0);
        moving = false;
        if (input.up()){
            setStatusMove("UP");
        }
        else if (input.down()) {
            setStatusMove("DOWN");
        }
        else if (input.left()){
            setStatusMove("LEFT");
        }
        else if (input.right()){
            setStatusMove("RIGHT");
        }
        if(numberBomb > bomblist.size() && input.placeBomb() && timeToPlaceNextBomb < 0){
            bomblist.add(new  Bomb(this, powerFlames));
            timeToPlaceNextBomb = 20;
        }
    }

    @Override
    public void update(double time){
        keyboardinput();
        timeToPlaceNextBomb--;

        super.update(time);

        if(bomblist.size() > 0) {
            for(int i = 0; i < bomblist.size(); i++) {
                Bomb bomb = bomblist.get(i);
                bomb.update();
                if (bomb.isDestroyed()) {
                    bomblist.remove(bomb);
                    i--;
                }
            }
        }
    }
    
    /*
    * movable?
    */
    @Override
    public boolean movable(int x, int y){
        x += w/3;
        y += h/3;

        Entity upperleft = BoardGame.getEntityAt((x - w/4)/w, y/h);
        Entity upperright = BoardGame.getEntityAt((x + w/3)/w, y/h);
        Entity lowerleft = BoardGame.getEntityAt((x - w/4)/w, (y+ 2*h/3)/h);
        Entity lowerright = BoardGame.getEntityAt((x + w/3)/w, (y+ 2*h/3)/h);

        if(upperleft instanceof Wall || upperright instanceof Wall ||
                lowerleft instanceof Wall || lowerright instanceof Wall){
            return false;
        }

        Brick brick = null;
        if(upperleft instanceof Brick){
            brick = (Brick) upperleft;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getEnemyList().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(upperright instanceof Brick){
            brick = (Brick) upperright;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getEnemyList().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(lowerleft instanceof Brick){
            brick = (Brick) lowerleft;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getEnemyList().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(lowerright instanceof Brick){
            brick = (Brick) lowerright;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.isPortal()){
                if( BoardGame.getEnemyList().size() == 0) {
                    System.out.println("next level");
                    BoardGame.nextLevel();
                    return true;
                }
                else return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }

        return true;
    }

    

    public void render(GraphicsContext gc, double time){
        super.render(gc, time);
        if(bomblist.size() > 0) {
            for (Bomb bomb : bomblist) {
                bomb.render(gc, time);
            }
        }
    }

    
}
