package googlecodejam.c2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;

public class Oversized_Pancake_Flipper {

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:/A-large.in")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/OUTPUT.txt")));
				
		String line = in.readLine();				
		int cases = Integer.parseInt(line);	
		for (int i = 0; i < cases; i++) 
		{
			line = in.readLine();
			String[] parts = line.split(" ");
			String pancakes = parts[0];
			int k = Integer.parseInt(parts[1]);	
			
			
			if(allPositive(pancakes))
				out.write("Case #"+(i+1)+": "+ "0" +"\n");	
			else if(checkInability(pancakes, k))
				out.write("Case #"+(i+1)+": "+ "IMPOSSIBLE" +"\n");		
			else
			{
				int total = 0;
				Hashtable<String, String> table = new Hashtable<String, String>();
				boolean left_right = true;
				
				while(!allPositive(pancakes) && !checkInability(pancakes, k))
				{
					if(left_right)
					{
						pancakes = leftToRight(pancakes, k);
						total++;
						left_right = false;
					}
					else
					{
						pancakes = rightToLeft(pancakes, k);
						total++;
						left_right = true;
					}
					
					//There is a loop
					if(table.containsKey(pancakes))
					{
						System.out.println("Case #"+(i+1)+": LOOP");
						break;
					}
					else
						table.put(pancakes, "");
					
				}
				
				if(allPositive(pancakes))
					out.write("Case #"+(i+1)+": "+ total +"\n");	
				else	
					out.write("Case #"+(i+1)+": "+ "IMPOSSIBLE" +"\n");	
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
	
	public static int[] consecutiveNegativesOnly (String line)
	{
		boolean coutingNegatives = false;
		int negatives = 0;
		for (int i = 0; i < line.length(); i++)
		{
			char c = line.charAt(i);
			if(c == '-')
			{
				if(!coutingNegatives)
				{
					//There are many parts of consecutives "negatives". Ex: ++++---+++----++++---++++
					if(negatives > 0)
						return new int[] {0, 0};
					else
					{
						coutingNegatives = true;
						negatives++;
					}
				}
				else
					negatives++;		
			}
			else
				coutingNegatives = false;
		}
		//There are only one part of consecutives "negatives". Ex: ++++++---++++++++++++
		return new int[] {1, negatives};
	}
	
	public static boolean checkInability(String line, int k)
	{
		int[] consNeg = consecutiveNegativesOnly(line);
		
		if(consNeg[0] == 1 && consNeg[1] < k)
			return true;
		
		return false;
	}
	
	public static String invert (String line)
	{
		String line2 = "";
		for (int i = line.length()-1; i >=0 ; i--)
		{
			char c = line.charAt(i);
			line2 += c;
		}
		return line2;
	}
	
	public static String flip (String pancakes)
	{
		String pancakes_flip = "";
		for (int i = 0; i < pancakes.length(); i++)
		{
			char c = pancakes.charAt(i);
			if(c == '-')
				pancakes_flip += "+";
			else
				pancakes_flip += "-";
		}		
		return pancakes_flip;
	}
	
	public static String leftToRight(String pancakes, int k)
	{
		for (int i = 0; i < pancakes.length(); i++)
		{
			char c = pancakes.charAt(i);
			if(c == '-')
			{
				if((i+k-1) <= pancakes.length()-1)
				{
					return pancakes.substring(0, i) + flip(pancakes.substring(i, i+k)) + pancakes.substring(i+k);
				}
				else
					return pancakes;			
			}				
		}		
		return "";
	}
	
	public static String rightToLeft(String pancakes, int k)
	{
		String inverted_pancakes = invert(pancakes);
		String result = leftToRight(inverted_pancakes, k);
		
		return invert(result);
	}

}
