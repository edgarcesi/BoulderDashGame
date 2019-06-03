package contract;

/**
 * The Interface IView.
 *
 * @author Jean-Aymeric Diet
 * @version $Id: $Id
 */
public interface IView {

	/**
	 * Prints the message.
	 *
	 * @param message
	 *          the message
	 */
	void printMessage(final String message);

	/**
	 * <p>playWinMusic.</p>
	 */
	void playWinMusic();
	/**
	 * <p>playGameoverMusic.</p>
	 */
	void playGameoverMusic();
}
