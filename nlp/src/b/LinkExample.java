package b;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

public class LinkExample
{
	public static void main(String argv[])
	{
		JFrame f = new JFrame("LinkExaple");
		JLayeredPane container = new MapContainer();
		f.getContentPane().add(container);
		container.setLayout(null);
		IconNode node[] = new IconNode[4];
		for(int i = 0; i < 4; i++)
		{
			node[i] = new IconNode();
			node[i].setSize(new Dimension(50,50));
			container.add(node[i]);
		}
		node[0].appendLink(node[2]);
		node[0].appendLink(node[1]);
		node[0].appendLink(node[3]);
		f.setSize(new Dimension(200,200));
		f.setVisible(true);
	}
}

class MapContainer extends JLayeredPane
{
	public MapContainer()
	{
		super();
	}
	
	public void drawLine(Graphics2D g, Point start, Point end)
	{
		g.drawLine(start.x, start.y, end.x, end.y);
	}
	
	public void paint(Graphics g)
	{
		Vector links = IconNode.links ;
		IconNode node1 = null, node2 = null;
		Point pp = null;
		for(int i = 0; i < links.size(); i++)
		{
			node1 = ((NodeLink)links.elementAt(i)).node1;
			node2 = ((NodeLink)links.elementAt(i)).node2;
			int[] p = Tools.getClosestPart( node1, node2 );
			if( p != null && p[0] != -1 && p[1] != -1)
			{
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
				g2.setColor(Color.darkGray);
				drawLine(g2, node1.getPartPoint(p[0]), node1.getProlongationPoint(p[0]));
				drawLine(g2, node1.getProlongationPoint(p[0]), node2.getProlongationPoint(p[1]));
				drawLine(g2, node2.getProlongationPoint(p[1]), node2.getPartPoint(p[1]));
				g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
				g2.setColor(Color.gray);
				drawLine(g2, node1.getPartPoint(p[0]), node1.getProlongationPoint(p[0]));
				drawLine(g2, node1.getProlongationPoint(p[0]), node2.getProlongationPoint(p[1]));
				drawLine(g2, node2.getProlongationPoint(p[1]), node2.getPartPoint(p[1]));
			}
		}
		super.paint(g);
	}
}

class IconNode extends JComponent
{
	public static Vector links = new Vector();
	private int focusx =0, focusy =0;
	private Vector targets = new Vector();
	public static final int PART_TOP	= 0 ;
	public static final int PART_RIGHT	= 1 ;
	public static final int PART_BOTTOM	= 2 ;
	public static final int PART_LEFT	= 3 ;
	private int prolongation ;
	
	
	public int getProlongation()
	{
		return ( prolongation = getWidth()/4 );
	}
	
	
	public Point getCenter()
	{
		return new Point(getX()+getWidth()/2,getY()+getHeight()/2);
	}
	
	
	public Point getProlongationPoint(int part)
	{
		getProlongation();
		switch(part)
		{
			case PART_TOP:
				return new Point(getX()+getWidth()/2, getY()-prolongation);
			case PART_RIGHT:
				return new Point(getX()+getWidth()+prolongation, getY()+getHeight()/2);
			case PART_BOTTOM:
				return new Point(getX()+getWidth()/2, getY()+getHeight()+prolongation);
			case PART_LEFT:
				return new Point(getX()-prolongation, getY()+getHeight()/2);
			default:
				return null;
		}
	}
	
	
	public Point getPartPoint(int part)
	{
		switch(part)
		{
			case PART_TOP:
				return new Point(getX()+getWidth()/2, getY());
			case PART_RIGHT:
				return new Point(getX()+getWidth(), getY()+getHeight()/2);
			case PART_BOTTOM:
				return new Point(getX()+getWidth()/2, getY()+getHeight());
			case PART_LEFT:
				return new Point(getX(), getY()+getHeight()/2);
			default:
				return null;
		}
	}
	
	
	public IconNode()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
		addMouseListener(new MouseProcess());
		addMouseMotionListener(new MouseMotionProcess());
	}
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
	}
	
	
	private class MouseProcess extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
		}
		public void mousePressed(MouseEvent e)
		{
			focusx = e.getX(); focusy = e.getY();
			if( getParent() instanceof JLayeredPane )
			{
				((JLayeredPane)getParent()).moveToFront(IconNode.this);
			}
		}
	}
	
	
	private class MouseMotionProcess extends MouseMotionAdapter
	{
		public void mouseDragged(MouseEvent e)
		{
			Container container = getParent();
			int w = container.getWidth();
			int h = container.getHeight();
			int x = getX()+e.getX()-focusx;
			int y = getY()+e.getY()-focusy;
			if( x+getWidth() > w ) x = w-getWidth();
			if( y+getHeight() > h ) y = h-getHeight();
			if( x < 0 ) x = 0; if( y < 0 ) y = 0;
			setLocation(x, y);
			container.repaint();
			container = null;
		}
	}
	
	
	public NodeLink findLink(IconNode node)
	{
		return null;
	}
	
	
	public boolean appendLink(IconNode node)
	{
		if( node == null || targets.contains(node) || node == this ) return false;
		targets.addElement(node);
		NodeLink link = null; 
		for(int i = 0; i < links.size(); i++)
		{
			link = (NodeLink)links.elementAt(i);
			if( this.hashCode() < node.hashCode() )
			{
				if( link.node1 == this && link.node2 == node )
					break;
			}
			else
			{
				if( link.node2 == this && link.node1 == node )
					break;
			}
			link = null;
		}
		if( link == null )
			links.addElement(new NodeLink(this,node));
		return node.appendLink(this);
	}
}


class NodeLink
{
	public IconNode node1 = null;
	public IconNode node2 = null;
	public NodeLink(IconNode n1, IconNode n2)
	{
		if( n1.hashCode() < n2.hashCode() )
		{
			node1 = n1;
			node2 = n2;
		}else if( n1.hashCode() > n2.hashCode() )
		{
			node1 = n2;
			node2 = n1;
		}
	}
}


class Tools
{
	public static Rectangle createRectangle(Point p1, Point p2)
	{
		if( p1 == null || p2 == null ) return null;
		int w = Math.abs(p1.x-p2.x);
		int h = Math.abs(p1.y-p2.y);
		return new Rectangle(Math.min(p1.x,p2.x)-1, Math.min(p1.y,p2.y)-1,w+2,h+2);
	}
	
	
	public static Rectangle createRectangle(IconNode node1, IconNode node2)
	{
		if( node1 == null || node2 == null ) return null;
		Point p1 = new Point(node1.getX()+node1.getWidth()/2, node1.getY()+node1.getHeight()/2);
		Point p2 = new Point(node2.getX()+node2.getWidth()/2, node2.getY()+node2.getHeight()/2);
		Rectangle rect = createRectangle(p1, p2);
		p1 = null;
		p2 = null;
		return rect;
	}
	
	
	public static Point[] getContainsPoints(IconNode node, Rectangle rect)
	{
		if( node == null || rect == null ) return null;
		Vector v = new Vector();
		Point p = node.getPartPoint(IconNode.PART_TOP);
		if( rect.contains(p) )	v.addElement(p);
		p = node.getPartPoint(IconNode.PART_RIGHT);
		if( rect.contains(p) )	v.addElement(p);
		p = node.getPartPoint(IconNode.PART_BOTTOM);
		if( rect.contains(p) )	v.addElement(p);
		p = node.getPartPoint(IconNode.PART_LEFT);
		if( rect.contains(p) )	v.addElement(p);
		Point[] ret = new Point[v.size()];
		for(int i=0;i < v.size();i++)	ret[i] = (Point)v.elementAt(i);
		v.clear();	v = null;
		return ret;
	}
	
	
	public static Point[] getClosestPoint(Point[] p1, Point[] p2)
	{
		if( p1 == null || p2 == null ) return null;
		Point ret[] = new Point[2];
		ret[0] = ret[1] = null;
		int mind = Integer.MAX_VALUE , distance;
		for(int i = 0; i < p1.length; i++)
		{
			for(int j = 0; j < p2.length; j++)
			{
				distance = (int)p1[i].distance(p2[j]);
				if( distance < mind )
				{
					ret[0] = p1[i];
					ret[1] = p2[j];
					mind = distance;
				}
			}
		}
		return ret;
	}
	
	
	public static int getPart(IconNode node, Point p)
	{
		if( node == null || p == null ) return -1;
		if( p.x == node.getX()+node.getWidth()/2 && p.y == node.getY() )
			return IconNode.PART_TOP;
		else if( p.x == node.getX()+node.getWidth() && p.y == node.getY()+node.getHeight()/2 )
			return IconNode.PART_RIGHT;
		else if( p.x == node.getX()+node.getWidth()/2 && p.y == node.getY()+node.getHeight() )
			return IconNode.PART_BOTTOM;
		else if( p.x == node.getX() && p.y == node.getY()+node.getHeight()/2 )
			return IconNode.PART_LEFT;
		else return -1;
	}
	
	
	public static int[] getClosestPart(IconNode node1, IconNode node2)
	{
		Point[] p = getClosestPoint(node1, node2);
		if( p == null ) return null;
		int[] ret = new int[2];
		ret[0] = ret[1] = -1;
		ret[0] = getPart(node1, p[0]);
		ret[1] = getPart(node2, p[1]);
		return ret;
	}
	
	
	public static Point[] getClosestPoint(IconNode node1,IconNode node2)
	{
		if( node1 == null || node2 == null ) return null;
		Rectangle rect = createRectangle(node1, node2);
		Point[] p1 = getContainsPoints(node1, rect);
		Point[] p2 = getContainsPoints(node2, rect);
		Point[] p = getClosestPoint(p1, p2);
		p1 = null; p2 = null;
		return p;
	}
}
