/**
 * 
 */
package dev.wonderworld.event.events.luaEvents;

import org.luaj.vm2.LuaTable;

import dev.wonderland.entity.Entity;
import dev.wonderworld.event.events.LuaBaseEvent;
import dev.wonderworld.lua.EntityConverter;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 11.10.2015
 * @version 0.1
 */
public class onInRangeEvent extends LuaBaseEvent {

	public static final int ID = 1000;
	public static final String name = "onInRange";
	
	private Entity p1, p2;
	
	public onInRangeEvent(String luaFile, Entity p1, Entity p2) {
		super(ID, luaFile);
		this.p1 = p1;
		this.p2 = p2;
		
		this.call();
	}

	/** (non-Javadoc)
	 * @see dev.wonderworld.event.events.LuaBaseEvent#call()
	 */
	@Override
	public void call() {
		LuaTable t1 = new LuaTable();
		t1.set("p2", EntityConverter.convert(p2));
		t1.set("p1", EntityConverter.convert(p1));
		
		this.callMethod("onInRange", t1);
	}

	public Entity getP1() {
		return p1;
	}

	public Entity getP2() {
		return p2;
	}
}
