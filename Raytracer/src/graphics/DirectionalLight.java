package graphics;

import geometry.Ray;
import geometry.Vector3;

public class DirectionalLight implements LightSource {

	Vector3 direction;
	
	public DirectionalLight(Vector3 d) {
		direction = d;
	}

	@Override
	public double getIntensity(Ray p, Vector3 normal) {
		return 0;
	}
}
