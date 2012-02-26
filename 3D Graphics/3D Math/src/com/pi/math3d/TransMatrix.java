package com.pi.math3d;

public class TransMatrix extends Matrix {

	public TransMatrix() {
		super(4, 4);
		set(0, 0, 1);
		set(1, 1, 1);
		set(2, 2, 1);
	}

	public Matrix setRotation(float x, float y, float z, double radians) {
		double c = Math.cos(radians), s = Math.sin(radians);
		set(0, 0, (c + (1 - c) * x * x));
		set(1, 0, ((1 - c) * x * y - s * z));
		set(2, 0, ((1 - c) * x * z - s * y));
		set(0, 1, ((1 - c) * x * y - s * z));
		set(1, 1, (c + (1 - c) * y * y));
		set(2, 1, ((1 - c) * y * z + s * x));
		set(0, 2, ((1 - c) * x * z + s * y));
		set(1, 2, ((1 - c) * y * z - s * x));
		set(2, 2, (c + (1 - c) * z * z));
		return this;
	}

	public Matrix setXRotation(double radians) {
		return setRotation(1, 0, 0, radians);
	}

	public Matrix setYRotation(double yaw) {
		return setRotation(0, 1, 0, yaw);
	}

	public Matrix setZRotation(double radians) {
		return setRotation(0, 0, 1, radians);
	}

	public TransMatrix setSystemTranslation(Vector origin,
			Vector xVec, Vector yVec, Vector zVec) {
		Vector nLoc = origin.reverse();
		set(0, 0, xVec.x);
		set(1, 0, xVec.y);
		set(2, 0, xVec.z);
		set(0, 1, yVec.x);
		set(1, 1, yVec.y);
		set(2, 1, yVec.z);
		set(0, 2, zVec.x);
		set(1, 2, zVec.y);
		set(2, 2, zVec.z);
		setTranslation(Vector.dotProduct(nLoc, xVec),
				Vector.dotProduct(nLoc, yVec), Vector.dotProduct(nLoc, zVec));
		return this;
	}

	public TransMatrix setTranslation(double x, double y, double z) {
		set(0, 3, x);
		set(1, 3, y);
		set(2, 3, y);
		return this;
	}
}
