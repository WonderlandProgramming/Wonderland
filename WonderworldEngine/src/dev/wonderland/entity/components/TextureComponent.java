package dev.wonderland.entity.components;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import dev.wonderland.entity.Component;
import dev.wonderland.gfx.textures.TexturedModel;
import dev.wonderworld.lua.LuaInteractable;

public class TextureComponent extends Component implements LuaInteractable{

	public static final int ID = 3;
	
	private TexturedModel model;
	private float scale;
	
	public TextureComponent(TexturedModel model, float scale) {
		super(ID);
		this.model = model;
		this.scale = scale;
	}

	@Override
	public LuaValue getLua() {
		LuaTable t1 = new LuaTable();
		
		return t1;
	}

	public TexturedModel getModel() {
		return model;
	}

	public float getScale(){
		return scale;
	}
}
