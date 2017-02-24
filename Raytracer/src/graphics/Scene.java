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
	private Vector3 ambient;
	
	public Scene() {
		objects = new ArrayList<SceneObject>();
		lights = new ArrayList<LightSource>();
		sky = Color.BLACK;
		ambient = new Vector3(0.1, 0.1, 0.1);
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
		
		Vector3 normal = object.getMesh().getNormal(tri);
		
		LightState lightEffect = new LightState(Vector3.ZERO, Vector3.ZERO, ambient);
		
		for (LightSource ls : lights) {
			if (seeLight(point, ls, normal)) {
				lightEffect = lightEffect.add(ls.getIntensity(projection.dir.getWithLength(-1), normal, point, 50));
			}
		}
		
		return lightEffect.applyLight(object.getMesh().getColours()[tri]).getColour();
	}
	
	public boolean seeLight(Vector3 point, LightSource ls, Vector3 normal) {
		if (ls instanceof PointLight) {
			PointLight pl = (PointLight) ls;
			Ray trace = Ray.fromTo(point.add(normal.getWithLength(0.0001)), pl.position);
			trace.dir.normalize();
			
			for (SceneObject obj : objects) {
				Intersection i = obj.intersect(trace);
				if (i.point != Vector3.INFINITY) {
					return false;
				}
			}
			
			return true;
		} else if (ls instanceof DirectionalLight) {
			
		}
		return false;
	}
}
