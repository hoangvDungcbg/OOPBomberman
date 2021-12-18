package main.bomberman.entities.fixedentity;

public class Wall extends FixedEntity {
    
    public Wall(){
        setScaleFactor(3);
        setImg("sprites\\wall.png");
    }
    public Wall(int x, int y) {
        setScaleFactor(3);
        setPosition(x, y);
        setImg("sprites\\wall.png");
    }
}
