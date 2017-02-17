package app;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import geometry.Ray;
import graphics.Camera;
import graphics.Scene;

public class RayPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	
	private Scene scene;
	private Camera cam;
	
	public RayPanel() {
		setPreferredSize(new Dimension(1024, 576));
		
		scene = new Scene();
		cam = new Camera(new Ray(0, 0, 0, 0, 0, -1), 90);
		
		(new Thread() {
			@Override
			public void run() {
				while (true) {
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
		cam.renderScene(g, scene, getWidth(), getHeight(), 1);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO this is all so terrible i need to fix it
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			cam.position.pos.addToThis(cam.position.dir.getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			cam.position.pos.subtractFromThis(cam.position.dir.getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			cam.position.pos.addToThis(cam.position.dir.getCross(cam.up).getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A) {
			cam.position.pos.subtractFromThis(cam.position.dir.getCross(cam.up).getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_Q) {
			cam.position.pos.addToThis(cam.up.getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_Z) {
			cam.position.pos.subtractFromThis(cam.up.getWithLength(1));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			cam.position.dir = cam.position.dir.multiply(Math.cos(Math.PI / 45)).add(cam.up.getNormalized().getCross(cam.position.dir).multiply(Math.sin(Math.PI / 45))).add(cam.up.getNormalized().multiply(cam.up.getNormalized().getDot(cam.position.dir) * (1 - Math.cos(Math.PI / 45))));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			cam.position.dir = cam.position.dir.multiply(Math.cos(-Math.PI / 45)).add(cam.up.getNormalized().getCross(cam.position.dir).multiply(Math.sin(-Math.PI / 45))).add(cam.up.getNormalized().multiply(cam.up.getNormalized().getDot(cam.position.dir) * (1 - Math.cos(-Math.PI / 45))));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			cam.position.dir = cam.position.dir.multiply(Math.cos(-Math.PI / 45)).add(cam.up.getCross(cam.position.dir).getNormalized().getCross(cam.position.dir).multiply(Math.sin(-Math.PI / 45))).add(cam.up.getCross(cam.position.dir).getNormalized().multiply(cam.up.getCross(cam.position.dir).getNormalized().getDot(cam.position.dir) * (1 - Math.cos(-Math.PI / 45))));
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			cam.position.dir = cam.position.dir.multiply(Math.cos(Math.PI / 45)).add(cam.up.getCross(cam.position.dir).getNormalized().getCross(cam.position.dir).multiply(Math.sin(Math.PI / 45))).add(cam.up.getCross(cam.position.dir).getNormalized().multiply(cam.up.getCross(cam.position.dir).getNormalized().getDot(cam.position.dir) * (1 - Math.cos(Math.PI / 45))));
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	@Override
	public void mouseDragged(MouseEvent arg0) {}
	@Override
	public void mouseMoved(MouseEvent arg0) {}
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