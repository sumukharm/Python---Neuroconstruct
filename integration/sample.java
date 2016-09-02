import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class sample {

    public static void main(String[] args) {

	try
	{
		BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Z1: ");
		String line1=buffer.readLine();
		System.out.println("Z2: ");	
		String line2=buffer.readLine();	
		//System.out.println(line1);
		//System.out.println(line2);
		String line3="python better_plot.py "+line1+" "+line2;
		System.out.println(line3);
	}
	catch(IOException ex3)
	{}

    }
} 

