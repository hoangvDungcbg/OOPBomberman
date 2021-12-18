package main.bomberman.entities.character.enermy.ai;

public class AIBasic extends AI{

    @Override
    public int calcmoves() {
        int pathway = 4;
        return rand.nextInt(pathway);
    }
}
