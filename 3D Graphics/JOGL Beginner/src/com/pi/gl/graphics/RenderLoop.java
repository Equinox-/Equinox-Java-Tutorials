package com.pi.gl.graphics;

import java.io.File;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import com.pi.gl.graphics.objects.MeshLoader;
import com.pi.gl.graphics.objects.HeightmapMesh;
import com.pi.gl.graphics.objects.Vector3D;

public class RenderLoop implements GLEventListener {
	private DisplayManager displayManager;
	private HeightmapMesh mesh;

	public RenderLoop(DisplayManager displayManager) {
		this.displayManager = displayManager;
		float[][] hMap;
		try {
			hMap = MeshLoader.loadHeightMap(new File("heightmap.png"), 0,
					255);
		} catch (Exception e) {
			hMap = new float[50][50];
		}
		mesh = new HeightmapMesh(hMap, 5, new Vector3D(-5 * (hMap.length / 2f),
				0, -5 * (hMap[0].length / 2f)));
	}

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
		displayManager.getCamera().translate(gl);
		// This is where the main render logic occurs.
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, mesh.getVertexBuffer());
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		gl.glColorPointer(3, GL.GL_FLOAT, 0, mesh.getColorBuffer());

		gl.glDrawElements(GL.GL_TRIANGLES, mesh.getIndexCount(),
				GL2.GL_UNSIGNED_INT, mesh.getIndexBuffer());

		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// This is where we would perform any GUI position updates
	}
}
