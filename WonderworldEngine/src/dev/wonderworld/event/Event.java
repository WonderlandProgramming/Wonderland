/**
 * 
 */
package dev.wonderworld.event;

import dev.wonderworld.event.type.Type;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 09.10.2015
 * @vesion 0.1
 */
public class Event {
	private Type type;
	boolean handled;
	
	protected Event(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
}
