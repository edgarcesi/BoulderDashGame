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
	private int mapID = 3; // Map to load
	private boolean win,dead = false;
	private long time;

	private Map map;
	private Player player;

	/**
	 * Instantiates a new model.
	 */
	public Model() {
		this.loadMap(mapID);
		this.player = new Player(RealPos(map.getStartX()),RealPos(map.getStartY()));

		// Start gravity thread
		time = getMap().getTime();
		Thread gravity = new Thread(() -> {
			while (time > 0) {
				try {
					mapGravity();
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
	 * @param map
	 *            the new map
	 */
	private void setMap(final Map map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Load the map.
	 *
	 *@param id
	 *            the map id
	 */
	public void loadMap(final int id){
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
		}
		catch (final SQLException e) {
			e.printStackTrace();
		}

	}


	public void mapGravity(){
		Block[][] blocks = map.getBlocks();
		for(int y = 0; y<map.getHeight(); y++) {
			for (int x = 0; x <map.getLenght(); x++) {
				// If ROCK
				if ( (blocks[y][x].getType().equals(BlockType.ROCK) )
						&& // And block under is empty
					 (blocks[y+1][x].getType().equals(BlockType.EMPTY)) ) {

						 // If player is under do nothing
					if( (IndexPos(player.getPosX())==x) && (IndexPos(player.getPosY()-1)==y) ){
						System.out.println("Joueur sous le bloc");
					} else {
						System.out.println("GRAVITY");
						//Apply gravity
						blocks[y][x].setType(BlockType.EMPTY);
						blocks[y + 1][x].setType(BlockType.ROCK);
					}
				}
			}
		}
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

	@Override
	public long getTime() {
		return time;
	}
}
