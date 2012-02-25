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
}