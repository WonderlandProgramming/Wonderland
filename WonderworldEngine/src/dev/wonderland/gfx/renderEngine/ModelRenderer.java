package dev.wonderland.gfx.renderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import dev.wonderland.entity.Entity;
import dev.wonderland.entity.components.PositionComponent;
import dev.wonderland.entity.components.TextureComponent;
import dev.wonderland.gfx.shader.StaticShader;
import dev.wonderland.gfx.textures.TexturedModel;
import dev.wonderworld.util.Maths;

public class ModelRenderer {
	static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	private Matrix4f projectionMatrix;

	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 0.3f, 0, 1);
	}

	public ModelRenderer(StaticShader shader) {
		createProjectionMatrix();
		shader.start();
		shader.loadTransformationMatrix(projectionMatrix);
		shader.stop();

	}

	public void render(Entity entity, StaticShader shader) {
		if(!entity.containsComponent(TextureComponent.ID) && !entity.containsComponent(PositionComponent.ID)) return;
		
		TextureComponent texture = (TextureComponent) entity.getComponent(TextureComponent.ID);
		TexturedModel texturedModel = texture.getModel();
		
		PositionComponent pos = (PositionComponent) entity.getComponent(PositionComponent.ID);
		
		RawModel model = texturedModel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(pos.getPosition(), pos.getxRot(),
				pos.getyRot(), pos.getzRot(), texture.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio;
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
}