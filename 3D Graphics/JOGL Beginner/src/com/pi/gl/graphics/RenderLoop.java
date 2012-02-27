package com.pi.gl.graphics;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

public class RenderLoop implements GLEventListener {

	@Override
	public void init(GLAutoDrawable drawable) {
		// Called when the GL instance is created
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// Called when the animator stops
	}

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
		// This is where the main render logic occurs.
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3f(1f, 0f, 0f);
		gl.glVertex3f(150f, 150f, -20f);
		gl.glColor3f(0f, 1f, 0f);
		gl.glVertex3f(-200f, 0f, -20f);
		gl.glColor3f(0f, 0f, 1f);
		gl.glVertex3f(0f, -200f, -20f);
		gl.glEnd();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// This is where we would perform any GUI position updates
	}
}
