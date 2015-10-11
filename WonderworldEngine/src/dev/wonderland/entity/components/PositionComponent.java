/**
 * 
 */
package dev.wonderland.entity.components;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import dev.wonderland.entity.Component;
import dev.wonderworld.lua.LuaInteractable;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 11.10.2015
 * @version 0.1
 */
public class PositionComponent extends Component implements LuaInteractable {

	public static final int ID = 1;

	private static final int TILESIZE = 32;

	private float xPos, yPos;
	private int xTile, yTile;

	public PositionComponent(float x, float y) {
		super(ID);
		this.xPos = x;
		this.yPos = y;
		this.xTile = (int) x / TILESIZE;
		this.yTile = (int) y / TILESIZE;
	}

	public PositionComponent(int xTile, int yTile) {
		super(ID);
		this.xTile = xTile;
		this.yTile = yTile;
		this.xPos = (xTile * TILESIZE) - (TILESIZE / 2);
		this.yPos = (yTile * TILESIZE) - (TILESIZE / 2);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see dev.wonderworld.lua.LuaInteractable#getLua()
	 */
	@Override
	public LuaValue getLua() {
		LuaTable t = new LuaTable();
		
		//Variables
		t.set("xTile", xTile);
		t.set("yTile", yTile);
		t.set("xPos", xPos);
		t.set("yPos", yPos);
		
		//Functions
		t.set("move", move.s_metatable);

		return t;
	}

	private static LuaValue move(LuaValue xPos, LuaValue yPos) {
		try {
			xPos.checkint();
			yPos.checkint();

			move(xPos, yPos);
			
		} catch (LuaError error) {
			return LuaValue.error("Did not get a int!");
		}
		return LuaValue.TRUE;
	}

	private class move extends TwoArgFunction {
		@Override
		public LuaValue call(LuaValue xPos, LuaValue yPos) {
			return move(xPos, yPos);
		}
	}

	public void move(float xDir, float yDir) {
		this.xPos += xDir;
		this.yPos += yDir;
		this.xTile = (int) xPos / TILESIZE;
		this.yTile = (int) yPos / TILESIZE;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public int getxTile() {
		return xTile;
	}

	public int getyTile() {
		return yTile;
	}
}
