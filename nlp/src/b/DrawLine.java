package b;

import   javax.swing.*; 
import   java.awt.event.*; 
import   java.awt.*; 
import   java.awt.image.*; 

public class DrawLine extends JFrame {
	int x1, y1;

	public DrawLine() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				int x2, y2;
				x2 = e.getX();
				y2 = e.getY();
				getGraphics().drawLine(x1, y1, x2, y2);
			}
		});
	}

	public static void main(String[] args) {
		DrawLine fm = new DrawLine();
		fm.setSize(300, 300);
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fm.setVisible(true);
	}
}