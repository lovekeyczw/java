package java_homework;

import java.io.*;
import java.math.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

public class WordNode extends JButton implements ActionListener
{

    private String label;
    private Shape shape;

    public WordNode(String str)
    {
        super(str);
        label=str;
        Dimension size=new Dimension(20,20);
        size.width = size.height = Math.min(size.width, size.height);
        this.setPreferredSize(size);
        this.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        if(this.getModel().isArmed())
        {
            g.setColor(Color.BLUE);
        }
        else
        {
            g.setColor(Color.red);
        }
        g.fillRect(0,0,getSize().width-1,getSize().height-1);
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x,int y)
    {
        if(shape==null||(!shape.getBounds().equals(getBounds())))
        {
            shape=new Rectangle2D.Float(0,0,getWidth(),getHeight());
        }
        return shape.contains(x,y);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.equals(ActionEvent.MOUSE_MOTION_EVENT_MASK))
        {
            System.out.println(e.getActionCommand()+" mouse motion");
        }
        else
        {
            System.out.println(e.getActionCommand()+" something wrong");
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
