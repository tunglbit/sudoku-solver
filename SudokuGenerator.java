// Name: Le Bui Thanh Tung
// Student ID: 51503362

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.*;

/**
 * The SudokuGenerator class creates a random standard (9x9) Sudoku board
 * through the use of permutation techniques.
 */
public class SudokuGenerator {

	public static final int n = 3;
	public Writer fileOut;
	private ArrayList<Block> boardS;

	/**
	 * Constructor. Resets board to zeros
	 */
	public SudokuGenerator() {
		boardS = null;
	}

	/**
	 * Driver method for nextBoard.
	 * 
	 * @param difficult
	 *            the number of blank spaces to insert
	 * @return board, a partially completed 9x9 Sudoku board
	 */
	public void generatingSudoku(String output1, int number_blanks) {

		///// Do something here

		// 0 2 1
		// 1 0 2
		// 2 1 0
		Block latin1 = new Block();
		{
			latin1.block[0][1] = 2;
			latin1.block[0][2] = 1;
			latin1.block[1][0] = 1;
			latin1.block[1][2] = 2;
			latin1.block[2][0] = 2;
			latin1.block[2][1] = 1;
		}

		// 0 2 1
		// 2 1 0
		// 1 0 2
		Block latin2 = new Block();
		{
			latin2.block[0][1] = 2;
			latin2.block[0][2] = 1;
			latin2.block[1][0] = 2;
			latin2.block[1][2] = 1;
			latin2.block[2][0] = 1;
			latin2.block[2][1] = 2;
		}
		/*
		 * 1 0 2 2 1 0 0 2 1
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
		/*
		 * 1 0 2 0 2 1 2 1 0
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
		/*
		 * 0 1 2 2 0 1 1 2 0
		 */
		Block latin5 = new Block();
		{
			latin5.block[0][1] = 1;
			latin5.block[0][2] = 2;
			latin5.block[1][0] = 2;
			latin5.block[1][2] = 1;
			latin5.block[2][0] = 1;
			latin5.block[2][1] = 2;
		}
		/*
		 * 0 1 2 1 2 0 2 0 1
		 */
		Block latin6 = new Block();
		{
			latin6.block[0][1] = 1;
			latin6.block[0][2] = 2;
			latin6.block[1][0] = 1;
			latin6.block[1][1] = 2;
			latin6.block[2][0] = 2;
			latin6.block[2][2] = 1;
		}
		/*
		 * 2 0 1 1 2 0 0 1 2
		 */
		Block latin7 = new Block();
		{
			latin7.block[0][0] = 2;
			latin7.block[0][2] = 1;
			latin7.block[1][0] = 1;
			latin7.block[1][1] = 2;
			latin7.block[2][1] = 1;
			latin7.block[2][2] = 2;
		}
		/*
		 * 2 0 1 0 1 2 1 2 0
		 */
		Block latin8 = new Block();
		{
			latin8.block[0][0] = 2;
			latin8.block[0][2] = 1;
			latin8.block[1][1] = 1;
			latin8.block[1][2] = 2;
			latin8.block[2][0] = 1;
			latin8.block[2][1] = 2;
		}
		/*
		 * 2 1 0 0 2 1 1 0 2
		 */
		Block latin9 = new Block();
		{
			latin9.block[0][0] = 2;
			latin9.block[0][1] = 1;
			latin9.block[1][1] = 2;
			latin9.block[1][2] = 1;
			latin9.block[2][0] = 1;
			latin9.block[2][2] = 2;
		}
		/*
		 * 2 1 0 1 0 2 0 2 1
		 */
		Block latin10 = new Block();
		{
			latin10.block[0][0] = 2;
			latin10.block[0][1] = 1;
			latin10.block[1][0] = 1;
			latin10.block[1][2] = 2;
			latin10.block[2][1] = 2;
			latin10.block[2][2] = 1;
		}
		/*
		 * 1 2 0 0 1 2 2 0 1
		 */
		Block latin11 = new Block();
		{
			latin11.block[0][0] = 1;
			latin11.block[0][1] = 2;
			latin11.block[1][1] = 1;
			latin11.block[1][2] = 2;
			latin11.block[2][0] = 2;
			latin11.block[2][2] = 1;
		}
		/*
		 * 1 2 0 2 0 1 0 1 2
		 */
		Block latin12 = new Block();
		{
			latin12.block[0][0] = 1;
			latin12.block[0][1] = 2;
			latin12.block[1][1] = 1;
			latin12.block[1][2] = 2;
			latin12.block[2][0] = 2;
			latin12.block[2][2] = 1;
		}

		Vector<Block> vecLatinSquare = new Vector<Block>();
		vecLatinSquare.add(latin1);
		vecLatinSquare.add(latin2);
		vecLatinSquare.add(latin3);
		vecLatinSquare.add(latin4);
		vecLatinSquare.add(latin5);
		vecLatinSquare.add(latin6);
		vecLatinSquare.add(latin7);
		vecLatinSquare.add(latin8);
		vecLatinSquare.add(latin9);
		vecLatinSquare.add(latin10);
		vecLatinSquare.add(latin11);
		vecLatinSquare.add(latin12);

		int i = 0;
		int j = 0;
		int k = 0;

		// Vector chua 9 Latin Square duoc chon
		Vector<Block> chosenLatinSq = new Vector<Block>();
		Vector<Integer> chosenIndex = new Vector<Integer>();
		Random randomGenerator = new Random();
		for (i = 0; i < 9; i++) {
			int index = randomGenerator.nextInt(12);
			// System.out.println(index);
			if (!chosenIndex.contains(index)) {
				chosenIndex.add(index);
				chosenLatinSq.add(vecLatinSquare.get(index));
			} else
				i--;
		}
		// System.out.println(chosenIndex.toString());
		// System.out.println(chosenLatinSq.toString());

		// for (i = 0; i < 9; i++) {
		// for (j = 0; j < 3; j++)
		// for (k = 0; k < 3; k++) {
		// System.out.println("Block " + i + "["+ j + ", " + k + "] : " +
		// chosenLatinSq.get(i).block[j][k]);
		// }
		//
		// }
		// System.out.println();
		// Thay doi so cua cac Latin Square
		Vector<Block> gottenBlock = new Vector<Block>();

		randomGenerator = new Random();
		Block major = vecLatinSquare.get(randomGenerator.nextInt(12));
		// for (i = 0; i < 3; i++) {
		// for (j = 0; j < 3; j++) {
		// System.out.print(major.block[i][j] + " ");
		// }
		// System.out.println();
		// }

		// int[] mulWith = { 2, 1, 0, 1, 0, 2, 0, 2, 1 };
		for (i = 0; i < 9; i++) {
			Block temp = new Block();
			for (j = 0; j < 3; j++)
				for (k = 0; k < 3; k++) {
					// System.out.println("Block " + i + "[ " + j + " " + k + "]
					// : " + chosenLatinSq.get(i).block[j][k]);
					temp.block[j][k] = chosenLatinSq.get(i).block[j][k] + 3 * major.block[i / 3][i % 3] + 1;
					// System.out.println();
				}
			gottenBlock.add(temp);
		}
		// for (i = 0; i < 9; i++) {
		// for (j = 0; j < 3; j++)
		// for (k = 0; k < 3; k++) {
		// System.out.println("Block " + i + "[" + j + ", " + k + "] : " +
		// gottenBlock.get(i).block[j][k]);
		// }
		// }

		int[][] arraySquare = new int[9][9];
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 3; j++) {
				for (k = 0; k < 3; k++) {
					arraySquare[(i / 3) * 3 + j][(i % 3) * 3 + k] = gottenBlock.get(i).block[j][k];
				}
			}
		}

		// for (i = 0; i < 9; i++) {
		// for (j = 0; j < 9; j++) {
		// System.out.print(arraySquare[i][j] + " ");
		// }
		// System.out.println();
		// }
		// System.out.println();

		// Doi vi tri cac dong 2 & 4, 3 & 7, 6 & 8
		arraySquare = swapRow(arraySquare, 1, 3);
		arraySquare = swapRow(arraySquare, 2, 6);
		arraySquare = swapRow(arraySquare, 5, 7);
		// for (i = 0; i < 9; i++) {
		// for (j = 0; j < 9; j++) {
		// System.out.print(arraySquare[i][j] + " ");
		// }
		// System.out.println();
		// }
		// System.out.println(checkRowCol(arraySquare));
		if (checkRowCol(arraySquare) == false) {
			generatingSudoku(output1, number_blanks);
			return;
		}

		// Duc lo
		randomGenerator = new Random();
		Vector<Integer> diggingSq = new Vector<Integer>();
		for (i = 0; i < number_blanks; i++) {
			int index = randomGenerator.nextInt(81);
			if (!diggingSq.contains(index))
				diggingSq.add(index);
			else
				i--;
		}
		for (i = 0; i < diggingSq.size(); i++) {
			arraySquare[diggingSq.get(i) / 9][diggingSq.get(i) % 9] = 0;
		}

//		 for (i = 0; i < 9; i++) {
//		 for (j = 0; j < 9; j++) {
//		 System.out.print(arraySquare[i][j] + " ");
//		 }
//		 System.out.println();
//		 }

		writePuzzle(arraySquare, output1);
	}

	private int[][] swapRow(int[][] array, int row1, int row2) {
		int temp = 0;
		for (int col = 0; col < 9; col++) {
			temp = array[row1][col];
			array[row1][col] = array[row2][col];
			array[row2][col] = temp;
		}
		return array;
	}

	public boolean checkRowCol(int[][] array) {
		for (int i = 0; i < 9; i++) {
			Vector<Integer> row = new Vector<Integer>();
			Vector<Integer> col = new Vector<Integer>();
			for (int j = 0; j < 9; j++) {
				if (!row.contains(array[i][j]))
					row.add(array[i][j]);
				if (!col.contains(array[j][i]))
					col.add(array[j][i]);
			}
			// System.out.println("row : " + row.toString());
			// System.out.println("col : " + col.toString());
			if (row.size() < 9 || col.size() < 9)
				return false;
		}
		return true;
	}

	public void writePuzzle(int[][] array, String output_name) {
		int i = 0;
		int j = 0;
		// int count = 0;
		try {
			fileOut = new FileWriter(output_name);
			// Do something here
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 9; j++)
					fileOut.write(array[i][j] + " ");
				fileOut.write('\n');
			}
			fileOut.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		SudokuGenerator sg = new SudokuGenerator();
		sg.generatingSudoku(args[0], Integer.parseInt(args[1]));
	}

}
