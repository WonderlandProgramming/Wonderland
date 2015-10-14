package dev.wonderworld.event.events.luaEvents;

import org.luaj.vm2.LuaTable;

import dev.wonderland.entity.Entity;
import dev.wonderland.entity.components.LuaScriptComponent;
import dev.wonderworld.event.events.LuaBaseEvent;
import dev.wonderworld.lua.EntityConverter;

public class onTileJoinEvent extends LuaBaseEvent{

	public static final int ID = 1001;
	
	private Entity e;
	
	protected onTileJoinEvent(Entity e) {
		super(ID, e.containsComponent(LuaScriptComponent.ID) ? (LuaScriptComponent)e.getComponent(LuaScriptComponent.ID) : null);
		this.e = e;
	}

	@Override
	public void call() {
		LuaTable t1 = new LuaTable();
		t1.set("joined", EntityConverter.convert(e));
		this.callMethod("onEntityJoin", t1);
	}

}
