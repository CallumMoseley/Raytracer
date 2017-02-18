package app;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import geometry.ColourCube;
import geometry.Ray;
import geometry.Vector3;
import graphics.Camera;
import graphics.Scene;
import graphics.SceneObject;

public class RayPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	private static final long serialVersionUID = -1897181677112770500L; // i hate that stupid warning
	private Scene scene;
	private Camera cam;
	private Robot robot;
	private boolean lockMouse;
	private boolean[] keysPressed;
	private int pixelSize = 8;
	
	public RayPanel() {
		keysPressed = new boolean[KeyEvent.KEY_LAST + 1];
		setPreferredSize(new Dimension(1024, 576));
		
		scene = new Scene();
		scene.getObjects().add(new SceneObject(new ColourCube(2, new Vector3(0, 0, 10))));
		cam = new Camera(new Ray(0, 0, 0, 0, 0, 1), 90);
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		lockMouse = false;
		
		(new Thread() {
			@Override
			public void run() {
				while (true) {
					if (lockMouse)
						robot.mouseMove(getWidth() / 2 + 8, getHeight() / 2 + 31);
					if (keysPressed[KeyEvent.VK_W]) {
						cam.position.pos.addToThis(cam.position.dir.getWithLength(0.1));
					}
					if (keysPressed[KeyEvent.VK_S]) {
						cam.position.pos.subtractFromThis(cam.position.dir.getWithLength(0.1));
					}
					if (keysPressed[KeyEvent.VK_D]) {
						cam.position.pos.addToThis(cam.position.dir.getCross(cam.up).getWithLength(0.1));
					}
					if (keysPressed[KeyEvent.VK_A]) {
						cam.position.pos.subtractFromThis(cam.position.dir.getCross(cam.up).getWithLength(0.1));
					}
					if (keysPressed[KeyEvent.VK_Q]) {
						cam.position.pos.addToThis(cam.up.getWithLength(0.1));
					}
					if (keysPressed[KeyEvent.VK_Z]) {
						cam.position.pos.subtractFromThis(cam.up.getWithLength(0.1));
					}
					repaint();
					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		cam.renderScene(g, scene, getWidth() / pixelSize, getHeight() / pixelSize, pixelSize);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() < keysPressed.length)
			keysPressed[arg0.getKeyCode()] = true;
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_L) {
			lockMouse = !lockMouse;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_EQUALS) {
			pixelSize--;
			pixelSize = Math.max(1, pixelSize);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_MINUS) {
			pixelSize++;
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() < keysPressed.length)
			keysPressed[arg0.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (lockMouse) {
			int dx = getWidth() / 2 - arg0.getX();
			int dy = getHeight() / 2 - arg0.getY();
			cam.rotateX(Math.toRadians(dx) / 10);
			cam.rotateY(Math.toRadians(dy) / 10);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}