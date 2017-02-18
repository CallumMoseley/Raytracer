package geometry;

import java.awt.Color;

public class Mesh {
	private Vector3[] vertices;
	private int[][] triangles;
	private Color[] triangleColours;
	
	public Mesh() {
		
	}
	
	public Mesh(Vector3[] verts, int[][] tris, Color[] cols) {
		vertices = verts;
		triangles = tris;
		triangleColours = cols;
	}
	
	public void setMesh(Vector3[] verts, int[][] tris, Color[] cols) {
		vertices = verts;
		triangles = tris;
		triangleColours = cols;
	}
	
	public Vector3[] getVertices() {
		return vertices;
	}
	
	public int[][] getTriangles() {
		return triangles;
	}
	
	public Color[] getColours() {
		return triangleColours;
	}
}
