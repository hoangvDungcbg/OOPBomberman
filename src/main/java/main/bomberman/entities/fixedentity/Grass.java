package main.bomberman.entities.fixedentity;

public class Grass extends FixedEntity {
    public Grass(){
        setScaleFactor(3);
        setImg("sprites\\grass.png");
    }
    public Grass(int x, int y) {
        setScaleFactor(3);
        setPosition(x, y);
        setImg("sprites\\grass.png");
    }

}
