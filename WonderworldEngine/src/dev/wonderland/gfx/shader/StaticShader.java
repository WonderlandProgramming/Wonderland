package dev.wonderland.gfx.shader;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{
	private static final String VERTEX_FILE = "src/dev/wonderland/gfx/shader/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/dev/wonderland/gfx/shader/fragmentShader.txt";

	 private int location_transformationMatrix;
	 
     public StaticShader() {
             super(VERTEX_FILE, FRAGMENT_FILE);
     }

     @Override
     protected void bindAttributes() {
             super.bindAttribute(0, "position");    
             super.bindAttribute(1, "textureCoords");
     }

     @Override
     protected void getAllUniformLocation() {
             super.getUniformLocation("transformationMatrix");
     }
    
     public void loadTransformationMatrix(Matrix4f matrix) {
             super.loadMatrix(location_transformationMatrix, matrix);
     }
}