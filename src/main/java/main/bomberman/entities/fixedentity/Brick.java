package main.bomberman.entities.fixedentity;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.graphics.AnimatedEntity;
import main.bomberman.graphics.Sprite;

public class Brick extends AnimatedEntity {
    private boolean isDestroyed;
    private boolean hasItem = false;
    private boolean collectedItem = false;
    private boolean isPortal = false;
    private boolean showPortal = false;
    private Item item;
    private int timeOff = 0;

    public Brick(){
        setScaleFactor(3);
        isDestroyed = false;
        setImg("sprites\\brick.png");
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 3));
    }

    public Brick(String item){
        setScaleFactor(3);
        isDestroyed = false;
        hasItem = true;
        if(item.equals("portal")){
            isPortal = true;
        }
        setImg("sprites\\brick.png");
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 3));
        this.item = new Item(item);
    }

    public Brick(int x, int y){
        setScaleFactor(3);
        isDestroyed = false;
        setImg("sprites\\brick.png");
        setPosition(x, y);
        setFrame(Sprite.getListImage("sprites\\brick_exploded", 3, 3));
    }

    @Override
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        if(hasItem){
            item.setPosition(x, y);
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed){
        isDestroyed = destroyed;
        setImg("sprites\\grass.png");
    }

    public boolean hasItem(){
        return hasItem && !collectedItem && !isPortal;
    }

    public void setCollectedItem(boolean collectedItem){
        if(isPortal)
            return;
        this.collectedItem = collectedItem;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public void render(GraphicsContext gc){
        if(isDestroyed && timeOff++ < frames.length * 5){
            gc.drawImage(getFrame(timeOff/5), x, y);
        }
        else if(isDestroyed && (hasItem && !collectedItem || isPortal)){
            item.render(gc);
        }
        else {
            super.render(gc);
        }
    }

    public boolean isPortal(){
        return isPortal;
    }
}
