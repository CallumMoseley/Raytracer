package graphics;

import java.util.ArrayList;

public class Scene {

	private ArrayList<SceneObject> objects;
	
	public Scene() {
		objects = new ArrayList<SceneObject>();
		objects.add(new SceneObject());
	}
	
	public ArrayList<SceneObject> getObjects() {
		return objects;
	}
}
