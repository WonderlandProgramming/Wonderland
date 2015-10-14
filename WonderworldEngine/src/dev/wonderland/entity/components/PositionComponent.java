/**
 * 
 */
package dev.wonderland.entity.components;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.lwjgl.util.vector.Vector3f;

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

	private float xPos, yPos, zPos;
	private float xRot, yRot, zRot;

	private int xTile, yTile;

	public PositionComponent(float x, float y, float z, float xRot, float yRot, float zRot) {
		super(ID);
		this.xPos = x;
		this.yPos = y;
		this.zPos = z;
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
		t.set("zPos" ,zPos);
		t.set("xRot", xRot);
		t.set("yRot", yRot);
		t.set("zRot", zRot);
		
		//Functions
		t.set("move", new move());
		t.set("rotate", new rotate());

		return t;
	}

	private class move extends TwoArgFunction {
		@Override
		public LuaValue call(LuaValue xPos, LuaValue zPos) {
			try {
				increasePosition((float)xPos.checkdouble(), 0, (float)zPos.checkdouble());
			} catch (LuaError error) {
				return LuaValue.error("Did not get a double!");
			}
			return LuaValue.TRUE;
		}
	}
	
	private class rotate extends ThreeArgFunction {
		@Override
		public LuaValue call(LuaValue xDir, LuaValue yDir, LuaValue zDir) {
			try {
				increaseRotation((float)xDir.checkdouble(), (float)yDir.checkdouble(), (float)zDir.checkdouble());
			} catch (LuaError error) {
				return LuaValue.error("Did not get a double!");
			}
			return LuaValue.TRUE;
		}
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getzPos() {
		return zPos;
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
	
	public Vector3f getPosition(){
		return new Vector3f(xPos, yPos, zPos);
	}

	@Override
	public String toString() {
		return "PositionComponent [xPos=" + xPos + ", yPos=" + yPos + ", xTile=" + xTile + ", yTile=" + yTile + "]";
	}

	public float getxRot() {
		return xRot;
	}

	public float getyRot() {
		return yRot;
	}

	public float getzRot() {
		return zRot;
	}

	public void increasePosition(float f, float i, float j) {
		this.xPos += f;
		this.yPos += i;
		this.zPos += j;
	}

	public void increaseRotation(float i, float j, float k) {
		this.xRot += i;
		this.yRot += j;
		this.zRot += k;
	}
}
