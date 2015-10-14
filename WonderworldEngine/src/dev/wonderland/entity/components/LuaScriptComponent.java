package dev.wonderland.entity.components;

import dev.wonderland.entity.Component;

public class LuaScriptComponent extends Component{

	public static final int ID = 2;
	
	private String path;
	
	public LuaScriptComponent(String scriptPath) {
		super(ID);
		this.path = scriptPath;
	}
	
	public String getLua(){
		return path;
	}
	
}
