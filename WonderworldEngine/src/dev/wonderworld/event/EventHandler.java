/**
 * 
 */
package dev.wonderworld.event;

/**
 * This Interface needs to be implemented to trigger on a call of a Event by the {@link EVENT_BUS}.
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @version 0.1
 */
public interface EventHandler {
	/**
	 * This Method is called if the Handler is called by the {@link EVENT_BUS}.
	 * @param event The event that was called.
	 * @return If event was successfull.
	 */
	public boolean onEvent(Event event);
}
