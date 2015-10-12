package dev.wonderworld.event.events;

import java.io.FileNotFoundException;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.LuajavaLib;

import dev.wonderworld.event.Event;

public abstract class LuaBaseEvent extends Event {

	protected String script;

	protected LuaBaseEvent(int ID, String luaFile) {
		super(ID);
		script = luaFile;
	}

	public abstract void call();

	protected void callMethod(String name, LuaValue parameters) {
		try {
			if (script == null || script.isEmpty())
				throw new FileNotFoundException();

			Globals user_globals = new Globals();
			user_globals.load(new JseBaseLib());
			user_globals.load(new PackageLib());
			user_globals.load(new Bit32Lib());
			user_globals.load(new TableLib());
			user_globals.load(new StringLib());
			user_globals.load(new JseMathLib());
			user_globals.load(new LuajavaLib());

			LoadState.install(user_globals);
			LuaC.install(user_globals);

			user_globals.get("dofile").call(LuaValue.valueOf(script));
			LuaValue toCall = user_globals.get(name);
			if (toCall != null && toCall != LuaValue.NIL)
				toCall.call(parameters);

		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			return;
		}
	}
}
