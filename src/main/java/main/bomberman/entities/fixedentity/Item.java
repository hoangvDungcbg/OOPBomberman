package main.bomberman.entities.fixedentity;

import main.bomberman.entities.Entity;

public class Item extends FixedEntity {
    private double speed = 1.0;
    private int fire = 0;
    private int c4 = 0;

    public double getSpeed(){
        return speed;
    }

    public int getFire(){
        return fire;
    }

    public int getC4(){
        return c4;
    }

    public double[] getProperties(){
        return new double[]{speed, fire, c4};
    }
    
    public Item(String itemname){
        if(itemname.equals("speed")) {
            setImg("sprites\\powerup_speed.png");
            speed = 2;
        }
        else if(itemname.equals("flames")) {
            setImg("sprites\\powerup_flames.png");
            fire = 2;
        }
        else if(itemname.equals("bomb")) {
            setImg("sprites\\powerup_bombs.png");
            c4 = 2;
        }
        else if(itemname.equals("portal")){
            setImg("sprites\\portal.png");
        }
    }
}
