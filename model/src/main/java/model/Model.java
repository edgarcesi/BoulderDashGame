package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import contract.IModel;
import entity.HelloWorld;
import entity.Map;
import entity.Player;
import entity.PlayerSprite;

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
	private boolean win = false;
    private Map map;
	private Player player;

	/**
	 * Instantiates a new model.
	 */
	public Model() {
		this.loadMap(mapID);
		this.player = new Player(RealPos(map.getStartX()),RealPos(map.getStartY()));
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
            // Fill map info
			ResultSet mapInfo = daoMap.mapInfo(id);
			this.map.setStartX(mapInfo.getInt("StartX"));
			this.map.setStartY(mapInfo.getInt("StartY"));
			this.map.setEndX(mapInfo.getInt("EndX"));
			this.map.setEndY(mapInfo.getInt("EndY"));
			this.map.setDiamond(mapInfo.getInt("Diamond"));
            // [DEBUG]-Write map info
			System.out.println("MAP INFO:");
			System.out.println("Start : "+map.getStartX()+","+map.getStartY());
			System.out.println("End : "+map.getEndX()+","+map.getEndY());
			System.out.println("Diamond : "+map.getDiamond());
		} catch (final SQLException e) {
			e.printStackTrace();
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

	public void PlayerDeathAnnimation(int prevTopX, int prevBotY){
		getPlayer().setFrame(PlayerSprite.MORT);
		getMap().TransformToStar(IndexPos(prevTopX - 16), IndexPos(prevBotY));
		getMap().TransformToStar(IndexPos(prevTopX - 16), IndexPos(prevBotY-16));
		getMap().TransformToStar(IndexPos(prevTopX - 16), IndexPos(prevBotY+16));
		getMap().TransformToStar(IndexPos(prevTopX), IndexPos(prevBotY+16));
		getMap().TransformToStar(IndexPos(prevTopX), IndexPos(prevBotY-16));
		getMap().TransformToStar(IndexPos(prevTopX + 16), IndexPos(prevBotY-16));
		getMap().TransformToStar(IndexPos(prevTopX + 16), IndexPos(prevBotY+16));
		getMap().TransformToStar(IndexPos(prevTopX+16), IndexPos(prevBotY));

	}
}
