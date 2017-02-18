package geometry;

public class Ray {
	
	public Vector3 pos;
	public Vector3 dir;
	
	public Ray() {
		pos = new Vector3();
		dir = new Vector3(0, 0, 1);
	}
	
	public Ray(Vector3 p, Vector3 d) {
		pos = p;
		dir = d;
	}
	
	public Ray(double px, double py, double pz, double dx, double dy, double dz) {
		pos = new Vector3(px, py, pz);
		dir = new Vector3(dx, dy, dz);
	}
	
	public static Ray fromTo(Vector3 a, Vector3 b) {
		return new Ray(a, Vector3.subtract(b, a));
	}
	
	// Basically a straight copy from
	// http://www.cs.virginia.edu/~gfx/Courses/2003/ImageSynthesis/papers/Acceleration/Fast%20MinimumStorage%20RayTriangle%20Intersection.pdf
	public Vector3 intersectTriangle(Vector3 v0, Vector3 v1, Vector3 v2) {
		Vector3 edge1 = v1.subtract(v0);
		Vector3 edge2 = v2.subtract(v0);
		
		Vector3 p = dir.getNormalized().getCross(edge2);
		double det = edge1.getDot(p);
		
		if (det < 0.000001)
			return Vector3.INFINITY;
		
		Vector3 t = pos.subtract(v0);
		double u = t.getDot(p);
		if (u < 0 || u > det)
			return Vector3.INFINITY;
		
		Vector3 q = t.getCross(edge1);
		
		double v = dir.getNormalized().getDot(q);
		if (v < 0 || u + v > det)
			return Vector3.INFINITY;
		
		double t0 = edge2.getDot(q);
		double invDet = 1 / det;
		
		t0 *= invDet;
		u *= invDet;
		v *= invDet;
		
		if (t0 > 0)
			return pos.add(dir.getWithLength(t0));
		return Vector3.INFINITY;
		
//		if (det > -0.000001 && det < 0.000001)
//			return Vector3.INFINITY;
//		
//		double invDet = 1 / det;
//		
//		Vector3 t = pos.subtract(v0);
//		
//		double u = t.getDot(p) * invDet;
//		if (u < 0 || u > 1)
//			return Vector3.INFINITY;
//		
//		Vector3 q = t.getCross(edge1);
//		
//		double v = dir.getDot(q) * invDet;
//		if (v < 0 || u + v > 1) 
//			return Vector3.INFINITY;
//		double t0 = edge2.getDot(q) * invDet;
//		if (t0 > 0)
//			return pos.add(dir.getWithLength(t0));
//		return Vector3.INFINITY;
	}
	
	@Override
	public String toString() {
		return String.format("Ray[p[%.2f, %.2f, %.2f], d[%.2f, %.2f, %.2f]]", pos.x, pos.y, pos.z, dir.x, dir.y, dir.z);
	}
}
