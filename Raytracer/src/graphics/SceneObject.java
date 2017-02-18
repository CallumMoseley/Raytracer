package graphics;

import geometry.Mesh;
import geometry.Ray;
import geometry.Vector3;
import utility.Intersection;

public class SceneObject {
	private Mesh mesh;
	
	public SceneObject() {
		mesh = new Mesh();
	}
	
	public SceneObject(Mesh m) {
		mesh = m;
	}
	
	public Mesh getMesh() {
		return mesh;
	}

	public Intersection intersect(Ray projection) {
		Vector3 closest = Vector3.INFINITY;
		int tri = 0;
		for (int i = 0; i < mesh.getTriangles().length; i++) {
			Vector3 intersection = projection.intersectTriangle(mesh.getVertices()[mesh.getTriangles()[i][0]],
																mesh.getVertices()[mesh.getTriangles()[i][1]],
																mesh.getVertices()[mesh.getTriangles()[i][2]]);
			if (intersection != Vector3.INFINITY && intersection.subtract(projection.pos).getLength() < closest.subtract(projection.pos).getLength()) {
				closest = intersection;
				tri = i;
			}
		}
		
		if (closest != Vector3.INFINITY) {
			return new Intersection(closest, tri);
		}
		
		return new Intersection();
	}
}
