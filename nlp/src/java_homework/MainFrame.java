package java_homework;

import java.io.*;
import java.math.*;
import java.lang.*;
import java.util.*;
//import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.dictionary.Dictionary;
import opennlp.tools.lang.english.*;
import opennlp.tools.parser.*;
import opennlp.tools.postag.*;
import opennlp.tools.sentdetect.*;
import opennlp.tools.tokenize.*;
import opennlp.tools.util.*;
import opennlp.tools.*;
import opennlp.*;
import opennlp.tools.cmdline.parser.ParserTool;

public class MainFrame
{
    
    public static void main(String[] argus)
    {
        InputStream modelIn=null;
        SentenceModel model=null;
        
        try
        {
            modelIn=new FileInputStream("D:\\JavaProject\\nlp\\en-sent.bin");
        }
        catch(IOException e)
        {
            System.out.println(e.toString()+"1");
            return;
        }


        try
        {
            model=new SentenceModel(modelIn);
        }
        catch(IOException e)
        {
            System.out.println(e.toString()+"2");
            return;
        }
        finally
        {
            if(modelIn!=null)
            {
                try
                {
                    modelIn.close();
                }
                catch(IOException e)
                {
                    System.out.println(e.toString()+"3");
                    return;
                }
            }
        }
        //SentenceDetectorME sentenceDetector=new SentenceDetectorME(model);
        //String sentences[]=sentenceDetector.sentDetect(readFile(new File("F:\\a.txt")));
        //Span sentences2[]=sentenceDetector.sentPosDetect("  First sentence. Second sentence. ");
        //for(int i=0;i<sentences.length;i++)
        //{
        //    System.out.println(sentences[i]+"  "+Integer.toString(i));
        //}
        /*
         * for(int i=0;i<sentences2.length;i++) {
         * System.out.println(sentences2[i]+" "+Integer.toString(i)); }
         */



        try
        {
            InputStream modelIn2=new FileInputStream("D:\\JavaProject\\nlp\\en-parser-chunking.bin");
            ParserModel model2=new ParserModel(modelIn2);
            Parser parser = ParserFactory.create(model2);
            Parse topParses[] = ParserTool.parseLine(readFile(new File("D:\\JavaProject\\nlp\\a.txt")), parser, 1);

            for(int i=0;i<topParses.length;i++)
            {
                topParses[i].show();
                System.out.println(Integer.toString(i)+'\n'+'\n');
            }

        }
        catch(IOException e)
        {
            System.out.println(e.toString()+"4");
        }

        finally
        {
            if(modelIn!=null)
            {
                try
                {
                    modelIn.close();
                }
                catch(IOException e)
                {
                }
            }
        }
    }

    public static String readFile(File file)
    {
        if(!file.exists())
        {
            return "";
        }
        else
        {
            Reader reader;
            String ans="";
            try
            {
                reader=new InputStreamReader(new FileInputStream(file));
                int tempchar;
                while((tempchar=reader.read())!=-1)
                {
                    if(((char)tempchar)!='\r')
                    {
                        ans+=(char)(tempchar);
                    }
                }
                reader.close();
                return ans;
            }
            catch(IOException e)
            {
                System.out.println(e.toString()+"4");
                return "";
            }
        }
    }
}

/*
public class MainFrame extends JFrame implements ActionListener
{

    public MainFrame()
    {
        this.setLayout(new BorderLayout());

        this.setFocusable(true);
        this.addWindowListener(new WindowListener());
        init();
        setLocation(200,200);
        setVisible(true);
        pack();
        //timer.start();
    }


    private void init()
    {
        WordNode a,b;
        a=new WordNode("example a");
        b=new WordNode("example b");
        this.add(a);this.add(b);
    }

    public static void main(String[] argus)
    {
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    //Listeners below

    //Window Listener
    public class WindowListener extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            //exitEditor();
            dispose();
            System.exit(0);
        }
    }
}
*/