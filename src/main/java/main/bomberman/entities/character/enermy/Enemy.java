package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AI;
import main.bomberman.graphics.AnimatedCharacter;

abstract public class Enemy extends AnimatedCharacter {
    protected AI ai;
    protected int MAX_STEPS = 50;
    protected int steps = 0;
    protected Bomber bomber;

    public Enemy(){
        speed = 50;
        duration = 0.1;
    }
    
    public void calcMove() {
        steps = 0;
        setMovestatus(ai.calcmoves());
    }

    @Override
    public void killed(){
        alive = false;
     
    }
    @Override
    public void update(double time){
        if(!alive)
            return;

        if(this.collide(bomber)){
            bomber.killed();
        }

        if(steps >= MAX_STEPS){
            calcMove();
        }

        if(movable((int) (x + velocX * time), (int)(y + velocY * time))){
            steps++;
            x += velocX * time;
            y += velocY * time;
        }
        else{
            calcMove();
        }
    }

    

}
