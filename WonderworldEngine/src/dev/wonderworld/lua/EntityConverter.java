package dev.wonderworld.lua;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import dev.wonderland.entity.Component;
import dev.wonderland.entity.Entity;

public class EntityConverter {
	public static LuaValue convert(Entity e) {
		LuaTable t = new LuaTable();

		t.set("class", "entity");

		LuaTable components = new LuaTable();
		for (Component c : e.getComponents()) {
			if (c instanceof LuaInteractable) {
				components.set(c.getID(), ((LuaInteractable) c).getLua());
			}
		}
		t.set("components", components);
		return t;
	}
}
