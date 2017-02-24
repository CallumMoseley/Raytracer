package geometry;

public class Mesh {
	private Vector3[] vertices;
	private int[][] triangles;
	private Vector3[] triangleColours;
	
	public Mesh() {
		
	}
	
	public Mesh(Vector3[] verts, int[][] tris, Vector3[] cols) {
		vertices = verts;
		triangles = tris;
		triangleColours = cols;
	}
	
	public void setMesh(Vector3[] verts, int[][] tris, Vector3[] cols) {
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
	
	public Vector3[] getTriangle(int t) {
		return new Vector3[] {
			vertices[triangles[t][0]],
			vertices[triangles[t][1]],
			vertices[triangles[t][2]]
		};
	}
	
	public Vector3[] getColours() {
		return triangleColours;
	}
	
	public Vector3 getNormal(int tri) {
		return normal(getTriangle(tri));
	}
	
	public static Vector3 normal(Vector3[] tri) {
		return normal(tri[0], tri[1], tri[2]);
	}
	
	public static Vector3 normal(Vector3 v0, Vector3 v1, Vector3 v2) {
		return v1.subtract(v0).getCross(v2.subtract(v0)).getNormalized();
	}
}
