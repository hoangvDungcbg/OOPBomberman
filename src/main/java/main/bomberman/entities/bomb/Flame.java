package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;
import main.bomberman.entities.fixedentity.*;
import main.bomberman.graphics.AnimatedEntity;
import main.bomberman.graphics.SetHud;
import main.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Flame extends AnimatedEntity {
    private Image[] center;

    private Image[][] North; 
    private Image[][] South;
    private Image[][] West;
    private Image[][] East;

    private int moveup;
    private int movedown;
    private int moveleft;
    private int moveright;

    private int showed = 20;
    private int length = 0;

    public Flame(Bomber bomber, int posx, int posy, int length){
        duration = 0.2;
        x = posx;
        y = posy;
        this.length = length;
        moveup = length + 1;
        movedown = length + 1;
        moveleft = length + 1;
        moveright = length + 1;
        calcDestroy(bomber);
        loadIMG();
      
    }
    
    private void loadIMG() {
        center = Sprite.getListImage("sprites\\bomb_exploded", 3, 3);

        if(movedown > 0){
            South = new Image[movedown][];
            for(int i = 0; i < movedown - 1; i++){
                South[i] = Sprite.getListImage("sprites\\explosion_vertical", 3, 3);
            }
            South[movedown-1] = Sprite.getListImage("sprites\\explosion_vertical_down_last", 3, 3);
        }

        if(moveup > 0){
            North = new Image[moveup][];
            for(int i = 0; i < moveup - 1; i++){
                North[i] = Sprite.getListImage("sprites\\explosion_vertical", 3, 3);
            }
            North[moveup-1] = Sprite.getListImage("sprites\\explosion_vertical_top_last", 3, 3);
        }

        if(moveright > 0){
            East = new Image[moveright][];
            for(int i = 0; i < moveright - 1; i++){
                East[i] = Sprite.getListImage("sprites\\explosion_horizontal", 3, 3);
            }
            East[moveright-1] = Sprite.getListImage("sprites\\explosion_horizontal_right_last", 3, 3);
        }
        if(moveleft > 0){
            West = new Image[moveleft][];
            for(int i = 0; i < moveleft - 1; i++){
                West[i] = Sprite.getListImage("sprites\\explosion_horizontal", 3, 3);
            }
            West[moveleft-1] = Sprite.getListImage("sprites\\explosion_horizontal_left_last", 3, 3);
        }

    }
    
    private void calcDestroy(Bomber bomber){
        ArrayList<Enemy> listE = BoardGame.getEnemyList();
        int centreX = x/w;
        int centreY = y/h;

        for(int i = centreY; i < centreY + 2 + length; i++){
            Entity entity = BoardGame.getEntityAt(centreX, i);
            if(entity instanceof Wall){
                movedown = i - centreY - 1;      //wall cant be destroyed
                break;
            }
            if(entity instanceof Brick && !((Brick) entity).isDestroyed()){
                ((Brick) entity).setDestroyed(true);         //brick on the other hand...
                movedown = i - centreY - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.collide(x, i*h, w, h)){
                    
                    enemy.killed();
                }
            }
            if(bomber.collide(x, i*h, w, h)){
                bomber.killed();
            }
        }
        for(int i = centreY - 1; i >= centreY - 1 - length; i--){
            Entity entity = BoardGame.getEntityAt(centreX, i);
            if(entity instanceof Wall){
                moveup = centreY - i - 1;
                break;
            }
            if(entity instanceof Brick && !((Brick) entity).isDestroyed()){
                ((Brick) entity).setDestroyed(true);
                moveup = centreY - i - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.collide(x, i*h, w, h)){
                    
                    enemy.killed();
                }
            }
            if(bomber.collide(x, i*h, w, h)){
                bomber.killed();
            }
        }
        for(int i = centreX + 1; i < centreX + 2 + length; i++){
            Entity entity = BoardGame.getEntityAt(i, centreY);
            if(entity instanceof Wall){
                moveright = i - centreX - 1;
                break;
            }
            if(entity instanceof Brick && !((Brick) entity).isDestroyed()){
                ((Brick) entity).setDestroyed(true);
                moveright = i - centreX - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.collide(i*w, y, w, h)){
                    
                    enemy.killed();
                }
            }
            if(bomber.collide(i*w, y, w, h)){
                bomber.killed();
            }
        }
        for(int i = centreX - 1; i >= centreX - 1 - length; i--){
            Entity entity = BoardGame.getEntityAt(i, centreY);
            if(entity instanceof  Wall){
                moveleft = centreX - i - 1;
                break;
            }
            if(entity instanceof Brick && !((Brick) entity).isDestroyed()){
                ((Brick) entity).setDestroyed(true);
                moveleft = centreX - i - 1;
                break;
            }
            for(Enemy enemy : listE){
                if(enemy.collide(i*w, y, w, h)){
                    
                    enemy.killed();
                }
            }
            if(bomber.collide(i*w, y, w, h)){
                bomber.killed();
            }
        }
    }

    public boolean isShowed(){
        return showed == 0;
    }

    public Image getFrame(Image[] im, double time){
        int index = (int)((time % (im.length * duration)) / duration);
        return im[index];
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        if(--showed > 0) {
            gc.drawImage(getFrame(center, time), x, y);

            for(int i = 0; i < moveleft; i++){
                gc.drawImage(getFrame(West[i], time), x - w*(i+1), y);
            }
            for(int i = 0; i < moveright; i++){
                gc.drawImage(getFrame(East[i], time), x + w*(i+1), y);
            }
            for(int i = 0; i < moveup; i++){
                gc.drawImage(getFrame(North[i], time), x, y - h*(i+1));
            }
            for(int i = 0; i < movedown; i++){
                gc.drawImage(getFrame(South[i], time), x, y + h*(i+1));
            }
        }
    }

    
}
