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
 * @version $Id: $Id
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


	/**
	 * <p>RealPos.</p>
	 *
	 * @param index a int.
	 * @return a int.
	 */
	int RealPos(int index);
	 /**
	  * <p>IndexPos.</p>
	  *
	  * @param realPos a int.
	  * @return a int.
	  */
	 int IndexPos(int realPos);

	 /**
	  * <p>setPlayer.</p>
	  *
	  * @param player a {@link entity.Player} object.
	  */
	 void setPlayer(final Player player);
	 /**
	  * <p>getPlayer.</p>
	  *
	  * @return a {@link entity.Player} object.
	  */
	 Player getPlayer();

	/**
	 * <p>getWin.</p>
	 *
	 * @return a boolean.
	 */
	boolean getWin();
	/**
	 * <p>setWin.</p>
	 *
	 * @param win a boolean.
	 */
	void setWin(boolean win);
	/**
	 * <p>setDead.</p>
	 *
	 * @param dead a boolean.
	 */
	void setDead(boolean dead);
	/**
	 * <p>isDead.</p>
	 *
	 * @return a boolean.
	 */
	boolean isDead();
	/**
	 * <p>getTime.</p>
	 *
	 * @return a long.
	 */
	long getTime();
	/**
	 * <p>timePercent.</p>
	 *
	 * @return a float.
	 */
	float timePercent();
	/**
	 * <p>mapGravity.</p>
	 */
	void mapGravity();
	/**
	 * <p>pickDiamond.</p>
	 */
	void pickDiamond();

	/**
	 * Gets the observable.
	 *
	 * @return the observable
	 */
	Observable getObservable();
}
