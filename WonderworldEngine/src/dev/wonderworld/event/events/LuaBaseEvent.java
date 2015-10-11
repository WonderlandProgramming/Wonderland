package dev.wonderworld.event.events;

import org.luaj.vm2.LuaValue;

import dev.wonderworld.event.Event;
import dev.wonderworld.lua.LuaManager;

public abstract class LuaBaseEvent extends Event{

	public static final int ID = 1001;
	
	protected LuaValue script;
	
	protected LuaBaseEvent(String luaFile) {
		super(ID);
		script = LuaManager.parseFile(luaFile);
		
		call();
	}
	
	public abstract void call();
	
	protected void callMethod(String name, LuaValue parameters){
		try{
			script.invokemethod(name, parameters);
		}catch(Exception e){
			return;
		}
		
	}
}
