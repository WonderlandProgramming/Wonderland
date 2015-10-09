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
	private String type;
	boolean handled;
	
	protected Event(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
