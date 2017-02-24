package graphics;

import geometry.Vector3;

public class LightState {
	Vector3 diffuse;
	Vector3 specular;
	Vector3 ambient;
	
	public LightState() {
		diffuse = new Vector3();
		specular = new Vector3();
		ambient = new Vector3();
	}
	
	public LightState(Vector3 d, Vector3 s, Vector3 a) {
		diffuse = d;
		specular = s;
		ambient = a;
	}
	
	public Vector3 applyLight(Vector3 colour) {
		return diffuse.add(specular).add(colour.multiply(ambient.getLength()));
	}
	
	public LightState add(LightState other) {
		return new LightState(diffuse.add(other.diffuse), specular.add(other.specular), ambient.add(other.ambient));
	}
}
