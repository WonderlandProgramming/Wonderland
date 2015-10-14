package dev.wonderworld.lua;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import dev.wonderland.entity.Component;
import dev.wonderland.entity.Entity;

public class EntityConverter {
	public static LuaValue convert(Entity e) {
		LuaTable t = new LuaTable();

		t.set("class", "entity");
		
		if(e == null) return t;

		LuaTable components = new LuaTable();
		
		for (Component c : e.getComponents()) {
			if(c == null) break;
			if (c instanceof LuaInteractable) {
				components.set(c.getClass().getSimpleName(), ((LuaInteractable) c).getLua());
			}
		}
		t.set("components", components);
		return t;
	}
}
