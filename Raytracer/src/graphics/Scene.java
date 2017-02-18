package graphics;

import java.util.ArrayList;

public class Scene {

	private ArrayList<SceneObject> objects;
	private ArrayList<LightSource> lights;
	
	public Scene() {
		objects = new ArrayList<SceneObject>();
	}
	
	public ArrayList<SceneObject> getObjects() {
		return objects;
	}
}
