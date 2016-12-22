package a;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
/**
 *主要触发事件的主类。
变量：
设置temp数组用来记录鼠标单击画点的横纵坐标，偶数为x，奇数为y
pointNumber用来记录已画了多少个点。
方法：
主要有两个按钮及其触发事件，以及鼠标单击、鼠标拖动事件。
“画B样条曲线”，调用drawBPic(Graphics g)画B样条、特征多边形及数据点。
“重画”，即把pointNumber置零。
鼠标单击，画点，将坐标记录到temp数组中
鼠标拖动，判断是否选中某点，设置的允许选定范围在±10px内，当选定后，该点的坐标即随鼠标的坐标改变，并不断刷新重画，即显示为B样条被牵动了。

 * @author 
 *
 */
public class Shiyan extends JFrame implements MouseMotionListener,MouseListener,ActionListener{
	private JPanel paintPanel;
	private BLine bl;
	private int pointNumber;
	private int[] temp;
	
	public Shiyan(){
		this.setLayout(null);
		paintPanel=new JPanel();
		paintPanel.setBackground(Color.black);
		paintPanel.setBounds(0,50,800,500);
		paintPanel.addMouseListener(this);
		paintPanel.addMouseMotionListener(this);
		JButton btn = new JButton("画B样条曲线");
		btn.setBounds(200, 10, 150, 30);
		btn.addActionListener(this);
		this.add(btn);
		JButton reset = new JButton("重画");
		reset.setBounds(370, 10, 70, 30);
		reset.addActionListener(this);
		this.add(reset);
		this.add(paintPanel);
		this.setBackground(Color.black);	
		pointNumber = 0;
		temp = new int[100];	//特征多边形不能超过100/2,即50个点
		
	}
	public static void main(String args[])
	{
		Shiyan mainFrame = new Shiyan();
		mainFrame.setSize(800, 550);
		mainFrame.setTitle("B样条曲线");
		mainFrame.setVisible(true);
	}
	
	/**
	 * 鼠标在画布上单击，画一个红色圆点，并将点的坐标存储于一个临时数组中，同时点的数量pointNumber+1
	 */
    public void mouseClicked(MouseEvent e) { //MouseListener  
    	Graphics g = paintPanel.getGraphics();
    	g.setColor(Color.red);
     	g.fillArc(e.getX()-3, e.getY()-3, 6, 6, 0, 360); 
     	temp[pointNumber*2]   = e.getX();
    	temp[pointNumber*2+1] = e.getY();
    	pointNumber++;    	
    	//bl.drawBLine(g);	//测试用数据       
    }  

    /**
     * 画曲线。还要画出之前鼠标事件的各点
     * @param g
     */
    public void drawBPic(Graphics g){
		int[] x = new int[pointNumber];
		int[] y = new int[pointNumber];	
		for(int i=0;i<pointNumber;i++){
			x[i] = temp[i*2];
			y[i] = temp[i*2+1];
			g.setColor(Color.red);
			g.fillArc(x[i]-3, y[i]-3, 6, 6, 0, 360);
		}
		bl = new BLine(x,y);
		bl.drawBLine(g);

    }
    
	/**
	 * 按钮的相应事件（不管触发什么事件，都先清屏）
	 * “画B样条曲线”：将临时数组中之前鼠标事件得到的点的坐标序列存入数组，并调用BLine中的drawBLine方法，画B样条曲线
	 * “重画”：将计数器pointNumber清零
	 */
	public void actionPerformed(ActionEvent e){
		Graphics g = paintPanel.getGraphics();
		String arg=e.getActionCommand();
		g.clearRect(0, 0, 800, 500);
		if(arg.equals("画B样条曲线")){
			drawBPic(g);
		}
		else if(arg.equals("重画")){
			pointNumber = 0;
		}	
	}

	
	/**
	 * 鼠标拖动事件。
	 * 判断鼠标点中了哪个点，一旦判定下来，则将该点的坐标随着鼠标的位置改变，并刷新重画
	 */
    public void mouseDragged(MouseEvent e) { //MouseMotionListener   	
    	Graphics g = paintPanel.getGraphics(); 
		int pointx = e.getX();
    	int pointy = e.getY();
    	for(int i=0;i<pointNumber;i++){
    		if(pointx>=(temp[i*2]-10)&&pointx<=(temp[i*2]+10)&&
    				pointy>=(temp[i*2+1]-10)&&pointy<=(temp[i*2+1]+10)){
    			temp[i*2]=pointx;
    			temp[i*2+1]=pointy;    			
    			break;
    		}
    	} 
    	g.clearRect(0, 0, 800, 500);
		drawBPic(g); 
    }  
  
    public void mouseMoved(MouseEvent e) {//MouseMotionListener  
          
    }  
    
  
    public void mouseEntered(MouseEvent e) { //MouseListener  
          
    }  
  
    public void mouseExited(MouseEvent e) { //MouseListener  
          
    }  

    public void mousePressed(MouseEvent e) { //MouseListener  

    }  

    public void mouseReleased(MouseEvent e) { //MouseListener  

    }  
    
	
	
}
