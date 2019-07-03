package googlecodejam.c2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tidy_Numbers {

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:/B-large.in")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/OUTPUT.txt")));
				
		String line = in.readLine();				
		int cases = Integer.parseInt(line);	
		for (int i = 0; i < cases; i++) 
		{
			line = in.readLine();
			int[] numbers = new int[line.length()];
			for (int j = 0; j < numbers.length; j++) 
			{
				numbers[j] = Integer.parseInt(line.charAt(j)+"");
			}
			
			while(!isTidy(numbers))
			{
				for (int j = numbers.length-1; j >=0 ; j--)
				{
					if((j-1) >= 0 && numbers[j] < numbers[j-1])
					{
						for (int k = j+1; k < numbers.length; k++) 
						{
							numbers[k] = 9;
						}
						numbers[j] = 9;
						numbers[j-1] = numbers[j-1] - 1;
					}
				}
			}		
			out.write("Case #"+(i+1)+": "+ convertToInteger(numbers) +"\n");
			
		}
		in.close();
		out.flush();
		out.close();
		System.exit(0);
		

	}
	
	public static boolean isTidy (int[] numbers)
	{
		for (int j = numbers.length-1; j >=0 ; j--)
		{
			if((j-1) >= 0 && numbers[j] < numbers[j-1])
				return false;
		}
		return true;
	}
	
	public static String convertToInteger (int[] numbers)
	{
		String merge = "";
		for (int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] > 0)
				merge += numbers[i] + "";
		}
		return merge;
	}

}
