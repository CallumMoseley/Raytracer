package graphics;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Ray;
import geometry.Vector3;
import utility.Intersection;

public class Camera {
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
	
	public void rotateX(double angle) {
		position.dir.rotate(up, angle);
	}
	
	public void rotateY(double angle) {
		position.dir.rotate(position.dir.getCross(up), angle);
		if ((new Vector3(position.dir.x, 0, position.dir.z)).getLength() < 1) {
			
		}
	}
	
	public void renderScene(Graphics g, Scene s, int width, int height, int pixel) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width * pixel, height * pixel);
		
		Vector3 z = position.dir.getNormalized();
		Vector3 x = z.getCross(up).getNormalized();
		Vector3 y = x.getCross(z).getNormalized();
		
		for (int x0 = -width / 2; x0 < width / 2; x0++) {
			for (int y0 = -height / 2; y0 < height / 2; y0++) {
				double xProjected = x0 / (width / 2.0) * (double) width / (double) height * Math.tan(Math.toRadians(fov) / 2);
				double yProjected = y0 / (height / 2.0) * Math.tan(Math.toRadians(fov) / 2);
				Vector3 pixelPos = position.pos.add(position.dir.getNormalized()).add(x.getWithLength(xProjected)).add(y.getWithLength(yProjected));
//				Ray projection = new Ray(pixelPos, pixelPos.subtract(position.pos).getNormalized());
				Ray projection = Ray.fromTo(position.pos, pixelPos);
				projection.dir.normalize();
				
				for (SceneObject o : s.getObjects()) {
					Intersection is = o.intersect(projection);
					if (is.point != Vector3.INFINITY) {
						g.setColor(o.getMesh().getColours()[is.triangle]);
						g.fillRect((x0 + width / 2) * pixel, (height / 2 - y0) * pixel, pixel, pixel);
					}
				}
			}
		}
	}
}
