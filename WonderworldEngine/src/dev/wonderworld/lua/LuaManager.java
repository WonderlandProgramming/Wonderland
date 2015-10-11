package dev.wonderworld.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaManager {
	public static LuaValue parseFile(String path){
		Globals _g = JsePlatform.standardGlobals();
		try{
			return _g.loadfile(path);
		}catch(Exception e){
			return null;
		}
	}
}
