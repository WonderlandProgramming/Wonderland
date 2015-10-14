/**
 * 
 */
package dev.wonderworld.main;

import org.lwjgl.opengl.Display;

import dev.wonderland.entity.Entity;
import dev.wonderland.entity.components.LuaScriptComponent;
import dev.wonderland.entity.components.PositionComponent;
import dev.wonderland.entity.components.TextureComponent;
import dev.wonderland.gfx.renderEngine.DisplayManager;
import dev.wonderland.gfx.renderEngine.Loader;
import dev.wonderland.gfx.renderEngine.RawModel;
import dev.wonderland.gfx.renderEngine.Renderer;
import dev.wonderland.gfx.shader.StaticShader;
import dev.wonderland.gfx.textures.ModelTexture;
import dev.wonderland.gfx.textures.TexturedModel;
import dev.wonderworld.event.EVENT_BUS;
import dev.wonderworld.event.Event;
import dev.wonderworld.event.events.luaEvents.onInRangeEvent;

/**
 * @author Lukas Kannenberg, Lukas Peer
 * @since 10.10.2015
 * @vesion 0.1
 */
public class MainClass {

	public static void main(String[] args) {

		EVENT_BUS.registerEventHandler(onInRangeEvent.ID, (Event e) -> (test(e)));

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = { -0.5f, 0.5f, 0, -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0 };

		int[] indices = { 0, 1, 3, 3, 1, 2 };

		float[] textureCoords = { 0, 0, 0, 1, 1, 1, 1, 0, 0, 0 };

		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		TexturedModel texturedModel = new TexturedModel(model, texture);

		Entity entity = new Entity().addComponent(new TextureComponent(texturedModel, 1f))
				.addComponent(new PositionComponent(0, 0, 1, 0, 0, 0))
				.addComponent(new LuaScriptComponent("res/luaTest.lua"));

		Entity p2 = new Entity().addComponent(new PositionComponent(50, 50));

		while (!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();

			EVENT_BUS.callEvent(new onInRangeEvent(entity, p2));

		}

		shader.cleanUP();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

	public static boolean test(Event e) {
		System.out.println(((PositionComponent) ((onInRangeEvent) e).getP1().getComponent(PositionComponent.ID)));
		return true;
	}
}
