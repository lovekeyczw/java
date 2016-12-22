package b;

import java.awt.Color; 
import java.awt.Component;
import java.awt.Container; 
import java.awt.Graphics;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseMotionAdapter; 

import javax.swing.JFrame; 
import javax.swing.JLabel; 

public class t 
{ 
    public static void main(String args[]) 
    { 
      JFrame.setDefaultLookAndFeelDecorated(true); 
       new frame(); 
    } 
} 

class frame extends JFrame 

{ 
    JLabel nd; 
    int lx, ly, focusx, focusy; 
    JLabel b1 = new JLabel( "o"); 
    JLabel b2 = new JLabel( "o"); 

    public frame() 
    { 
      super( "asdfsadf "); 
      setSize(500, 500); 
      
     
      b1.setBounds(150, 200, 10, 10); 
      b2.setBounds(250, 200, 10, 10); 

      Container c = getContentPane(); 
      
      c.setLayout(null); 
 
      b1.addMouseListener(new MouseProcess()); 
      b1.addMouseMotionListener(new MouseMotionProcess()); 
      c.add(b1); 
       
      b2.addMouseListener(new MouseProcess()); 
      b2.addMouseMotionListener(new MouseMotionProcess()); 
      c.add(b2); 
      System.out.println(b1.getX());

      this.setVisible(true);
    } 
    
   

    private class MouseProcess extends MouseAdapter 
    { 

      public void mousePressed(MouseEvent e) 
      { 
        nd = (JLabel) e.getSource(); 
        lx = e.getX(); 
        ly = e.getY(); 
//         focusx = nd.getX() + lx; 
//         focusy = nd.getY() + ly; 
        nd = null; 
      } 

      public void mouseReleased(MouseEvent e) 
      { 

      } 
    } 

    private class MouseMotionProcess extends MouseMotionAdapter 
    { 
      public void mouseDragged(MouseEvent e) 
      { 
        nd = (JLabel) e.getSource(); 
        int w = e.getX() - lx; 
        int h = e.getY() - ly; 
        int x = nd.getX() + w; 
        int y = nd.getY() + h; 
        if (x < 0) 
            x = 0; 
        if (y < 0) 
            y = 0; 
        nd.setLocation(x, y); 
        nd.updateUI(); 
      } 

    } 

}