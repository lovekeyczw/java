package a;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
/**
 *��Ҫ�����¼������ࡣ
������
����temp����������¼��굥������ĺ������꣬ż��Ϊx������Ϊy
pointNumber������¼�ѻ��˶��ٸ��㡣
������
��Ҫ��������ť���䴥���¼����Լ���굥��������϶��¼���
����B�������ߡ�������drawBPic(Graphics g)��B��������������μ����ݵ㡣
���ػ���������pointNumber���㡣
��굥�������㣬�������¼��temp������
����϶����ж��Ƿ�ѡ��ĳ�㣬���õ�����ѡ����Χ�ڡ�10px�ڣ���ѡ���󣬸õ�����꼴����������ı䣬������ˢ���ػ�������ʾΪB������ǣ���ˡ�

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
		JButton btn = new JButton("��B��������");
		btn.setBounds(200, 10, 150, 30);
		btn.addActionListener(this);
		this.add(btn);
		JButton reset = new JButton("�ػ�");
		reset.setBounds(370, 10, 70, 30);
		reset.addActionListener(this);
		this.add(reset);
		this.add(paintPanel);
		this.setBackground(Color.black);	
		pointNumber = 0;
		temp = new int[100];	//��������β��ܳ���100/2,��50����
		
	}
	public static void main(String args[])
	{
		Shiyan mainFrame = new Shiyan();
		mainFrame.setSize(800, 550);
		mainFrame.setTitle("B��������");
		mainFrame.setVisible(true);
	}
	
	/**
	 * ����ڻ����ϵ�������һ����ɫԲ�㣬�����������洢��һ����ʱ�����У�ͬʱ�������pointNumber+1
	 */
    public void mouseClicked(MouseEvent e) { //MouseListener  
    	Graphics g = paintPanel.getGraphics();
    	g.setColor(Color.red);
     	g.fillArc(e.getX()-3, e.getY()-3, 6, 6, 0, 360); 
     	temp[pointNumber*2]   = e.getX();
    	temp[pointNumber*2+1] = e.getY();
    	pointNumber++;    	
    	//bl.drawBLine(g);	//����������       
    }  

    /**
     * �����ߡ���Ҫ����֮ǰ����¼��ĸ���
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
	 * ��ť����Ӧ�¼������ܴ���ʲô�¼�������������
	 * ����B�������ߡ�������ʱ������֮ǰ����¼��õ��ĵ���������д������飬������BLine�е�drawBLine��������B��������
	 * ���ػ�������������pointNumber����
	 */
	public void actionPerformed(ActionEvent e){
		Graphics g = paintPanel.getGraphics();
		String arg=e.getActionCommand();
		g.clearRect(0, 0, 800, 500);
		if(arg.equals("��B��������")){
			drawBPic(g);
		}
		else if(arg.equals("�ػ�")){
			pointNumber = 0;
		}	
	}

	
	/**
	 * ����϶��¼���
	 * �ж����������ĸ��㣬һ���ж��������򽫸õ��������������λ�øı䣬��ˢ���ػ�
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
