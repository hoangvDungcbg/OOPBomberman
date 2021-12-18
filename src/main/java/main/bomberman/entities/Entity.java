package main.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.graphics.SetImage;
import main.bomberman.graphics.Sprite;


public abstract class Entity {
    protected int x;
    protected int y;
    protected boolean alive;
    protected int w = 48;
    protected int h = 48;
    protected Image img;
    protected int scalefactor = 3;

    public Entity() {
        x = 0;
        y = 0;
    }
    /*
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
    */
    public void setPosition(int posx, int posy){
        x = posx;
        y = posy;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
    
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    
    public void setSize(int width, int height){
        w = width;
        h = height;
    }

    public void setImg(String name){
        Image im = new Image(SetImage.set(name));
        img = Sprite.readImage(im, 0, 0, (int)im.getWidth(), (int)im.getHeight(), scalefactor);
        w = (int)im.getWidth()*scalefactor;
        h = (int)im.getHeight()*scalefactor;
    }

    public void setScaleFactor(int s){
        this.scalefactor = s;
    }

    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x,y,w,h);
    }

    public boolean collide(Entity s) {
        return s.getBounds().intersects(this.getBounds() );
    }

    public boolean collide(int x, int y, int w, int h){
        Rectangle2D rectangle2D = new Rectangle2D(x, y, w, h);
        return this.getBounds().intersects(rectangle2D);
    }
    
}
