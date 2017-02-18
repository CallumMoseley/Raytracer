package utility;

import geometry.Vector3;

public class Intersection {
	public Vector3 point;
	public int triangle;
	
	public Intersection() {
		point = Vector3.INFINITY;
		triangle = -1;
	}
	
	public Intersection(Vector3 p, int tri) {
		point = p;
		triangle = tri;
	}
}
