package graphics;

import java.awt.Color;
import java.util.ArrayList;

import geometry.Ray;
import geometry.Vector3;
import utility.Intersection;

public class Scene {

	private ArrayList<SceneObject> objects;
	private ArrayList<LightSource> lights;
	private Color sky;
	
	public Scene() {
		objects = new ArrayList<SceneObject>();
		lights = new ArrayList<LightSource>();
		sky = Color.BLACK;
	}
	
	public ArrayList<SceneObject> getObjects() {
		return objects;
	}
	
	public ArrayList<LightSource> getLights() {
		return lights;
	}

	public Color trace(Ray projection) {
		Vector3 point = Vector3.INFINITY;
		int tri = -1;
		SceneObject object = null;
		for (SceneObject obj : objects) {
			Intersection i = obj.intersect(projection);
			if (projection.pos.subtract(i.point).getLength() < projection.pos.subtract(point).getLength()) {
				point = i.point;
				object = obj;
				tri = i.triangle;
			}
		}
		
		if (point == Vector3.INFINITY) {
			return sky;
		}
		
		
		
		return object.getMesh().getColours()[tri];
	}
}
