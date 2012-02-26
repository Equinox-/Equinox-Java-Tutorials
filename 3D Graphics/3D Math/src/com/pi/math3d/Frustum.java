package com.pi.math3d;

import java.awt.geom.Point2D;

public class Frustum {
	int zNear, zFar;
	double screenWidth2, screenHeight2;
	double verticalRadians, screenDepth;

	public Frustum(int zNear, int zFar, double verticalRadians,
			double screenWidth, double screenHeight) {
		if (zNear <= 0)
			throw new IllegalArgumentException("Invalid Near Bound!");
		this.zNear = zNear;
		this.zFar = zFar;
		this.screenWidth2 = screenWidth / 2d;
		this.screenHeight2 = screenHeight / 2d;
		this.verticalRadians = verticalRadians;
		this.screenDepth = ((double) screenHeight)
				/ Math.tan(verticalRadians / 2d);
	}

	public void setSize(double screenWidth, double screenHeight) {
		this.screenWidth2 = screenWidth / 2d;
		this.screenHeight2 = screenHeight / 2d;
		this.screenDepth = ((double) screenHeight)
				/ Math.tan(verticalRadians / 2d);
	}

	public Point2D toScreen(Vector point) {
		if (point.z >= zNear && point.z <= zFar) {
			double _x = ((point.x / point.z * screenDepth) + screenWidth2);
			double _y = ((point.y / point.z * screenDepth) + screenHeight2);
			return new Point2D.Double(_x, (screenHeight2 * 2d) - _y);
		}
		return null;
	}
}
