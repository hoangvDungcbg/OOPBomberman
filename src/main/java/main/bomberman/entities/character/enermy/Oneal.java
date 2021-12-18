package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AIBasic;

import java.util.Random;

public class Oneal extends Enemy {
    public Oneal(Bomber b){
        bomber = b;
        ai = new AIBasic();

        setFrame("sprites\\oneal_left", "sprites\\oneal_left",
                "sprites\\oneal_right", "sprites\\oneal_right", 3);
        setDeadAnimation("sprites\\oneal_dead", 1);
    }
    @Override
    public void calcMove() {
        Random random = new Random();
        speed = 20 + random.nextInt(80);
        super.calcMove();
    }
}
