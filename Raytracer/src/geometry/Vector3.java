package geometry;

import java.awt.Color;

public class Vector3 {
	public static final Vector3 ZERO = new Vector3();
	public static final Vector3 INFINITY = new Vector3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	
	public double x;
	public double y;
	public double z;
	
	public Vector3() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public Vector3 add(Vector3 v) {
		return add(this, v);
	}
	
	public void addToThis(Vector3 v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}
	
	public Vector3 subtract(Vector3 v) {
		return subtract(this, v);
	}

	public void subtractFromThis(Vector3 v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}
	
	public Vector3 multiply(double scalar) {
		return new Vector3(x * scalar, y * scalar, z * scalar);
	}
	
	public void multiplyBy(double scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
	}
	
	public Vector3 getNegative() {
		return multiply(-1);
	}

	public void negate() {
		multiplyBy(-1);
	}

	public Vector3 getNormalized() {
		double l = getLength();
		return new Vector3(x / l, y / l, z / l);
	}

	public void normalize() {
		double l = getLength();
		x /= l;
		y /= l;
		z /= l;
	}

	public double getLength() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public double getLengthSq() {
		return x * x + y * y + z * z;
	}

	public Vector3 getWithLength(double scalar) {
		return getNormalized().multiply(scalar);
	}

	public void setLength(double scalar) {
		normalize();
		multiply(scalar);
	}

	public Vector3 getCross(Vector3 v) {
		return crossProduct(this, v);
	}

	public double getDot(Vector3 v) {
		return dotProduct(this, v);
	}
	
	public Vector3 rotateAround(Vector3 axis, double angle) {
		Vector3 normalAxis = axis.getNormalized();
		return multiply(Math.cos(angle))
			   .add(normalAxis.getCross(this).multiply(Math.sin(angle)))
			   .add(normalAxis.multiply(normalAxis.getDot(this) * (1 - Math.cos(angle))));
	}
	
	public void rotate(Vector3 axis, double angle) {
		Vector3 rotated = this.rotateAround(axis, angle);
		x = rotated.x;
		y = rotated.y;
		z = rotated.z;
	}
	
	public Color getColour() {
		return new Color((int) (255 * Math.min(1, x)), (int) (255 * Math.min(1, y)), (int) (255 * Math.min(1, z)));
	}

	@Override
	public Vector3 clone() {
		return new Vector3(x, y, z);
	}

	@Override
	public String toString() {
		return String.format("Vector3[%.2f, %.2f, %.2f]", x, y, z);
	}
	
	public boolean equals(Vector3 other) {
		return x == other.x && y == other.y && z == other.z;
	}

	public static Vector3 add(Vector3 a, Vector3 b) {
		return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	public static Vector3 subtract(Vector3 a, Vector3 b) {
		return new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	public static Vector3 crossProduct(Vector3 a, Vector3 b) {
		return new Vector3(a.y * b.z - a.z * b.y,
						   a.z * b.x - a.x * b.z,
						   a.x * b.y - a.y * b.x);
	}
	
	public static double dotProduct(Vector3 a, Vector3 b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	public static Vector3 rotate(Vector3 v, Vector3 axis, double angle) {
		Vector3 normalAxis = axis.getNormalized();
		return v.multiply(Math.cos(angle))
			   .add(normalAxis.getCross(v).multiply(Math.sin(angle)))
			   .add(normalAxis.multiply(normalAxis.getDot(v) * (1 - Math.cos(angle))));
	}
}