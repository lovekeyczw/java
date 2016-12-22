package b;

import java.awt.*; 
import java.awt.event.MouseEvent; 
import java.awt.geom.Line2D; 
import java.util.LinkedList; 
import java.util.List; 

import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.event.MouseInputListener; 

public class LineTest extends JPanel implements MouseInputListener 
{ 
	private List <Point> points = new LinkedList <Point> (); 
	private Point draggingPoint; 

	public LineTest() 
	{ 
		this.addMouseListener(this); 
		this.addMouseMotionListener(this); 
	} 

	@Override 
	protected void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g); 

		Point lastPoint = null; 
		g.setColor(Color.BLACK); 
		for (Point p : points) { 
			if (lastPoint != null) { 
				g.drawLine(lastPoint.x, lastPoint.y, p.x, p.y); 
			} 
			lastPoint = p; 
		} 

		for (Point p : points) { 
			g.setColor(getBackground()); 
			g.fillRect(p.x-2, p.y-2, 4, 4); 
			g.setColor(Color.BLACK); 
			g.drawRect(p.x-2, p.y-2, 4, 4); 
		} 
	} 

	private Point getPointAtSameLocation(Point p) 
	{ 
		for (Point point : points) { 
			if (Math.abs(p.x - point.x) <= 2 && Math.abs(p.y - point.y) <= 2) { 
				return point; 
			} 
		} 
		return null; 
	} 

	private int getLineAtLocation(Point p) 
	{ 
		int index = -1; 
		Point lastPoint = null; 
		for (Point point : points) { 
			if (lastPoint != null) { 
				Line2D line = new Line2D.Float(lastPoint, point); 
				if (line.intersects(p.x - 2, p.y - 2, 4, 4)) { 
					return index; 
				} 
			} 
			lastPoint = point; 
			index++; 
		} 
		return -1; 
	} 

	public void mouseClicked(MouseEvent e) {} 
	
	public void mousePressed(MouseEvent e) { 
		Point point = e.getPoint(); 
		if (e.getButton() == MouseEvent.BUTTON1) { 
			Point p = getPointAtSameLocation(point); 
			if (p != null) { 
				this.draggingPoint = p; 
				return; 
			} 

			int index = getLineAtLocation(point); 
			if (index != -1) { 
				points.add(index + 1, point); 
				this.draggingPoint = point; 
				repaint(); 
				return; 
			} 

			if (draggingPoint == null) { 
				points.add(e.getPoint()); 
				repaint(); 
			} 
		} 
		else if (e.getButton() == MouseEvent.BUTTON3) { 
			Point p = getPointAtSameLocation(point); 
			if (p != null) { 
				points.remove(p); 
				repaint(); 
			} 
		} 
	} 
	
	public void mouseReleased(MouseEvent e) { 
		draggingPoint = null; 
	} 
	
	public void mouseEntered(MouseEvent e) {} 
	public void mouseExited(MouseEvent e) {} 
	
	public void mouseDragged(MouseEvent e) 
	{ 
		if (this.draggingPoint != null) { 
			this.draggingPoint.x = e.getX(); 
			this.draggingPoint.y = e.getY(); 
			repaint(); 
		} 
	} 
	
	public void mouseMoved(MouseEvent e) 
	{ 
		Point p = getPointAtSameLocation(e.getPoint()); 
		if (p != null) { 
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
			return; 
		} 

		int index = getLineAtLocation(e.getPoint()); 
		if (index != -1) { 
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
		} 
		else { 
			setCursor(Cursor.getDefaultCursor()); 
		} 
	} 

	public static void main(String[] args) 
	{ 
		JFrame f = new JFrame( "Points "); 
		f.getContentPane().add(new LineTest(), BorderLayout.CENTER); 
		f.setSize(600, 600); 
		f.setLocationRelativeTo(null); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		f.setVisible(true); 

	} 
} 