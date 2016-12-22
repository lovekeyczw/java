package a;
//�����࣬��Ҫʹ�������еľ���˷�����һ����ȡһ��һ�о����ֵ�ķ�����

public class Matrix {
	private double[][] array; 		//������һ����ά����
	private int size_r,size_c;	//���������������
	/**
	 * Ĭ�ϵĹ��췽��
	 */
	public  Matrix(){
		this.array = null;
		this.size_r = 0;
		this.size_c = 0;
	}
	/**
	 * ����ά���鹹��Ϊ����
	 * @param arg ��ά����
	 */
	public  Matrix(double [][]arg){
		this.array = arg;
		this.size_r = arg.length;
		this.size_c = arg[0].length;
	}
	/**
	 * ����һ����֪�������ľ���
	 * @param r ����
	 * @param c ����
	 */
	public  Matrix(int r,int c){	//�������ǹ��ڲ����ؽ������ʹ�õ�
		this.array =new double[r][c];
		this.size_r = r;
		this.size_c = c;
	}
	/**
	 * ����ӷ�
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Matrix MatrixAdd(Matrix arg1,Matrix arg2){
		if((arg1.size_r==arg2.size_r)&&(arg1.size_c==arg2.size_c)){//����������ȵ�ʱ��ſ������
			int i,j;
			Matrix result = new Matrix(arg1.size_r,arg1.size_c);
			for(i=0;i<arg1.size_r;i++)
				for(j=0;j<arg1.size_c;j++){
					result.array[i][j] = arg1.array[i][j] + arg2.array[i][j];
				}
			return result;	//���ؾ���ӷ��Ľ��
		}
		else return null;	//�������ά������ȣ�������ӣ��򷵻�Ϊ��
	}

	/**
	 * ����˷�
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static Matrix MatrixMul(Matrix arg1,Matrix arg2){ 
		if(arg1.size_c==arg2.size_r){//ֻ�е���һ������������͵ڶ���������������ʱ�ſ��Խ��о���˷�
			int i,j,k;
			double temp;
			Matrix result = new Matrix(arg1.size_r,arg2.size_c);
			for(i=0;i<arg1.size_r;i++){
				for(j=0;j<arg2.size_c;j++){
					temp = 0;
					for(k=0;k<arg1.size_c;k++){
						temp+=arg1.array[i][k]*arg2.array[k][j];
					}
					result.array[i][j] = temp;
				}
			}
			return result;
		}
		else return null;//���������������ˣ����ؿ�
	}
	
	public double[][] getArray() {
		return array;
	}
	
	public void setArray(double[][] array) {
		this.array = array;
	}
	
	
	public int getResult() {
		return (int)(1.0/6 * array[0][0]);
	}


	
	
	
}
