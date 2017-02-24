package graphics;

import geometry.Vector3;

public class PointLight implements LightSource {

	Vector3 position;
	double diffusePower;
	Vector3 diffuseColour;
	double specularPower;
	Vector3 specularColour;
	
	public PointLight(Vector3 p, double dp, Vector3 dc, double sp, Vector3 sc) {
		position = p;
		diffusePower = dp;
		specularPower = sp;
		diffuseColour = dc;
		specularColour = sc;
	}
	
	@Override
	public LightState getIntensity(Vector3 viewer, Vector3 normal, Vector3 point, double specularHardness) {
		LightState result = new LightState();
		viewer.normalize();
		Vector3 light = position.subtract(point).getNormalized();
		double distance = position.subtract(point).getLengthSq();
		Vector3 half = light.add(viewer).getNormalized();
		
		double intensity = Math.max(0, Math.min(1, normal.getDot(light)));
		result.diffuse = diffuseColour.getWithLength(diffusePower / distance).multiply(intensity);
		
		intensity = Math.pow(Math.max(0, Math.min(1, normal.getDot(half))), specularHardness);
		result.specular = specularColour.multiply(specularPower / distance).multiply(intensity);
		
		return result;
	}
}
