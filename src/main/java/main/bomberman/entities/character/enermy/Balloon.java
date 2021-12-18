package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AIBasic;

public class Balloon extends Enemy {
    public Balloon(Bomber b){
        bomber = b;
        ai = new AIBasic();

        setFrame("sprites\\balloom_left", "sprites\\balloom_left",
                "sprites\\balloom_right", "sprites\\balloom_right", 3);
        setDeadAnimation("sprites\\balloom_dead", 1);
    }

}
