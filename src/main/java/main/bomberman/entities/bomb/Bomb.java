package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.character.Bomber;
import main.bomberman.graphics.AnimatedEntity;
import main.bomberman.graphics.Sprite;

public class Bomb extends AnimatedEntity {
    private boolean discovered = false;
    private int timeToExplode = 150; 
    private Flame flame;
    private boolean isDestroyed = false;
    private int explosionrange = 0;
    private Bomber bomber;
    
    public void update(){
        if(timeToExplode > 0)
            timeToExplode--;
        else {
            if(!discovered) {
                discovered = true;
                flame = new Flame(bomber, x, y, explosionrange);
            }
            else {

            }
        }
    }
    
    /*
    cons
    */
    public Bomb(Bomber bomber, int length){
        this.bomber = bomber;
        duration = 0.2;
        int x = ((bomber.getX() + bomber.getWidth() / 2)/bomber.getWidth())*bomber.getWidth();
        int y = ((bomber.getY() + bomber.getHeight() / 2)/ bomber.getHeight())* bomber.getHeight();
        setPosition(x, y);
        explosionrange = length;
        setFrame(Sprite.getListImage("sprites\\bomb_", 3, 3));
    }

    

    public boolean isDestroyed(){
        return isDestroyed;
    }

    @Override
    public void render(GraphicsContext gc, double time){
        if(discovered){
            if(!isDestroyed) {
                flame.render(gc, time);

                if(flame.isShowed())
                    isDestroyed = true;
            }
        }
        else{
            super.render(gc, time);
        }
    }

}
