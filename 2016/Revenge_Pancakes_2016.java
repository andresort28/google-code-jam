package googlecodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;

public class Revenge_Pancakes_2016 {


	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:/B-large-practice.in")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/OUTPUT.txt")));
				
		String line = in.readLine();				
		int cases = Integer.parseInt(line);	
		for (int i = 0; i < cases; i++) 
		{
			line = in.readLine();
			
			if(line.equals("-") || line.equals("-+") || allNegative(line))
				out.write("Case #"+(i+1)+": "+ "1" +"\n");
			else if(line.equals("+-"))
				out.write("Case #"+(i+1)+": "+ "2" +"\n");
			else if(allPositive(line))
				out.write("Case #"+(i+1)+": "+ "0" +"\n");			
			else
			{
				int total = 0;
				while(!allPositive(line))
				{
					if(topPositive(line) && bottomPositive(line))
					{
						line = topPosNeg(line);
						total++;
						line = topNegPos(line);
						total++;
					}					
					else if(bottomNegative(line))
					{
						if(topNegative(line))
						{
							line = topNegPos(line);
							total++;
						}
						line = invertir(line);
						total++;
						line = topNegPos(line);
						total++;
					}
					else if(bottomPositive(line)){
						line = topNegPos(line);
						total++;
					}
				}
				out.write("Case #"+(i+1)+": "+ total +"\n");	
			}
			
		}
		in.close();
		out.flush();
		out.close();
		System.exit(0);
	}
			
	public static boolean allPositive (String line)
	{
		for (int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c == '-')
				return false;
		}
		return true;
	}
	
	public static boolean allNegative (String line)
	{
		for (int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c == '+')
				return false;
		}
		return true;
	}
	
	public static boolean bottomNegative (String line){
		
		char c = line.charAt(line.length()-1);
		if(c == '-')
			return true;
		return false;
	}
	
	public static boolean bottomPositive (String line){
		
		char c = line.charAt(line.length()-1);
		if(c == '+')
			return true;
		return false;
	}
	
	public static boolean topNegative (String line){
		
		char c = line.charAt(0);
		if(c == '-')
			return true;
		return false;
	}
	
	public static boolean topPositive (String line){
		
		char c = line.charAt(0);
		if(c == '+')
			return true;
		return false;
	}

	
	public static String topNegPos (String line)
	{		
		int j = -1;
		for (int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c == '+')
			{
				j = i;
				break;
			}
		}
		String change = "";
		for (int i = 0; i < j; i++)
			change += "+";
		
		return change + line.substring(j);		
	}
	
	public static String invertir (String line)
	{
		String line2 = "";
		for (int i = line.length()-1; i >=0 ; i--)
		{
			char c = line.charAt(i);
			line2 += c;
		}
		return line2;
	}
	
	public static String topPosNeg (String line)
	{		
		int j = -1;
		for (int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c == '-')
			{
				j = i;
				break;
			}
		}
		String change = "";
		for (int i = 0; i < j; i++)
			change += "-";
		
		return change + line.substring(j);		
	}
	

}
