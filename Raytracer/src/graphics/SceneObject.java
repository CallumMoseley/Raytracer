package graphics;

import java.awt.Color;

import geometry.Ray;
import geometry.Vector3;

public class SceneObject {
	private Color color;
	private Vector3[] vertices;
	private int[][] triangles;
	
	public SceneObject() {
		color = Color.RED;
		vertices = new Vector3[3];
		vertices[0] = new Vector3(-10, 0, -5);
		vertices[1] = new Vector3(10, 0, -5);
		vertices[2] = new Vector3(0, 10, -5);
		triangles = new int[1][3];
		triangles[0][0] = 0;
		triangles[0][1] = 1;
		triangles[0][2] = 2;
	}

	public Color getColor() {
		return color;
	}

	public Vector3 intersect(Ray projection) {
		for (int i = 0; i < triangles.length; i++) {
			Vector3 intersection = projection.intersectTriangle(vertices[triangles[i][0]], vertices[triangles[i][1]], vertices[triangles[i][2]]);
			if (intersection != Vector3.INFINITY) {
				return intersection;
			}
		}
		
		return Vector3.INFINITY;
	}
}
