import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class Block {
	int[][] block;
	public static final int n = 3;

	public Block() {
		block = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				block[i][j] = 0;
	}

	public void test(){
		/* 0 2 1
		 * 1 0 2
		 * 2 1 0
		 */
		Block latin1 = new Block();
		{
			latin1.block[0][1] = 2;
			latin1.block[0][2] = 1;
			latin1.block[1][0] = 1;
			latin1.block[1][2] = 2;
			latin1.block[2][0] = 2;
			latin1.block[2][1] = 1;
		}
		/* 0 2 1
		 * 2 1 0
		 * 1 0 2
		 */
		Block latin2 = new Block();
		{
			latin2.block[0][1] = 2;
			latin2.block[0][2] = 1;
			latin2.block[1][0] = 2;
			latin2.block[1][2] = 1;
			latin2.block[2][0] = 1;
			latin2.block[2][1] = 2;
		}
		/* 1 0 2
		 * 2 1 0
		 * 0 2 1
		 */
		Block latin3 = new Block();
		{
			latin3.block[0][0] = 1;
			latin3.block[0][2] = 2;
			latin3.block[1][0] = 2;
			latin3.block[1][1] = 1;
			latin3.block[2][1] = 2;
			latin3.block[2][2] = 1;
		}
		/* 1 0 2
		 * 0 2 1
		 * 2 1 0
		 */
		Block latin4 = new Block();
		{
			latin4.block[0][0] = 1;
			latin4.block[0][2] = 2;
			latin4.block[1][1] = 2;
			latin4.block[1][2] = 1;
			latin4.block[2][0] = 2;
			latin4.block[2][1] = 1;
		}
		/* 0 1 2
		 * 2 0 1
		 * 1 2 0
		 */
		
		
		
		
		//print
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				System.out.print(latin1.block[i][j]+((j==n-1)?"":" "));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static ArrayList<Block> generateBlock(ArrayList<int[]> a) {
		ArrayList<Block> p = new ArrayList<Block>();

		Block latin1 = new Block();
		{
			latin1.block[0][1] = 2;
			latin1.block[0][2] = 1;
			for (int col = 0; col < n; col++)
			{
				for (int row = 0; row < n; row++)
				{
					latin1.block[row][col] = 1 + (latin1.block[row-1][col]) % n; 
				}
			}
		}
		
		
		
		//print
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				System.out.print(latin1.block[i][j]+((j==n-1)?"":","));
			}
			System.out.println();
		}
		System.out.println();
		
		return p;
	}

	public boolean checkCol(int id) {
		for (int i = 0; i < n; i++) // kiem tra tu dong dau tien tro xuong
		{
			for (int j = 0; j < i; j++)
				if (block[i][id] == block[j][id])
					return false;
			for (int j = i + 1; j < n; j++)
				if (block[i][id] == block[j][id])
					return false;
		}
		return true;
	}

	public boolean checkRow(int id) {
		for (int i = 0; i < n; i++) // kiem tra tu cot dau tien tu trai sang
									// phai
		{
			for (int j = 0; j < i; j++)
				if (block[id][i] == block[j][id])
					return false;
			for (int j = i + 1; j < n; j++)
				if (block[id][i] == block[j][id])
					return false;
		}
		return true;
	}

	public static void main(String args[]){
		Block a = new Block();
		a.test();
	}
}
