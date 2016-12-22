package a;

//B�������ߵ���Ҫ�㷨�ࡣ�����������������εĵ���������飬drawBLine(Graphics g) ����Ҫ��B�����ķ������÷���ʵ�ֻ�m+1��3��B���ߣ�����������������Ρ�

import java.awt.*;

public class BLine {
	int[] px;	//��������ε�x��������
	int[] py;	//��������ε�y��������
	int[] x = new int[101];	//���ߵ�x����
	int[] y = new int[101];	//���ߵ�y����
	double[][] tempx;	//pi,pi+1,pi+2,pi+3	
	double[][] tempy;	//pi,pi+1,pi+2,pi+3
	int times;	//���ߵĶ������� m+1 ��ֵ���˴�nΪ3
	Matrix M;	//��������
	public BLine(){
		this.px = new int[]{100,200,300,400};
		this.py = new int[]{100,300,400,100};
		this.tempx = new double[4][1];
		this.tempy = new double[4][1];
		this.times = 1;
		double[][] mArray = {{-1,3,-3,1},{3,-6,3,0},{-3,0,3,0},{1,4,1,0}};
		this.M = new Matrix(mArray);
	}
	
	public BLine(int[] px, int[] py){
		this.px = px;
		this.py = py;
		this.tempx = new double[4][1];
		this.tempy = new double[4][1];
		this.times = px.length-3;
		double[][] mArray = {{-1,3,-3,1},{3,-6,3,0},{-3,0,3,0},{1,4,1,0}};
		this.M = new Matrix(mArray);
	}
	
	public void drawBLine(Graphics g){
		g.setColor(Color.red);
		
		int i,j;
		double t;
		double[][] tArray = new double[1][4];
		g.setColor(Color.green);
		//�� m+n+1 ���㣬����Ҫ m+1 �����߶�
		for(j=0;j<times;j++){
			for(i=0;i<4;i++){
				tempx[i][0]=px[j+i];
				tempy[i][0]=py[j+i];				
			}
			Matrix Px = new Matrix(tempx);
			Matrix Py = new Matrix(tempy);	
			
			//t��[0,1],�Ѹ�����ֳ�100��
			for(i=0;i<=100;i++){
				t=i*0.01;
				tArray[0][3]=1;
				tArray[0][2]=t;
				tArray[0][1]=tArray[0][2]*t;
				tArray[0][0]=tArray[0][1]*t;
				Matrix T = new Matrix(tArray);
				Matrix TM = Matrix.MatrixMul(T, M);
				x[i] = Matrix.MatrixMul(TM, Px).getResult();//����1/6������ֱ�ӷŵ������getResult()����
				y[i] = Matrix.MatrixMul(TM, Py).getResult();
			}
			g.drawPolyline(x, y, 101);
		}

		
	}
}
