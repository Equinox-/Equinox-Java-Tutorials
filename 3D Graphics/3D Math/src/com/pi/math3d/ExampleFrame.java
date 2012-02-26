package com.pi.math3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ExampleFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private Frustum frust;
	private Camera3rdPerson cam;
	private BufferStrategy buffer;

	private Vector[][] terrain;

	public ExampleFrame() {
		super("Math3D Example");
		setSize(500, 500);
		setLocation(0, 0);

		frust = new Frustum(1, 500, Math.PI / 2d, getWidth(), getHeight());
		cam = new Camera3rdPerson(50);
		addKeyListener(cam);

		terrain = new Vector[25][25];
		for (int x = 0; x < terrain.length; x++) {
			for (int z = 0; z < terrain[x].length; z++) {
				terrain[x][z] = new Vector((x - (terrain.length / 2)) * 10, 0,
						(z - (terrain[x].length / 2)) * 10);
			}
		}

		setVisible(true);

		createBufferStrategy(2);
		buffer = getBufferStrategy();

		setResizable(false);
		setIgnoreRepaint(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		while (isVisible()) {
			paint3D();
		}
	}

	public Point2D toScreen(Vector v) {
		return frust.toScreen(cam.translate((Vector) v.clone()));
	}
	
	public void paint3D() {
		if (cam.doModification()) {
			Graphics g = buffer.getDrawGraphics();
			g.clearRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString(cam.toString(), 50, 50);
			// Repaint loop
			Point[][] indices = new Point[terrain.length][terrain[0].length];
			for (int x = 0; x < terrain.length; x++) {
				for (int z = 0; z < terrain[x].length; z++) {
					Point2D screen = toScreen(terrain[x][z]);
					if (screen != null) {
						indices[x][z] = new Point((int) screen.getX(),
								(int) screen.getY());
					} else {
						indices[x][z] = null;
					}
					if (x > 0 && z > 0 && indices[x][z] != null
							&& indices[x - 1][z] != null
							&& indices[x - 1][z - 1] != null
							&& indices[x][z - 1] != null) {
						Polygon poly = new Polygon();
						poly.addPoint(indices[x][z].x, indices[x][z].y);
						poly.addPoint(indices[x - 1][z].x, indices[x - 1][z].y);
						poly.addPoint(indices[x - 1][z - 1].x,
								indices[x - 1][z - 1].y);
						poly.addPoint(indices[x][z - 1].x, indices[x][z - 1].y);
						g.drawPolygon(poly);
					}
				}
			}
			g.dispose();
			buffer.show();
		}
	}

	public static void main(String[] args) {
		new ExampleFrame();
	}
}
