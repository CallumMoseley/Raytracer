package geometry;

import java.awt.Color;

public class ColourCube extends Mesh {
	
	public ColourCube(double l, Vector3 pos) {
		l /= 2;
		Vector3[] vertices;
		int[][] triangles;
		Color[] triangleColours;
		vertices = new Vector3[8];
		vertices[0] = new Vector3(-l, -l, -l);
		vertices[1] = new Vector3(l, -l, -l);
		vertices[2] = new Vector3(l, -l, l);
		vertices[3] = new Vector3(-l, -l, l);
		vertices[4] = new Vector3(-l, l, -l);
		vertices[5] = new Vector3(l, l, -l);
		vertices[6] = new Vector3(l, l, l);
		vertices[7] = new Vector3(-l, l, l);
		for (Vector3 v : vertices) {
			v.addToThis(pos);
		}
		
		triangles = new int[12][3];
		triangles[0][0] = 0;
		triangles[0][1] = 1;
		triangles[0][2] = 3;
		triangles[1][0] = 1;
		triangles[1][1] = 2;
		triangles[1][2] = 3;
		
		triangles[2][0] = 0;
		triangles[2][1] = 5;
		triangles[2][2] = 1;
		triangles[3][0] = 5;
		triangles[3][1] = 0;
		triangles[3][2] = 4;
		
		triangles[4][0] = 1;
		triangles[4][1] = 6;
		triangles[4][2] = 2;
		triangles[5][0] = 5;
		triangles[5][1] = 6;
		triangles[5][2] = 1;
		
		triangles[6][0] = 2;
		triangles[6][1] = 7;
		triangles[6][2] = 3;
		triangles[7][0] = 6;
		triangles[7][1] = 7;
		triangles[7][2] = 2;
		
		triangles[8][0] = 3;
		triangles[8][1] = 4;
		triangles[8][2] = 0;
		triangles[9][0] = 4;
		triangles[9][1] = 3;
		triangles[9][2] = 7;
		
		triangles[10][0] = 4;
		triangles[10][1] = 6;
		triangles[10][2] = 5;
		triangles[11][0] = 7;
		triangles[11][1] = 6;
		triangles[11][2] = 4;
		
		triangleColours = new Color[12];
		triangleColours[0] = Color.RED;
		triangleColours[1] = Color.RED;
		triangleColours[2] = Color.GREEN;
		triangleColours[3] = Color.GREEN;
		triangleColours[4] = Color.BLUE;
		triangleColours[5] = Color.BLUE;
		triangleColours[6] = Color.YELLOW;
		triangleColours[7] = Color.YELLOW;
		triangleColours[8] = Color.CYAN;
		triangleColours[9] = Color.CYAN;
		triangleColours[10] = Color.PINK;
		triangleColours[11] = Color.PINK;
		
		setMesh(vertices, triangles, triangleColours);
	}
}
