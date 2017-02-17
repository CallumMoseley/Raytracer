package graphics;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Ray;
import geometry.Vector3;

public class Camera {
	
	private static final int WIDTH = 16;
	private static final int HEIGHT = 9;
	
	public Ray position;
	double fov;
	public Vector3 up;
	
	public Camera(Ray p, double fov) {
		position = p;
		this.fov = fov;
		up = new Vector3(0, 1, 0);
	}
	
	public Camera(Ray p, double fov, Vector3 up) {
		position = p;
		this.fov = fov;
		this.up = up;
	}
	
	public void renderScene(Graphics g, Scene s, int width, int height, int pixel) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		double distance = WIDTH / (pixel * 2 * Math.tan(Math.toRadians(fov / 2)));
		Vector3 source = position.pos.subtract(position.dir.getWithLength(distance));
		
		if (width % pixel != 0 || height % pixel != 0) {
			System.out.println("Warning: pixel size does not divide screen size");
		}
		
		Vector3 z = position.dir.getNormalized();
		Vector3 x = z.getCross(up).getNormalized();
		Vector3 y = x.getCross(z).getNormalized();
		
		Vector3 corner = position.pos.subtract(x.getWithLength(WIDTH / 2)).subtract(y.getWithLength(HEIGHT / 2));
		
		for (int i = 0; i < Math.ceil((double) width / pixel); i++) {
			for (int j = 0; j < Math.ceil((double) height / pixel); j++) {
				Vector3 pixelPos = corner.add(x.multiply((double) i * pixel / width * WIDTH)).add(y.multiply((double) j * pixel / height * HEIGHT));
				Ray projection = Ray.fromTo(source, pixelPos);
				projection.dir.normalize();
				
				for (SceneObject o : s.getObjects()) {
					Vector3 collision = o.intersect(projection);
					if (collision != Vector3.INFINITY) {
						g.setColor(o.getColor());
						g.fillRect(i * pixel, height - (j + 1) * pixel, pixel, pixel);
					}
				}
			}
		}
	}
}
