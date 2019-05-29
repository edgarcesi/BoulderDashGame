package contract;

import java.util.Observable;

import entity.HelloWorld;
import entity.Map;
import entity.Player;

import javax.swing.*;

/**
 * The Interface IModel.
 *
 * @author Jean-Aymeric Diet
 */
public interface IModel {

	/**
	 * Gets the map
	 *
	 * @return the map entity
	 */
	Map getMap();

	/**
	 * Load the map
	 *
	 * @param id
	 * 			the id
	 */
	void loadMap(int id);


	int RealPos(int index);
	 int IndexPos(int realPos);

	 void setPlayer(final Player player);
	 Player getPlayer();

	boolean getWin();
	void setWin(boolean win);
	void setDead(boolean dead);
	boolean isDead();
	long getTime();

	void mapGravity();

	//void PlayerDeathAnnimation(int prevTopX, int prevBotY);
	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
}
