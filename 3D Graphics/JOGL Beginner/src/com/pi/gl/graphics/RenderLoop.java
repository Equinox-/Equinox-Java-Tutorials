package com.pi.gl.graphics;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.jogamp.common.nio.Buffers;

public class RenderLoop implements GLEventListener {
	private DisplayManager displayManager;

	public RenderLoop(DisplayManager displayManager) {
		this.displayManager = displayManager;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// Called when the GL instance is created
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// Called when the animator stops
	}

	FloatBuffer vertexBuffer = (FloatBuffer) Buffers
			.newDirectFloatBuffer(12)
			.put(new float[] { 150, 150, -20, -150f, 150f, -20f, 150f, -150f,
					-20f, -150f, -150f, -20f }).flip();

	FloatBuffer colorBuffer = (FloatBuffer) Buffers.newDirectFloatBuffer(12)
			.put(new float[] { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }).flip();

	ByteBuffer indexBuffer = (ByteBuffer) Buffers.newDirectByteBuffer(6)
			.put(new byte[] { 0, 1, 2, 1, 2, 3 }).flip();

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		// Define the world projection
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(drawable.getWidth() / -2, drawable.getWidth() / 2,
				drawable.getHeight() / 2, drawable.getHeight() / -2, -500, 500);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		displayManager.getCamera().translate(gl);
		// This is where the main render logic occurs.
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, vertexBuffer);
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glColorPointer(3, GL.GL_FLOAT, 0, colorBuffer);

		gl.glDrawElements(GL.GL_TRIANGLES, 6, GL2.GL_UNSIGNED_BYTE, indexBuffer);

		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// This is where we would perform any GUI position updates
	}
}
