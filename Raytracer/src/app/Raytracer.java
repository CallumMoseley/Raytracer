package app;
import javax.swing.JFrame;

public class Raytracer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RayPanel rp = new RayPanel();
		frame.setContentPane(rp);
		frame.pack();
		frame.setVisible(true);
	}
}