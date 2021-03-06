package com;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

import javax.swing.filechooser.FileFilter;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class NLP  extends JFrame {

	/**
	 * 
	 */
	 String str="SADEWRD";
	private static final long serialVersionUID = 1L;
	String name;
	private final Color colorvalues[]={Color.black,Color.blue,Color.red,Color.green};
	private JRadioButtonMenuItem colorItems[], fonts[];
	   private JCheckBoxMenuItem styleItems[];
	   private JLabel displayLabel;
	   private ButtonGroup fontGroup, colorGroup;
	   private int style;
	
	JTextArea textArea = new TextAreaMenu();

	public NLP(){
		setResizable(false);
		setLocation(new Point(450, 200));
		setSize(new Dimension(510, 500));
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"D:/JavaProject/nlp/re060323004.jpg"));
		setTitle("汉字树库系统");
		getContentPane().setLayout(null);

		textArea.setBounds(10, 10, 470, 150);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(477, 10, 17, 151);
		getContentPane().add(textArea);
		textArea.add(scrollBar);

		JButton button_1 = new JButton("生成");
		button_1.setBounds(400, 165, 80, 25);
		getContentPane().add(button_1);

		JTextArea JTextArea = new JTextArea();
		JTextArea.setText("搜索");
		JTextArea.setBounds(10, 165, 150, 25);
		getContentPane().add(JTextArea);

		
		JButton btnNewButton = new JButton("统计");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public int sob(String s)   //查找相关句型
		    {
		    	int i=0,u=0,i1;
		    	while(i<str.length())
		    	{
		    		i1=str.indexOf(s,i);
		    		if(i1!=-1&&i1!=i)
		    		{
		    			u++;
		    		}
		    		else return u;
		    		i=i1;
		    	}
		    	return u;
		    }
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "句子数目="+sob("A")+'\n'+"主谓结构数="+'\n'+"主谓宾结构数="+'\n');
			}
		});
		
		btnNewButton.setBounds(425, 420, 68, 25);
		getContentPane().add(btnNewButton);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("文件（F）");
		menu.setMnemonic('F');
		menuBar.add(menu);

		JMenuItem menuItem_1 = new JMenuItem("打开（O）");
		menuItem_1.setMnemonic('O');
		menu.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() { // 定义"打开"组件操作

					public void actionPerformed(ActionEvent arg0) {
						chooseToOpen();
					}

				});

		JMenuItem menuItem_2 = new JMenuItem("保存（S）");
		menuItem_2.setMnemonic('S');
		menu.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener() { // 定义"打开"组件操作

					public void actionPerformed(ActionEvent arg0) {
						chooseToSave();
					}

				});

		JMenuItem menuItem_3 = new JMenuItem("关闭（E）");
		menuItem_3.setMnemonic('E');
		menu.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() { // 定义"关闭"组件操作

					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});

		JMenu menu_1 = new JMenu("编辑（D）");
		menu_1.setMnemonic('D');
		menuBar.add(menu_1);

		JMenuItem menuItem_6 = new JMenuItem("剪切（X）");
		menuItem_6.setMnemonic('X');
		menu_1.add(menuItem_6);

		JMenuItem menuItem_4 = new JMenuItem("复制（C）");
		menuItem_3.setMnemonic('C');
		menu_1.add(menuItem_4);

		JMenuItem menuItem_5 = new JMenuItem("粘贴（V）");
		menuItem_5.setMnemonic('V');
		menu_1.add(menuItem_5);

		JMenuItem menuItem_7 = new JMenuItem("查找（F）");
		menuItem_7.setMnemonic('F');
		menu_1.add(menuItem_7);
		
		JMenu menu_3=new JMenu("格式（F）");
		menu_3.setMnemonic('F');
		menuBar.add(menu_3);
		

		JMenu menu_2 = new JMenu("帮助（H）");
		menu_2.setMnemonic('H');
		menuBar.add(menu_2);

		JMenuItem menuItem_8 = new JMenuItem("关于（A）");
		menuItem_8.setMnemonic('A');
		menuItem_8.addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "汉字树库系统\n...\n...", "关于",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menu_2.add(menuItem_8);

		// close
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}
		});
		String colors[] = { "Black", "Blue", "Red", "Green" };

	      JMenu colorMenu = new JMenu( "Color" );
	      colorMenu.setMnemonic( 'C' );

	      colorItems = new JRadioButtonMenuItem[ colors.length ];
	      colorGroup = new ButtonGroup();

	      // create color radio button menu items
	      for ( int count = 0; count < colors.length; count++ ) {
	         colorItems[ count ] = 
	            new JRadioButtonMenuItem( colors[ count ] );
	         colorMenu.add( colorItems[ count ] );
	         colorGroup.add( colorItems[ count ] );
	         //colorItems[ count ].addActionListener( itemHandler );
	      }

	      // select first Color menu item
	      colorItems[ 0 ].setSelected( true );  

	      // add format menu to menu bar
	      menu_3.add( colorMenu );
	      menu_3.addSeparator();

	      // create Font submenu
	      String fontNames[] = { "Serif", "Monospaced", "SansSerif" };

	      JMenu fontMenu = new JMenu( "Font" );
	      fontMenu.setMnemonic( 'n' );

	      fonts = new JRadioButtonMenuItem[ fontNames.length ];
	      fontGroup = new ButtonGroup();

	      // create Font radio button menu items
	      for ( int count = 0; count < fonts.length; count++ ) {
	         fonts[ count ] = new JRadioButtonMenuItem( fontNames[ count ] );
	         fontMenu.add( fonts[ count ] );
	         fontGroup.add( fonts[ count ] );
	         //fonts[ count ].addActionListener( itemHandler );
	      }

	      // select first Font menu item
	      fonts[ 0 ].setSelected( true );

	      fontMenu.addSeparator();

	      // set up style menu items
	      String styleNames[] = { "Bold", "Italic" };

	      styleItems = new JCheckBoxMenuItem[ styleNames.length ];

	      // create style checkbox menu items
	      for ( int count = 0; count < styleNames.length; count++ ) {
	         styleItems[ count ] = 
	            new JCheckBoxMenuItem( styleNames[ count ] );
	         fontMenu.add( styleItems[ count ] );
	         //styleItems[ count ].addItemListener( styleHandler );
	      }

	      // put Font menu in Format menu
	      menu_3.add( fontMenu );

	      // add Format menu to menu ba
	     

	}
	 public int sob(String s)   //查找相关句型
	    {
	    	int i=0,u=0;
	    	while(i<s.length())
	    	{
	    		i=str.indexOf(s,i);
	    		if(i!=-1)
	    		{
	    			u++;
	    		}
	    	}
	    	return u;
	    }
	protected void chooseToSave() {

		File file = chooseFile();
		if (null == file)
			return;
		if (file.exists()) {
			int cho = JOptionPane.showConfirmDialog(this, "文件已存在，是否覆盖？");
			System.out.println(cho);
			if (cho == JOptionPane.OK_OPTION)
				save(file);
			else
				return;
		} else
			save(file);

	}

	private void save(File file) {
		name = file.getName();
		write(textArea.getText(), file.getPath());

	}

	protected void chooseToOpen() {
		File file = chooseFile();
		if (null == file || !file.exists())
			return;
		name = file.getName();
		NLP.this.setTitle(name + " ");
		read(textArea, file);
	}

	private File chooseFile() {

		JFileChooser chooser = new JFileChooser(); // 构建文件选择器
		chooser.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "文本文件";
			}

			@Override
			public boolean accept(File f) {
				String name = f.getName().toLowerCase();

				return f.isDirectory() || name.endsWith(".txt")
						|| name.endsWith(".c") || name.endsWith(".java")
						|| name.endsWith(".cpp") || name.endsWith(".xml"); // 可识别文件
			}
		});
		int result = chooser.showDialog(null, "确定");
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			System.out.println(file.getAbsolutePath());
		} else {
			System.out.println("未选择文件");
		}
		return chooser.getSelectedFile();
	}

	public static void read(JTextArea text, File file) { // 定义读取文件操作
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String string = null;
			while ((string = br.readLine()) != null) {
				text.append(string + "\n");
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String txt, String fileName) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(txt);
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	public static void main(String[] args) {
		NLP dda=new NLP();
	}
}
