package b;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;




public class BezierFrame extends JFrame {
  /**
	 * 
	 */
	
  private int B3count; //B3曲线点个数
  private int B3ptNum = 0; //B3控制点个数
  private Point[] B3outpt = new Point[1000]; //输出的点
  private Point[] B3pt = new Point[100]; //输入的点
  private int B3admittedTow = -1; //允许B3样条拖动标志位
  private int B3update = 0;
  private int B3mode = 0; //B3次样条状态

  private int B2count; //B2曲线点个数
  private int B2ptNum = 0; //B2控制点个数
  private Point[] B2outpt = new Point[1000]; //输出的点
  private Point[] B2pt = new Point[100]; //输入的点
  private int B2admittedTow = -1; //允许B2样条拖动标志位
  private int B2update = 0;
  private int B2mode = 0; //B2次样条状态

  private int Bezier3update = 0; //改变Bezier三次曲线控制点标志位
  private int Bezier3admittedTow = -1; //允许Bezier三次曲线拖动标志位
  private int Bezier3mode = 0; //Bezier三次曲线的状态
  private int Bezier3PtNum = -1; //Beizer三次曲线控制点个数
  private Point[] Bezier3pt = new Point[100];
  point[] ptTemp = new point[4];

  private int Bezier2update = 0; //改变Bezier二次曲线控制点标志位
  private int Bezier2admittedTow = -1; //允许Bezier二次曲线拖动标志位
  private int Bezier2mode = 0; //Bezier二次曲线的状态
  private int Bezier2PtNum = -1; //Beizer二次曲线控制点个数

  
  private Point[] Bezier2pt = new Point[100];

  private int clear = 0;
  private int curveMode = -1; //曲线模型标志位
  private int jPanel_graphics_x;
  private int jPanel_graphics_y;

  JPanel contentPane;
  JPanel jPanel_graphics = new JPanel();
  TitledBorder titledBorder1;
  JPanel jPanel_button = new JPanel();
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  JButton jButton_generateCurve = new JButton();
  JButton jButton_editControlPoint = new JButton();
  JButton jButton_clear = new JButton();
  JPanel jPanel_choices = new JPanel();
  Border border1;
  TitledBorder titledBorder4;
  TitledBorder titledBorder5;
  JRadioButton jRadioButton_Bezier3 = new JRadioButton();
  JRadioButton jRadioButton_b2 = new JRadioButton();
  JRadioButton jRadioButton_b3 = new JRadioButton();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JRadioButton jRadioButton_Bezier2 = new JRadioButton();
  JLabel statetip = new JLabel();

  //Construct the frame
  public BezierFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    contentPane = (JPanel)this.getContentPane();
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
        white, new Color(153, 159, 165)), "绘图区");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    border1 = BorderFactory.createEmptyBorder();
    titledBorder4 = new TitledBorder(border1, "选择曲线类型");
    titledBorder5 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
        white, new Color(153, 159, 165)), "选择曲线类型");
    contentPane.setLayout(null);
    this.setSize(new Dimension(427, 534));
    this.setTitle("曲线绘制软件");
    jPanel_graphics.setBackground(Color.white);
    jPanel_graphics.setBorder(titledBorder1);
    jPanel_graphics.setBounds(new Rectangle(9, 10, 406, 297));
    jPanel_graphics.addMouseMotionListener(new
                                           BezierFrame_jPanel_graphics_mouseMotionAdapter(this));
    jPanel_graphics.addMouseListener(new
                                     BezierFrame_jPanel_graphics_mouseAdapter(this));
    jPanel_graphics.setLayout(null);
    //  jPanel_graphics.setCursor(Cursor.getPredefinedCursor(Cursor.
    //     CROSSHAIR_CURSOR));
    jPanel_button.setBorder(titledBorder3);
    jPanel_button.setBounds(new Rectangle(262, 329, 151, 116));
    jPanel_button.setLayout(null);
    jButton_generateCurve.setBounds(new Rectangle(27, 7, 92, 25));
    jButton_generateCurve.setText("生成曲线");
    jButton_generateCurve.addActionListener(new
                                            BezierFrame_jButton_generateCurve_actionAdapter(this));
    jButton_editControlPoint.setBounds(new Rectangle(28, 45, 93, 25));
    jButton_editControlPoint.setText("编辑控制点");
    jButton_editControlPoint.addActionListener(new
                                               BezierFrame_jButton_editControlPoint_actionAdapter(this));
    jButton_clear.setBounds(new Rectangle(27, 78, 94, 25));
    jButton_clear.setText("清屏");
    jButton_clear.addActionListener(new BezierFrame_jButton_clear_actionAdapter(this));
    jPanel_choices.setBorder(titledBorder5);
    jPanel_choices.setBounds(new Rectangle(12, 321, 240, 126));
    jPanel_choices.setLayout(null);
    jRadioButton_Bezier3.setText("Bezier三次曲线");
    jRadioButton_Bezier3.setBounds(new Rectangle(14, 45, 125, 25));
    jRadioButton_Bezier3.addActionListener(new
                                           BezierFrame_jRadioButton_Bezier3_actionAdapter(this));
    jRadioButton_b2.setText("B样条二次曲线");
    jRadioButton_b2.setBounds(new Rectangle(14, 72, 118, 25));
    jRadioButton_b2.addActionListener(new
                                      BezierFrame_jRadioButton_b2_actionAdapter(this));
    jRadioButton_b3.setText("B样条三次曲线");
    jRadioButton_b3.setBounds(new Rectangle(14, 95, 136, 25));
    jRadioButton_b3.addActionListener(new
                                      BezierFrame_jRadioButton_b3_actionAdapter(this));
    jRadioButton_Bezier2.setText("Bezier二次曲线");
    jRadioButton_Bezier2.setBounds(new Rectangle(14, 19, 112, 25));
    jRadioButton_Bezier2.addActionListener(new
                                           BezierFrame_jRadioButton_Bezier2_actionAdapter(this));
    statetip.setBounds(new Rectangle(20, 468, 316, 24));
    contentPane.add(jPanel_graphics, null);
    contentPane.add(jPanel_button, null);
    jPanel_button.add(jButton_generateCurve, null);
    jPanel_button.add(jButton_clear, null);
    jPanel_button.add(jButton_editControlPoint, null);
    contentPane.add(jPanel_choices, null);
    jPanel_choices.add(jRadioButton_b3, null);
    jPanel_choices.add(jRadioButton_b2, null);
    jPanel_choices.add(jRadioButton_Bezier3, null);
    jPanel_choices.add(jRadioButton_Bezier2, null);
    contentPane.add(statetip, null);
    jPanel_graphics_x = jPanel_graphics.getLocation().x;
    jPanel_graphics_y = jPanel_graphics.getLocation().y;
    buttonGroup1.add(jRadioButton_Bezier3);
    buttonGroup1.add(jRadioButton_b2);
    buttonGroup1.add(jRadioButton_b3);
    buttonGroup1.add(jRadioButton_Bezier2);
    initialBPointArray();
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      String info =
          "byebye";
      JOptionPane.showMessageDialog(null, info, "友情提示",
                                    JOptionPane.INFORMATION_MESSAGE);
      System.exit(0);
    }
  }

  //初始化B样条控制点和输出绘制点数组
  private void initialBPointArray() {
    for (int i = 0; i < 100; i++) {
      B2pt[i] = new Point();
      B3pt[i] = new Point();
      Bezier3pt[i] = new Point();
      Bezier2pt[i] = new Point();
    }
    for (int i = 0; i < 1000; i++) {
      B2outpt[i] = new Point();
      B3outpt[i] = new Point();
    }
    for (int i = 0; i < 4; i++) {
      ptTemp[i] = new point();
    }

  }

  

  public void generateBezier(Graphics g, point[] inPt, float t, int n,
                             int floor) {
    int floortemp = floor;
    point[][] outPt = new point[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        outPt[i][j] = new point();
      }
    for (int i = 0; i < n; i++) {
      outPt[i][0].x = inPt[i].x;
      outPt[i][0].y = inPt[i].y;
    }
    for (int r = 1; r < n; r++)
      for (int i = 0; i < n - r; i++) {
        outPt[i][r].x = (1 - t) * outPt[i][r - 1].x + t * outPt[i + 1][r - 1].x;
        outPt[i][r].y = (1 - t) * outPt[i][r - 1].y + t * outPt[i + 1][r - 1].y;
      }
    floortemp--;
    if (floortemp == 0) {
      g.drawLine( (int) outPt[0][0].x, (int) outPt[0][0].y,
                 (int) outPt[0][n - 1].x, (int) outPt[0][n - 1].y);
      g.drawLine( (int) outPt[0][n - 1].x, (int) outPt[0][n - 1].y,
                 (int) outPt[n - 1][0].x, (int) outPt[n - 1][0].y);
      return; //回退
    }
    point[] leftPt = new point[n];
    point[] rightPt = new point[n];
    for (int i = 0; i < n; i++) {
      leftPt[i] = new point();
      rightPt[i] = new point();
    }
    //px[0][3], px[1][2], px[2][1], px[3][0]
    for (int i = 0; i < n; i++) {
      leftPt[i].x = outPt[0][i].x;
      leftPt[i].y = outPt[0][i].y;
      rightPt[i].x = outPt[i][n - i - 1].x;
      rightPt[i].y = outPt[i][n - i - 1].y;
    }

    generateBezier(g, leftPt, t, n, floortemp);
    generateBezier(g, rightPt, t, n, floortemp);
  }

  //由已知的四个控制点，计算B2outpt(t)
  void multipleMatric2(double t, Point[] p) {
    float[][] Matric = {
        {
        1, -2, 1}
        , {
        -2, 2, 0}
        , {
        1, 1, 0}
    };
    double[] tArrayR= new double[3];
    double[] tArray = {
        t * t, t, 1};
    int i, j;
    for (i = 0; i < 3; i++) {
      tArrayR[i] = 0;
      for (j = 0; j < 3; j++)
        tArrayR[i] += tArray[j] * Matric[j][i];
    }
    Point result = new Point();
    result.x = result.y = 0;
    for (i = 0; i < 3; i++) {
      result.x += (int) (tArrayR[i] * p[i].x);
      result.y += (int) (tArrayR[i] * p[i].y);
    }
    B2outpt[B2count].x = result.x / 2;
    B2outpt[B2count].y = result.y / 2;
    B2count++;
  }

  //由已知的四个控制点，计算B3outpt(t)
  void multipleMatric3(double t, Point[] p) {
    double[][] Matric = {
        {
        -1, 3, -3, 1}
        , {
        3, -6, 3, 0}
        , {
        -3, 0, 3, 0}
        , {
        1, 4, 1, 0}
    };
    double[] tArrayR = new double[4];
    double[] tArray = {
        t * t * t, t * t, t, 1};
    int i, j;
    for (i = 0; i < 4; i++) {
      tArrayR[i] = 0;
      for (j = 0; j < 4; j++) {
        tArrayR[i] += tArray[j] * Matric[j][i];
      }
    }
    B3outpt[B3count].x = B3outpt[B3count].y = 0;
    for (i = 0; i < 4; i++) {
      B3outpt[B3count].x += (int) (tArrayR[i] * p[i].x);
      B3outpt[B3count].y += (int) (tArrayR[i] * p[i].y);
    }
    B3outpt[B3count].x = B3outpt[B3count].x / 6;
    B3outpt[B3count].y = B3outpt[B3count].y / 6;
    B3count++;
  }

  // 生成2次B样条曲线算法
  void drawBSpline2(Graphics g) {

    Point[] B2pp = new Point[100];
    Point[] B2p = new Point[3];
    for (int i = 0; i < 100; i++) {
      B2pp[i] = new Point();
    }
    for (int i = 0; i < 3; i++) {
      B2p[i] = new Point();
    }
   
    B2pp[0].x = B2pt[0].x * 2 - B2pt[1].x;
    B2pp[0].y = B2pt[0].y * 2 - B2pt[1].y;
    B2pp[B2ptNum - 1].x = B2pt[B2ptNum - 1].x * 2 - B2pt[B2ptNum - 2].x;
    B2pp[B2ptNum - 1].y = B2pt[B2ptNum - 1].y * 2 - B2pt[B2ptNum - 2].y;
    int start = 0;
    for (int i = 1; i < B2ptNum - 1; i++) {
      B2pp[i].x = B2pt[i].x;
      B2pp[i].y = B2pt[i].y;
    }
    B2count = 0;
    double t = 0;
    while (start < B2ptNum - 2) {
      t = 0;
      //每次选4个点作为控制点，生成1段曲线；接着右移1个点，继续循环
      //（比如有5个点A,B,C,D,E，刚开始选A,B,C,D,接着选B,C,D,E作为控制点）
      for (int i = 0; i < 3; i++) {
        B2p[i].x = B2pp[start + i].x;
        B2p[i].y = B2pp[start + i].y;
      }
      start++;
      while (t < 1) {
        multipleMatric2(t, B2p); //计算出 B2outpt(t)
        t = t + 0.05;
      }
    }
    B2outpt[B2count].x = B2pt[B2ptNum - 1].x;
    B2outpt[B2count].y = B2pt[B2ptNum - 1].y;
    B2count++;
    //注意处理好边界i < B2count - 1
    for (int i = 0; i < B2count - 1; i++) {
      g.drawLine(B2outpt[i].x, B2outpt[i].y, B2outpt[i + 1].x, B2outpt[i + 1].y);
    }
  }

  // 生成3次B样条曲线算法
  void drawBSpline3(Graphics g) {
    Point[] B3pp = new Point[100];
    Point[] B3p = new Point[4];
    for (int i = 0; i < 100; i++) {
      B3pp[i] = new Point();
    }
    for (int i = 0; i < 4; i++) {
      B3p[i] = new Point();
    }
    
    B3pp[0].x = B3pt[0].x * 2 - B3pt[1].x;
    B3pp[0].y = B3pt[0].y * 2 - B3pt[1].y;
    B3pp[B3ptNum + 1].x = B3pt[B3ptNum - 1].x * 2 - B3pt[B3ptNum - 2].x;
    B3pp[B3ptNum + 1].y = B3pt[B3ptNum - 1].y * 2 - B3pt[B3ptNum - 2].y;//传递控制点的值
    int i, start = 0;
    for (i = 0; i < B3ptNum; i++) {
      B3pp[i + 1].x = B3pt[i].x;
      B3pp[i + 1].y = B3pt[i].y;
    }
    B3count = 0;
    double t = 0;
    while (start < B3ptNum - 1) {
      t = 0;
      //每次选4个点作为控制点，生成1段曲线；接着右移1个点，继续循环
      //（比如有5个点A,B,C,D,E，刚开始选A,B,C,D,接着选B,C,D,E作为控制点）
      for (i = 0; i < 4; i++) {
        B3p[i].x = B3pp[start + i].x;
        B3p[i].y = B3pp[start + i].y;
      }
      start++;
      while (t < 1) {
        multipleMatric3(t, B3p); //计算出 B3outpt(t)
        t = t + 0.05;
      }
    }
    B3outpt[B3count].x = B3pt[B3ptNum - 1].x;
    B3outpt[B3count].y = B3pt[B3ptNum - 1].y;
    B3count++;
    for (i = 0; i < B3count - 1; i++) {
      g.drawLine(B3outpt[i].x, B3outpt[i].y, B3outpt[i + 1].x, B3outpt[i + 1].y);
    }
  }

  private void opOnBezier2(Graphics g) {
    if (Bezier2mode == 1 && Bezier2update != 1) {
      g.drawRect(Bezier2pt[Bezier2PtNum].x, Bezier2pt[Bezier2PtNum].y, 5, 5);
      if (Bezier2PtNum >= 1) {
        g.setColor(Color.green);
        g.drawLine(Bezier2pt[Bezier2PtNum - 1].x, Bezier2pt[Bezier2PtNum - 1].y,
                   Bezier2pt[Bezier2PtNum].x, Bezier2pt[Bezier2PtNum].y);
        g.setColor(Color.black);
      }
    }
    //响应“生成曲线”按钮，绘制曲线
    else if (Bezier2mode == 2 && Bezier2update != 1) {
      int i = 0;
      while (i <= (Bezier2PtNum - 2)) {
        for (int k = 0; k < 3; k++) {
          ptTemp[k].x = (float) Bezier2pt[i + k].x;
          ptTemp[k].y = (float) Bezier2pt[i + k].y;
        }
        generateBezier(g, ptTemp, (float) 0.5, 3, 7);
        i = i + 2;
      }
    }
    //响应鼠标拖拉控制点事件，在控制点位置不断改变的情况下，绘制相应的曲线
    else if (Bezier2update == 1) {
      super.update(g);
      for (int i = 0; i <= Bezier2PtNum; i++) {
        g.drawRect(Bezier2pt[i].x, Bezier2pt[i].y, 5, 5);
        if (i >= 0 && i < Bezier2PtNum) {
          g.setColor(Color.green);
          g.drawLine(Bezier2pt[i].x, Bezier2pt[i].y,
                     Bezier2pt[i + 1].x, Bezier2pt[i + 1].y);
          g.setColor(Color.black);
        }
      }

      int i = 0;
      while (i <= (Bezier2PtNum - 2)) {
        for (int k = 0; k < 3; k++) {
          ptTemp[k].x = (float) Bezier2pt[i + k].x;
          ptTemp[k].y = (float) Bezier2pt[i + k].y;
        }
        generateBezier(g, ptTemp, (float) 0.5, 3, 7);
        i = i + 2;
      }

      Bezier2update = 0;
    }

  }

  //与可视化的结合部分的代码,对 Bezier三次曲线的操作
  private void opOnBezier3(Graphics g) {
    //响应鼠标点击事件绘制控制点
    if (Bezier3mode == 1 && Bezier3update != 1) {
      g.drawRect(Bezier3pt[Bezier3PtNum].x, Bezier3pt[Bezier3PtNum].y, 5, 5);
      if (Bezier3PtNum >= 1) {
        g.setColor(Color.green);
        g.drawLine(Bezier3pt[Bezier3PtNum - 1].x, Bezier3pt[Bezier3PtNum - 1].y,
                   Bezier3pt[Bezier3PtNum].x, Bezier3pt[Bezier3PtNum].y);
        g.setColor(Color.black);
      }
    }
    //响应“生成曲线”按钮，绘制曲线
    else if (Bezier3mode == 2 && Bezier3update != 1) {
      int i = 0;
      while (i <= (Bezier3PtNum - 3)) {
        for (int k = 0; k < 4; k++) {
          ptTemp[k].x = (float) Bezier3pt[i + k].x;
          ptTemp[k].y = (float) Bezier3pt[i + k].y;
        }
        generateBezier(g, ptTemp, (float) 0.5, 4, 7);
        i = i + 3;
      }
    }
    //响应鼠标拖拉控制点事件，在控制点位置不断改变的情况下，绘制相应的曲线
    else if (Bezier3update == 1) {
      super.update(g);
      for (int i = 0; i <= Bezier3PtNum; i++) {
        g.drawRect(Bezier3pt[i].x, Bezier3pt[i].y, 5, 5);
        if (i >= 0 && i < Bezier3PtNum) {
          g.setColor(Color.green);
          g.drawLine(Bezier3pt[i].x, Bezier3pt[i].y,
                     Bezier3pt[i + 1].x, Bezier3pt[i + 1].y);
          g.setColor(Color.black);
        }
      }
      int i = 0;

      while (i <= (Bezier3PtNum - 3)) {
        for (int k = 0; k < 4; k++) {
          ptTemp[k].x = (float) Bezier3pt[i + k].x;
          ptTemp[k].y = (float) Bezier3pt[i + k].y;
        }
        generateBezier(g, ptTemp, (float) 0.5, 4, 7);
        i = i + 3;
      }

      Bezier3update = 0;
    }
  }

  //与可视化的结合部分的代码
  private void opOnB2(Graphics g) {
    //响应鼠标点击事件绘制控制点
    if (B2mode == 1 && B2update != 1) {
      g.drawRect(B2pt[B2ptNum - 1].x, B2pt[B2ptNum - 1].y, 5, 5);
      if (B2ptNum >= 2) {
        g.setColor(Color.green);
        g.drawLine(B2pt[B2ptNum - 2].x, B2pt[B2ptNum - 2].y,
                   B2pt[B2ptNum - 1].x, B2pt[B2ptNum - 1].y);
        g.setColor(Color.black);
      }
    }
    //响应“生成曲线”按钮，绘制曲线
    else if (B2mode == 2 && B2update != 1) {
      super.update(g); //刷新屏幕
      for (int i = 0; i < B2ptNum; i++) {
        g.drawRect(B2pt[i].x, B2pt[i].y, 5, 5);
        if (i >= 0 && i < B2ptNum - 1) {
          g.setColor(Color.green);
          g.drawLine(B2pt[i].x, B2pt[i].y,
                     B2pt[i + 1].x, B2pt[i + 1].y);
          g.setColor(Color.black);
        }
      }
      drawBSpline2(g);
    }
    //响应鼠标拖拉控制点事件，在控制点位置不断改变的情况下，绘制相应的曲线
    else if (B2update == 1) {
      super.update(g);
      for (int i = 0; i < B2ptNum; i++) {
        g.drawRect(B2pt[i].x, B2pt[i].y, 5, 5);
        if (i >= 0 && i < B2ptNum - 1) {
          g.setColor(Color.green);
          g.drawLine(B2pt[i].x, B2pt[i].y,
                     B2pt[i + 1].x, B2pt[i + 1].y);
          g.setColor(Color.black);
        }
      }
      drawBSpline2(g);
      B2update = 0;
    }
  }

  //与可视化的结合部分的代码
  private void opOnB3(Graphics g) {
    //响应鼠标点击事件绘制控制点
    if (B3mode == 1 && B3update != 1) {
      g.drawRect(B3pt[B3ptNum - 1].x, B3pt[B3ptNum - 1].y, 5, 5);
      if (B3ptNum >= 2) {
        g.setColor(Color.green);
        g.drawLine(B3pt[B3ptNum - 2].x, B3pt[B3ptNum - 2].y,
                   B3pt[B3ptNum - 1].x, B3pt[B3ptNum - 1].y);
        g.setColor(Color.black);
      }
    }
    //响应鼠标拖拉控制点事件，在控制点位置不断改变的情况下，绘制相应的曲线
    else if (B3mode == 2 && B3update != 1) {
      super.update(g);
      for (int i = 0; i < B3ptNum; i++) {
        g.drawRect(B3pt[i].x, B3pt[i].y, 5, 5);
        if (i >= 0 && i < B3ptNum - 1) {
          g.setColor(Color.green);
          g.drawLine(B3pt[i].x, B3pt[i].y,
                     B3pt[i + 1].x, B3pt[i + 1].y);
          g.setColor(Color.black);
        }
      }
      drawBSpline3(g);
    }
    //响应鼠标拖拉控制点事件，在控制点位置不断改变的情况下，绘制相应的曲线
    else if (B3update == 1) {
      super.update(g);
      for (int i = 0; i < B3ptNum; i++) {
        g.drawRect(B3pt[i].x, B3pt[i].y, 5, 5);
        if (i >= 0 && i < B3ptNum - 1) {
          g.setColor(Color.green);
          g.drawLine(B3pt[i].x, B3pt[i].y,
                     B3pt[i + 1].x, B3pt[i + 1].y);
          g.setColor(Color.black);
        }
      }
      drawBSpline3(g);
      B3update = 0;
    }
  }

  public void update(Graphics g) {
    if (clear == 1) {
      super.update(g);
      B2ptNum = 0;
      B3ptNum = 0;

      Bezier3PtNum = -1;
      Bezier2PtNum = -1;
      clear = 0;
    }
    else if (curveMode == 0) {
      opOnBezier2(g);
    }
    else if (curveMode == 1) {
      opOnBezier3(g);
    }
    else if (curveMode == 2) {
      opOnB2(g);
    }
    else if (curveMode == 3) {
      opOnB3(g);
    }
  }

  void jPanel_graphics_mouseClicked(MouseEvent e) {
    if (curveMode == 0) {
      if (Bezier2mode == 1) {
        int k = e.getClickCount();
        if (k == 1) {
          Bezier2PtNum++;
          Bezier2pt[Bezier2PtNum].x = e.getX() + jPanel_graphics_x + 4;
          Bezier2pt[Bezier2PtNum].y = e.getY() + jPanel_graphics_y + 31;
          repaint();
        }
      }
    }
    else if (curveMode == 1) {
      if (Bezier3mode == 1) {
        int k = e.getClickCount();
        if (k == 1) {
          Bezier3PtNum++;
          Bezier3pt[Bezier3PtNum].x = e.getX() + jPanel_graphics_x + 4;
          Bezier3pt[Bezier3PtNum].y = e.getY() + jPanel_graphics_y + 31;
          repaint();
        }
      }
    }
    else if (curveMode == 2) {
      if (B2mode == 1) {
        int k = e.getClickCount();
        if (k == 1 && B2ptNum < 100) {
          B2pt[B2ptNum].x = e.getX() + jPanel_graphics_x + 4;
          B2pt[B2ptNum].y = e.getY() + jPanel_graphics_y + 31;
          B2ptNum++;
          repaint();
        }
      }
    }
    else if (curveMode == 3) {
      if (B3mode == 1) {
        int k = e.getClickCount();
        if (k == 1 && B3ptNum < 100) {
          B3pt[B3ptNum].x = e.getX() + jPanel_graphics_x + 4;
          B3pt[B3ptNum].y = e.getY() + jPanel_graphics_y + 31;
          B3ptNum++;
          repaint();
        }
      }
    }
    int x = e.getX() - 3;
    int y = e.getY() - 9;
    statetip.setText("  当前鼠标位置-- ( " + x + "," + y
                     + " )");

  }

  void jButton_generateCurve_actionPerformed(ActionEvent e) {
    if (curveMode == 0) {
      Bezier2mode = 2;
      repaint();
    }
    else if (curveMode == 1) {
      Bezier3mode = 2;
      repaint();
    }
    else if (curveMode == 2) {
      B2mode = 2;
      repaint();
    }
    else if (curveMode == 3) {
      B3mode = 2;
      repaint();
    }
  }

  void jButton_editControlPoint_actionPerformed(ActionEvent e) {
    if (curveMode == 0) {
      Bezier2mode = 1; //确定控制点
    }
    else if (curveMode == 1) {
      Bezier3mode = 1; //确定控制点
    }
    else if (curveMode == 2) {
      B2mode = 1;
    }
    else if (curveMode == 3) {
      B3mode = 1;
    }
  }

  void jPanel_graphics_mousePressed(MouseEvent e) {
    if (curveMode == 0) {
      if (Bezier2mode == 1) {
        int x = e.getX() + jPanel_graphics_x + 4;
        int y = e.getY() + jPanel_graphics_y + 31;
        for (int i = 0; i <= Bezier2PtNum; i++) {
          if (Bezier2pt[i].x <= x && x <= Bezier2pt[i].x + 10 &&
              Bezier2pt[i].y <= y &&
              y <= Bezier2pt[i].y + 10) {
            Bezier2admittedTow = i;
          }
        }
      }
    }
    else if (curveMode == 1) {
      if (Bezier3mode == 1) {
        int x = e.getX() + jPanel_graphics_x + 4;
        int y = e.getY() + jPanel_graphics_y + 31;
        for (int i = 0; i <= Bezier3PtNum; i++) {
          if (Bezier3pt[i].x <= x && x <= Bezier3pt[i].x + 10 &&
              Bezier3pt[i].y <= y &&
              y <= Bezier3pt[i].y + 10) {
            Bezier3admittedTow = i;
          }
        }
      }
    }
    else if (curveMode == 2) {
      if (B2mode == 1) {
        int x = e.getX() + jPanel_graphics_x + 4;
        int y = e.getY() + jPanel_graphics_y + 31;
        for (int i = 0; i < B2ptNum; i++) {
          if (B2pt[i].x <= x && x <= B2pt[i].x + 9 && B2pt[i].y <= y &&
              y <= B2pt[i].y + 9) {
            B2admittedTow = i;
          }
        }
      }
    }
    else if (curveMode == 3) {
      if (B3mode == 1) {
        int x = e.getX() + jPanel_graphics_x + 4;
        int y = e.getY() + jPanel_graphics_y + 31;
        for (int i = 0; i < B3ptNum; i++) {
          if (B3pt[i].x <= x && x <= B3pt[i].x + 10 && B3pt[i].y <= y &&
              y <= B3pt[i].y + 10) {
            B3admittedTow = i;
          }
        }
      }
    }

  }

  void jPanel_graphics_mouseDragged(MouseEvent e) {
    if (curveMode == 0) {
      if (Bezier2mode == 1 && Bezier2admittedTow != -1) {
        Bezier2pt[Bezier2admittedTow].x = e.getX() + jPanel_graphics_x + 4;
        Bezier2pt[Bezier2admittedTow].y = e.getY() + jPanel_graphics_y + 31;
        Bezier2update = 1;
        repaint();
      }
    }
    else if (curveMode == 1) {
      if (Bezier3mode == 1 && Bezier3admittedTow != -1) {
        Bezier3pt[Bezier3admittedTow].x = e.getX() + jPanel_graphics_x + 4;
        Bezier3pt[Bezier3admittedTow].y = e.getY() + jPanel_graphics_y + 31;
        Bezier3update = 1;
        repaint();
      }
    }
    else if (curveMode == 2) {
      if (B2mode == 1 && B2admittedTow != -1) {
        B2pt[B2admittedTow].x = e.getX() + jPanel_graphics_x + 4;
        B2pt[B2admittedTow].y = e.getY() + jPanel_graphics_y + 31;
        B2update = 1;
        repaint();
      }
    }
    else if (curveMode == 3) {
      if (B3mode == 1 && B3admittedTow != -1) {
        B3pt[B3admittedTow].x = e.getX() + jPanel_graphics_x + 4;
        B3pt[B3admittedTow].y = e.getY() + jPanel_graphics_y + 31;
        B3update = 1;
        repaint();
      }
    }
    int x = e.getX() - 3;
    int y = e.getY() - 9;
    statetip.setText("  当前鼠标位置-- ( " + x + "," + y
                     + " )");

  }

  void jButton_clear_actionPerformed(ActionEvent e) {
    if (curveMode == 0) {
      clear = 1;
      repaint();
    }
    else if (curveMode == 1) {
      clear = 1;
      repaint();
    }
    else if (curveMode == 2) {
      clear = 1;
      repaint();
    }
    else if (curveMode == 3) {
      clear = 1;
      repaint();
    }

  }

  void jPanel_graphics_mouseReleased(MouseEvent e) {
    if (curveMode == 0) {
      Bezier2update = 0;
      Bezier2admittedTow = -1;
    }
    else if (curveMode == 1) {
      Bezier3update = 0;
      Bezier3admittedTow = -1;
    }
    else if (curveMode == 2) {
      B2update = 0;
      B2admittedTow = -1;
    }
    else if (curveMode == 3) {
      B3update = 0;
      B3admittedTow = -1;
    }
  }

  void jRadioButton_Bezier3_actionPerformed(ActionEvent e) {
    curveMode = 1;
    clear = 1;
    repaint();
  }

  void jRadioButton_b2_actionPerformed(ActionEvent e) {
    curveMode = 2;
    clear = 1;
    repaint();
  }

  void jRadioButton_b3_actionPerformed(ActionEvent e) {
    curveMode = 3;
    clear = 1;
    repaint();
  }

  void jRadioButton_Bezier2_actionPerformed(ActionEvent e) {
    curveMode = 0;
    clear = 1;
    repaint();
  }

  void jPanel_graphics_mouseMoved(MouseEvent e) {
    int x = e.getX() - 3;
    int y = e.getY() - 9;
    statetip.setText("  当前鼠标位置-- ( " + x + "," + y
                     + " )");
  }

}

class BezierFrame_jPanel_graphics_mouseAdapter
    extends java.awt.event.MouseAdapter {
  BezierFrame adaptee;

  BezierFrame_jPanel_graphics_mouseAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jPanel_graphics_mouseClicked(e);
  }

  public void mousePressed(MouseEvent e) {
    adaptee.jPanel_graphics_mousePressed(e);
  }

  public void mouseReleased(MouseEvent e) {
    adaptee.jPanel_graphics_mouseReleased(e);
  }
}

class BezierFrame_jButton_generateCurve_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jButton_generateCurve_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton_generateCurve_actionPerformed(e);
  }
}

class BezierFrame_jButton_editControlPoint_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jButton_editControlPoint_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton_editControlPoint_actionPerformed(e);
  }
}

class BezierFrame_jPanel_graphics_mouseMotionAdapter
    extends java.awt.event.MouseMotionAdapter {
  BezierFrame adaptee;

  BezierFrame_jPanel_graphics_mouseMotionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseDragged(MouseEvent e) {
    adaptee.jPanel_graphics_mouseDragged(e);
  }

  public void mouseMoved(MouseEvent e) {
    adaptee.jPanel_graphics_mouseMoved(e);
  }
}

class BezierFrame_jButton_clear_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jButton_clear_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton_clear_actionPerformed(e);
  }
}

class BezierFrame_jRadioButton_Bezier3_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jRadioButton_Bezier3_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton_Bezier3_actionPerformed(e);
  }
}

class BezierFrame_jRadioButton_b2_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jRadioButton_b2_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton_b2_actionPerformed(e);
  }
}

class BezierFrame_jRadioButton_b3_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jRadioButton_b3_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton_b3_actionPerformed(e);
  }
}

class BezierFrame_jRadioButton_Bezier2_actionAdapter
    implements java.awt.event.ActionListener {
  BezierFrame adaptee;

  BezierFrame_jRadioButton_Bezier2_actionAdapter(BezierFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton_Bezier2_actionPerformed(e);
  }
}
