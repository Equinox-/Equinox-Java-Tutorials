package com.pi.math3d;

public class Vector {
	public double x, y, z;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector) o;
			return v.x == x && v.y == y && v.z == z;
		}
		return false;
	}

	@Override
	public Object clone() {
		return new Vector(x, y, z);
	}

	@Override
	public String toString() {
		return "<" + x + "," + y + "," + z + ">";
	}

	public Vector reverse() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public static Vector reverse(Vector v) {
		return ((Vector) v.clone()).reverse();
	}

	public Vector add(double scalar) {
		x = x + scalar;
		y = y + scalar;
		z = z + scalar;
		return this;
	}

	public Vector subtract(double scalar) {
		return add(-scalar);
	}

	public static Vector add(Vector v, double scalar) {
		return ((Vector) v.clone()).add(scalar);
	}

	public static Vector subtract(Vector v, double scalar) {
		return ((Vector) v.clone()).subtract(scalar);
	}

	public Vector add(Vector a) {
		x += a.x;
		y += a.y;
		z += a.z;
		return this;
	}

	public Vector subtract(Vector a) {
		x -= a.x;
		y -= a.y;
		z -= a.z;
		return this;
	}

	public static Vector add(Vector a, Vector b) {
		return ((Vector) a.clone()).add(b);
	}

	public static Vector subtract(Vector a, Vector b) {
		return ((Vector) a.clone()).subtract(b);
	}

	public double magnitude() {
		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public double magnitude(Vector a) {
		return a.magnitude();
	}

	public Vector normalize() {
		double mag = magnitude();
		x /= mag;
		y /= mag;
		z /= mag;
		return this;
	}

	public static Vector normalize(Vector a) {
		return ((Vector) a.clone()).normalize();
	}

	public static Vector crossProduct(Vector a, Vector b) {
		double x = (a.y * b.z) - (a.z - b.y);
		double y = (a.z * b.x) - (a.x * b.z);
		double z = (a.x * b.y) - (a.y * b.x);
		return new Vector(x, y, z);
	}

	public static double dotProduct(Vector a, Vector b) {
		return (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
	}

	public double getAngleTo(Vector b) {
		return getAngleBetween(this, b);
	}

	public static double getAngleBetween(Vector a, Vector b) {
		double cos = dotProduct(a, b) / (a.magnitude() * b.magnitude());
		return Math.acos(cos);
	}

	public Vector multiply(TransMatrix trans) {
		double resX = trans.get(0, 3) + (x * trans.get(0, 0)) + (y * trans.get(1, 0))
				+ (z * trans.get(2, 0));
		double resY = trans.get(1, 3) + (x * trans.get(0, 1)) + (y * trans.get(1, 1))
				+ (z * trans.get(2, 1));
		double resZ = trans.get(2, 3) + (x * trans.get(0, 2)) + (y * trans.get(1, 2))
				+ (z * trans.get(2, 2));
		this.x = resX;
		this.y = resY;
		this.z = resZ;
		return this;
	}

	public static Vector multiply(Vector vec, TransMatrix trans) {
		return ((Vector) vec.clone()).multiply(trans);
	}
}
