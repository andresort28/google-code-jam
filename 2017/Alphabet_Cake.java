package googlecodejam.c2017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Alphabet_Cake {

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("D:/INPUT.txt")));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/OUTPUT.txt")));
				
		String line = in.readLine();				
		int cases = Integer.parseInt(line);	
		for (int i = 0; i < cases; i++) 
		{
			line = in.readLine();
			String[] numbers = line.split(" ");
			
			int rows = Integer.parseInt(numbers[0]);
			int cols = Integer.parseInt(numbers[1]);
			char[][] grid = new char[rows][cols];
			ArrayList<Character> list = new ArrayList<>();
			Hashtable<Character, int[]> positions = new Hashtable<>();
			
			for (int r = 0; r < rows; r++) 
			{
				line = in.readLine();
				for (int c = 0; c < cols; c++) 
				{
					grid[r][c] = line.charAt(c);
					if(grid[r][c] != '?' && c != 0 && r != 0 && c != cols-1 && r != rows-1)
					{
						list.add(grid[r][c]);
						positions.put(grid[r][c], new int[]{r,c});
					}					
				}
			}
			
			for (int z = 0; z < list.size(); z++)
			{
				char letter = list.get(z);
				int[] pos = positions.get(letter);
				int r = pos[0];
				int c = pos[1];
				
				if(checkHorizontal(0, c-1, r, grid))
					FillHorizontal(0, c-1, r, grid, letter);
				else if(checkHorizontal(c+1, cols-1, r, grid))
					FillHorizontal(c+1, cols-1, r, grid, letter);
				else if(checkVertical(0, r-1, c, grid))
					FillVertical(0, r-1, c, grid, letter);
				else if(checkVertical(r+1, rows-1, c, grid))
					FillVertical(r+1, rows-1, c, grid, letter);					
			}
			
			//Lo deshabilite mientras tanto
			//La manera correcta de llenarlo es tratando de armar el rentangulo mas grande partiendo desde la letra existente, y mirando lo ya lleno
			//Toca volverlo a plantear como acabo de mencionar anteriomente. El siguiente while se queda iterando porque nunca crea cuadrado, solo rentagulos de 1 x N
			while(true)
			{
				boolean incomplete = false;
				for (int r = 0; r < rows; r++) 
				{
					for (int c = 0; c < cols; c++) 
					{
						if(grid[r][c] == '?')
						{						
							if( c-1 >= 0 && grid[r][c-1] != '?' && !existVertical(r-1, r+1, c-1, grid, grid[r][c-1]))
								grid[r][c] = grid[r][c-1];
							else if( r-1 >= 0 && grid[r-1][c] != '?' && !existHorizontal(c-1, c+1, r-1, grid, grid[r-1][c]))
								grid[r][c] = grid[r-1][c];
							else if( c+1 < cols && grid[r][c+1] != '?' && !existVertical(r-1, r+1, c+1, grid, grid[r][c+1]))
								grid[r][c] = grid[r][c+1];
							else if( r+1 < rows && grid[r+1][c] != '?' && !existHorizontal(c-1, c+1, r+1, grid, grid[r+1][c]))
								grid[r][c] = grid[r+1][c];
							else
								incomplete = true;
						}					
					}
				}
				if(!incomplete)
					break;
			}
			
			
			if((i+1) == 1)
				out.write("Case #"+(i+1)+":\n");
			else
				out.write("\nCase #"+(i+1)+":\n");
			
			for (int r = 0; r < rows; r++) 
			{
				String row = "";
				for (int c = 0; c < cols; c++) 
				{
					row += grid[r][c];	
				}
				
				if(r+1 == rows)
					out.write(row);
				else
					out.write(row+"\n");
			}
		}
		
		
		
		in.close();
		out.flush();
		out.close();
		System.exit(0);
	}
	
	
	public static boolean checkHorizontal (int a, int b, int r, char[][] grid)
	{
		for (int i = a; i <= b; i++)
		{
			if(grid[r][i] != '?')
				return false;
		}		
		return true;
	}
	
	public static boolean checkVertical (int a, int b, int c, char[][] grid)
	{
		for (int i = a; i <= b; i++)
		{
			if(grid[i][c] != '?')
				return false;
		}		
		return true;
	}
	
	public static void FillHorizontal (int a, int b, int r, char[][] grid, char letter)
	{
		for (int i = a; i <= b; i++)
			grid[r][i] = letter;
	}
	
	public static void FillVertical (int a, int b, int c, char[][] grid, char letter)
	{
		for (int i = a; i <= b; i++)
			grid[i][c] = letter;
	}
	
	public static boolean existVertical (int r1, int r2, int c, char[][] grid, char letter)
	{
		if((r1 >= 0 && grid[r1][c] == letter) || (r2 <= grid.length-1 && grid[r2][c] == letter))
			return true;		
		return false;
	}
	
	public static boolean existHorizontal (int c1, int c2, int r, char[][] grid, char letter)
	{
		if((c1 >= 0 && grid[r][c1] == letter) || (c2 <= grid[0].length-1 && grid[r][c2] == letter))
			return true;		
		return false;
	}

}
