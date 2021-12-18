package main.bomberman.board;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.Entity;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;
import main.bomberman.entities.fixedentity.*;
import main.bomberman.graphics.SetHud;
import main.bomberman.level.LoadLevel;

import java.util.ArrayList;

public class BoardGame {
    private static int width;
    private static int height;
    private static ArrayList<Entity> map = new ArrayList<>();
    private static ArrayList<Enemy> mobs = new ArrayList<>();
    private static Bomber player;

    private static SetHud properties;
    private static boolean paused;
    private static boolean gamelost;

    public BoardGame(){
        map.clear();
        mobs.clear();
        player = new Bomber();
        LoadLevel.load(1, player);
        width = LoadLevel.get_width();
        height = LoadLevel.get_height();
        map = LoadLevel.getMap();
        mobs = LoadLevel.getListEnemy();
        properties = new SetHud();
        paused = false;
        gamelost = false;
    }

    public void update(double elapsedTime){
        if(paused){
            return;
        }

        player.update(elapsedTime);

        for(int i = 0; i < mobs.size(); i++){
            mobs.get(i).update(elapsedTime);
            if(mobs.get(i).isKilled()){
                mobs.remove(i);
                i--;
            }
        }
    }
    /*@Override
	public void update() {
		if( _game.isPaused() ) return;
		
		updateEntities();
		updateMobs();
		updateBombs();
		updateMessages();
		detectEndGame();
		
		for (int i = 0; i < _mobs.size(); i++) {
			Mob a = _mobs.get(i);
			if(((Entity)a).isRemoved()) _mobs.remove(i);
		}
	}


	@Override
	public void render(Screen screen) {
		if( _game.isPaused() ) return;
		
		//only render the visible part of screen
		int x0 = Screen.xOffset >> 4; //tile precision, -> left X
		int x1 = (Screen.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
		int y0 = Screen.yOffset >> 4;
		int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILES_SIZE; //render one tile plus to fix black margins
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				_entities[x + y * _level.getWidth()].render(screen);
			}
		}
		
		renderBombs(screen);
		renderMobs(screen);
		
	}
	
	
	public void newGame() {
		resetProperties();
		changeLevel(1);
	}
	
	@SuppressWarnings("static-access")
	private void resetProperties() {
		_points = Game.POINTS;
		_lives = Game.LIVES;
		Player._powerups.clear();
		
		_game.playerSpeed = 1.0;
		_game.bombRadius = 1;
		_game.bombRate = 1;
		
	}

	public void restartLevel() {
		changeLevel(_level.getLevel());
	}
	
	public void nextLevel() {
		changeLevel(_level.getLevel() + 1);
	}
	
	public void changeLevel(int level) {
		_time = Game.TIME;
		_screenToShow = 2;
		_game.resetScreenDelay();
		_game.pause();
		_mobs.clear();
		_bombs.clear();
		_messages.clear();
		
		try {
			_level = new FileLevel("levels/Level" + level + ".txt", this);
			_entities = new Entity[_level.getHeight() * _level.getWidth()];
			
			_level.createEntities();
		} catch (LoadLevelException e) {
			endGame(); //failed to load.. so.. no more levels?
		}
	}
	
	public void changeLevelByCode(String str) {
		int i = _level.validCode(str);
		
		if(i != -1) changeLevel(i + 1);
	}
	
	public boolean isPowerupUsed(int x, int y, int level) {
		Powerup p;
		for (int i = 0; i < Player._powerups.size(); i++) {
			p = Player._powerups.get(i);
			if(p.getX() == x && p.getY() == y && level == p.getLevel())
				return true;
		}
		
		return false;
	}*/
    public void render(GraphicsContext gc, double time){
        for(Entity e : map) {
            e.render(gc);
        }

        player.render(gc, time);

        for(Enemy enemy : mobs){
            enemy.render(gc, time);
        }

        

        properties.render(gc);

        if(gamelost){
            paused = true;
            properties.renderGameOver(gc);
        }
    }

    public static Entity getEntityAt(int x, int y){
        if(x + y*width >= 0 && x + y*width < map.size())
            return map.get(x + y*width);
        return new Wall();
    }

    public static ArrayList<Enemy> getEnemyList(){
        return mobs;
    }

    public static void setPause(boolean ok){
        paused = ok;
    }

    public static boolean isPaused(){
        return paused;
    }

    public static void nextLevel(){
        map.clear();
        mobs.clear();
        player = new Bomber();
        LoadLevel.load(LoadLevel.getlev() + 1, player);
        map = LoadLevel.getMap();
        mobs = LoadLevel.getListEnemy();
        properties.resetTime();
    }

    public static void setGameOver(boolean ok){
        gamelost = ok;
    }
    
    public static boolean endGame(){
        return gamelost;
    }
    
    public static void wait(int ms)
         {
                        try
                    {
                        Thread.sleep(ms);
                    }
                            catch(InterruptedException ex)
                    {
        Thread.currentThread().interrupt();
    }
        }
    public static void restart(){
        map.clear();
        mobs.clear();
        player = new Bomber();
        LoadLevel.load(1, player);
        width = LoadLevel.get_width();
        height = LoadLevel.get_height();
        map = LoadLevel.getMap();
        mobs = LoadLevel.getListEnemy();
        properties = new SetHud();
        paused = false;
        gamelost = false;
    }
}
