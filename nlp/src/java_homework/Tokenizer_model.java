package java_homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokenizer_model {
	
	public void Tokenizer_model_out(String outputfilename) throws FileNotFoundException {
		InputStream modelIn=null;
		TokenizerModel model=null;
		try {
		  modelIn = new FileInputStream("D:\\JavaProject\\nlp\\en-parser-chunking.bin");
		  model = new TokenizerModel(modelIn);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		Tokenizer tokenizer = new TokenizerME(model);

		

		// 读入数据集
		String s;
		PrintWriter printout=new PrintWriter(outputfilename);
		BufferedReader buf_reader = null;
		try {
			File f = new File("D:\\JavaProject\\nlp\\a.txt");
			FileReader r_file = new FileReader(f);
			buf_reader = new BufferedReader(r_file);
			while((s=buf_reader.readLine())!=null)
			{	
				String tokens[] = tokenizer.tokenize(s+" ");
				for(int i=0;i<tokens.length;i++)
				printout.print(tokens[i]+"   ");
				printout.println();
			}
			printout.close();
			buf_reader.close();
		} catch (IOException e1) {

		}
		

		//String sent[] = new String[] { "Most", "large", "cities", "in", "the",
				//"US", "had", "morning", "and", "afternoon", "newspapers", "." };
		
	

	}

//	public void Tokenizer_model_out(String outputfilename) {
//		// TODO Auto-generated method stub
//		
//	}
}
