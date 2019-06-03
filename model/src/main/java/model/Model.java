package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Observable;

import contract.IModel;
import entity.*;

import javax.swing.*;
import javax.swing.text.View;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 * @version $Id: $Id
 */
public final class Model extends Observable implements IModel {
	private final int OFFSET = 16; // Const offset 16px
	private int mapID = 5; // Map to load
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
	 * {@inheritDoc}
	 *
	 * Load the map.
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


	/**
	 * <p>mapGravity.</p>
	 */
	public void mapGravity() {
		Block[][] blocks = map.getBlocks();
		for (int y = 0; y < map.getHeight(); y++) {
			for (int x = 0; x < map.getLenght(); x++) {

				Block actualBlock = blocks[y][x];

				// If ROCK
				if (actualBlock.getType().equals(BlockType.ROCK)) {

					Block nextBlock = blocks[y+1][x];

					// If player is under a falling block, game over
					if(actualBlock.isFalling() && ((IndexPos(player.getPosX()) == x) && (IndexPos(player.getPosY() - 1) == y))){
						actualBlock.setFalling(false);
						PlayerDeathAnnimation(IndexPos(player.getPosX()), IndexPos(player.getPosY()));
						setDead(true);
					}

					// If block under is not empty stop gravity
					if( !(nextBlock.getType().equals(BlockType.EMPTY)) ){
						actualBlock.setFalling(false);
					}
					// Else if block under is empty, start falling once player is no longer under current block
					else {
						if(!actualBlock.isFalling() && !((IndexPos(player.getPosX()) == x) && (IndexPos(player.getPosY() - 1) == y))){
							actualBlock.setFalling(true);
						}
					}

					// if block is falling apply gravity
					if (actualBlock.isFalling()) {
						actualBlock.setType(BlockType.EMPTY);
						nextBlock.setType(BlockType.ROCK);
						nextBlock.setFalling(true);
						actualBlock.setFalling(false);
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * <p>pickDiamond.</p>
	 */
	public void pickDiamond(){
        player.setScore(player.getScore()+1);
        if(player.getScore()>=map.getDiamond()){
           map.getBlocks(map.getEndX(),map.getEndY()).setType(BlockType.END);
        }
    }

    /**
     * <p>timePercent.</p>
     *
     * @return a float.
     */
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

	/** {@inheritDoc} */
	public int RealPos(int index){ return index*OFFSET;}
	/** {@inheritDoc} */
	public int IndexPos(int realPos){ return realPos/OFFSET;}

	/**
	 * <p>Getter for the field <code>player</code>.</p>
	 *
	 * @return a {@link entity.Player} object.
	 */
	public Player getPlayer(){return this.player;}
	/**
	 * {@inheritDoc}
	 *
	 * Sets the player.
	 */
	public void setPlayer(Player player){this.player = player;}

	/**
	 * <p>Getter for the field <code>win</code>.</p>
	 *
	 * @return a boolean.
	 */
	public boolean getWin() {
		return win;
	}

	/** {@inheritDoc} */
	public void setWin(boolean win) {
		this.win = win;
	}

	/**
	 * <p>isDead.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isDead() {
		return dead;
	}

	/** {@inheritDoc} */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void PlayerDeathAnnimation(int x, int y){
		ArrayList<BlockType> forbiddenBlocks = new ArrayList<>();
		forbiddenBlocks.add(BlockType.ROCK);
		forbiddenBlocks.add(BlockType.WALL);
		forbiddenBlocks.add(BlockType.DIAMOND);

		if (!forbiddenBlocks.contains(getMap().getBlockType(x-1, y))){
			getMap().TransformToStar(x-1, y);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x-1, y-1))){
			getMap().TransformToStar(x-1, y-1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x-1, y+1))){
			getMap().TransformToStar(x-1, y+1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x, y+1))){
			getMap().TransformToStar(x, y+1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x, y-1))){
			getMap().TransformToStar(x, y-1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x+1, y-1))){
			getMap().TransformToStar(x+1, y-1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x+1, y+1))){
			getMap().TransformToStar(x+1, y+1);
		}
		if (!forbiddenBlocks.contains(getMap().getBlockType(x+1, y))){
			getMap().TransformToStar(x+1, y);
		}
	}

	/** {@inheritDoc} */
	@Override
	public long getTime() {
		return time;
	}
}
