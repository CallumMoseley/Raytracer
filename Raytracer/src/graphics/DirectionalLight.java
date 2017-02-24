package graphics;

import geometry.Vector3;

public class DirectionalLight implements LightSource {

	Vector3 direction;
	
	public DirectionalLight(Vector3 d) {
		direction = d;
	}
	
	@Override
	public LightState getIntensity(Vector3 viewer, Vector3 normal, Vector3 point, double specularHardness) {
		// TODO Auto-generated method stub
		return null;
	}
}
