package code.model;

/**
 * Used in accordance with the observer pattern
 */
public interface Observer {
	/**
	 * Called in order to update the underlying model based upon the state of the user interface
	 */
	public void update();

}
