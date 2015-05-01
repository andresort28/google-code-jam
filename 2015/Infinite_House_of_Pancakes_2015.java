package googlecodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import javax.swing.text.html.MinimalHTMLWriter;

public class Infinite_House_of_Pancakes_2015 {

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:/input.in")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/output.txt")));
		//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String line;		
		StringTokenizer tokens;
		line = in.readLine();
		int cases = Integer.parseInt(line);			
		for (int n = 0; n < cases; n++) 
		{
			int d = Integer.valueOf(line = in.readLine());
			int[] numbers = new int[d];		
			
			line = in.readLine();
			tokens = new StringTokenizer(line);
			int max = -1;
			for (int i = 0; i < numbers.length; i++) {				
				numbers[i] = Integer.valueOf(tokens.nextToken());
				if(numbers[i] > max)
					max = numbers[i];
			}		
			
			int[] counts = new int[max + 1];
			for (int i : numbers)
				counts[i]++;
			
			int min = Integer.MAX_VALUE;
			for (int part = 1; part <= counts.length; part++) {
				int moves = 0;
				for (int i = 0; i < counts.length; i++) {
					moves += ((i-1)/part)*counts[i];
				}
				if(moves + part < min)
					min = moves + part;
			}
			out.write("Case #"+(n+1)+": "+ min +"\n");			
		}
		in.close();
		out.flush();
		out.close();
		System.exit(0);
	}
}


