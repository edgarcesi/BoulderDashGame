package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Observable;

import contract.IModel;
import entity.*;

import javax.swing.*;
import javax.swing.text.View;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public final class Model extends Observable implements IModel {
	private final int OFFSET = 16; // Const offset 16px
	private int mapID = 2; // Map to load
	private boolean win, dead = false;
	private long time;

	private Map map;
	private Player player;

	/**
	 * Instantiates a new model.
	 */
	public Model() {
		this.loadMap(mapID);
		this.player = new Player(RealPos(map.getStartX()), RealPos(map.getStartY()));

		// Start gravity thread
		time = getMap().getTime();
		Thread gravity = new Thread(() -> {
			while (time > 0) {
				mapGravity();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		gravity.start();
	}


	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getMessage()
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	private void setMap(final Map map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Load the map.
	 *
	 * @param id the map id
	 */
	public void loadMap(final int id) {
		try {
			final DAOMap daoMap = new DAOMap(DBConnection.getInstance().getConnection());
			// Get the map
			this.setMap(daoMap.find(id));

			// Start timer thread
			time = getMap().getTime();
			Thread timer = new Thread(() -> {
				while (time > 0) {
					try {
						Thread.sleep(1000);
						time--;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			timer.start();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}


	public void mapGravity() {
		Block[][] blocks = map.getBlocks();
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getLenght(); x++) {

				Block actualBlock = blocks[y][x];

				// If ROCK
				if (actualBlock.getType().equals(BlockType.ROCK)) {

					// If player is under, game over
					if(actualBlock.isFalling() && ((IndexPos(player.getPosX()) == x) && (IndexPos(player.getPosY() - 1) == y))){
						actualBlock.setFalling(false);
						setDead(true);
					}

					// If block under is not empty stop gravity
					if( !(blocks[y+1][x].getType().equals(BlockType.EMPTY)) ){
						blocks[y][x].setFalling(false);
					}

					// if block is falling apply gravity
					if (actualBlock.isFalling()) {
						actualBlock.setType(BlockType.EMPTY);
						blocks[y + 1][x].setType(BlockType.ROCK);
						blocks[y + 1][x].setFalling(true);
						actualBlock.setFalling(false);
					}

					// If block under is empty
					if (blocks[y+1][x].getType().equals(BlockType.EMPTY)) {
						// If block is falling and player under, game over
						if(actualBlock.isFalling() && (IndexPos(player.getPosX()) == x) && (IndexPos(player.getPosY() - 1) == y)){
							System.out.println("Player dead");
						}
						// Start falling once player is no longer under
						if(!actualBlock.isFalling() && !((IndexPos(player.getPosX()) == x) && (IndexPos(player.getPosY() - 1) == y))){
							actualBlock.setFalling(true);
						}
					}
				}
			}
		}
	}

	public void pickDiamond(){
        player.setScore(player.getScore()+1);
        if(player.getScore()>=map.getDiamond()){
           map.getBlocks(map.getEndX(),map.getEndY()).setType(BlockType.END);
        }
    }

    public float timePercent(){
	    return (time*100)/map.getTime();

    }
	/**
     * Gets the observable.
     *
     * @return the observable
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IModel#getObservable()
	 */
	public Observable getObservable() {
		return this;
	}

	public int RealPos(int index){ return index*OFFSET;}
	public int IndexPos(int realPos){ return realPos/OFFSET;}

	public Player getPlayer(){return this.player;}
	/**
	 * Sets the player.
	 *
	 * @param player
	 *            the new player
	 */
	public void setPlayer(Player player){this.player = player;}

	public boolean getWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
/*
	public void PlayerDeathAnnimation(int prevTopX, int prevTotY){
		getPlayer().setFrame(PlayerSprite.DEAD);
		if (!getMap().getBlockType(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY-OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY-OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY+OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX - OFFSET), IndexPos(prevTotY+OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX), IndexPos(prevTotY+OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX), IndexPos(prevTotY+OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX), IndexPos(prevTotY-OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX), IndexPos(prevTotY-OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX + OFFSET), IndexPos(prevTotY-OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX + OFFSET), IndexPos(prevTotY-OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX + OFFSET), IndexPos(prevTotY+OFFSET)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX + OFFSET), IndexPos(prevTotY+OFFSET));
		}
		if (!getMap().getBlockType(IndexPos(prevTopX+OFFSET), IndexPos(prevTotY)).equals(BlockType.WALL)){
			getMap().TransformToStar(IndexPos(prevTopX+OFFSET), IndexPos(prevTotY));
		}
	}
*/
	@Override
	public long getTime() {
		return time;
	}
}
