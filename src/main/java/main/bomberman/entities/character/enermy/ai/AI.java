package main.bomberman.entities.character.enermy.ai;

import java.util.Random;

abstract public class AI {
    protected Random rand = new Random();

    public abstract int calcmoves();
}
