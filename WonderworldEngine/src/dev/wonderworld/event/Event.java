/**
 * 
 */
package dev.wonderworld.event;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @vesion 0.1
 */
public class Event {
	private int type;
	boolean handled;
	
	protected Event(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
