package graphics;

import geometry.Ray;
import geometry.Vector3;

public interface LightSource {
	
	public double getIntensity(Ray p, Vector3 normal);
}
