package graphics;

import geometry.Vector3;

public interface LightSource {
	
	public LightState getIntensity(Vector3 viewer, Vector3 normal, Vector3 point, double specularHardness);
}
