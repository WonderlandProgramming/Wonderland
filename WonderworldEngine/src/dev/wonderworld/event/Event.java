/**
 * 
 */
package dev.wonderworld.event;

/**
 * This is the base Event and it can be extendet to create more complext Events.
 * 
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @version 0.1
 */
public class Event {
	private int type;
	boolean handled;

	/**
	 * Creates a Event with the given Type
	 * 
	 * @param type
	 *            the type of the Event
	 */
	protected Event(int type) {
		this.type = type;
	}

	/**
	 * Gets the ID of the Event
	 * 
	 * @return the ID / Type
	 */
	public int getType() {
		return type;
	}
}
