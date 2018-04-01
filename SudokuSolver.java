import java.io.*;
import java.util.*;
import java.text.*;

public class SudokuSolver {
	private Writer fileOut;
	private ArrayList<Block> boardS;
	private final int BlockSize = 3;
	private final int nBlock = 9;
	private final int nBoard = 81;

	// tao mang chua sudoku nhan vao
	private int array[] = new int[nBoard];

	public boolean readSudoku(String filename) {
		try {
			FileReader reader = new FileReader(filename);
			Scanner fileIn = new Scanner(reader);
			int i = 0;
			while (fileIn.hasNextInt()) {
				int value = fileIn.nextInt();
				array[i] = value;
				// System.out.print(array[i]);
				i++;
			}
			fileIn.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	// Ham xac dinh block cua square
	public int idBlock(int idSquare) {
		return ((idSquare / nBlock) / BlockSize) * BlockSize + (idSquare % nBlock) / BlockSize;
	}

	// Ham kiem tra square theo hang
	public boolean checkRow(int idSquare) {
		for (int i = 0; i < nBoard; i++) {
			if (i == idSquare)
				continue;
			if (i / nBlock == idSquare / nBlock && array[i] == array[idSquare])
				return false;
		}
		return true;
	}

	// Ham kiem tra square theo cot
	public boolean checkCol(int idSquare) {
		for (int i = 0; i < nBoard; i++) {
			if (i == idSquare)
				continue;
			if (i % nBlock == idSquare % nBlock && array[i] == array[idSquare])
				return false;
		}
		return true;
	}

	public boolean checkAll(int level) {
		for (int i = 0; i < nBoard; i++) {
			if (idBlock(i) == level && (checkRow(i) == false || checkCol(i) == false))
				return false;
		}
		return true;
	}

	int ccc = 0;

	public void SudokuSolver(String filename) {
		// SudokuGenerator.writePuzzle(boardS, filename);
		try {
			fileOut = new FileWriter(filename);
			// Do something here
			for (int i = 0; i < nBoard; i++) {
				fileOut.write(array[i] + " ");
				if ((i + 1) % 9 == 0)
					fileOut.write('\n');
			}

			fileOut.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private Vector<Integer> swap(int i, int j, Vector<Integer> missingNumber) {
		// write code here
		int temp = missingNumber.get(i);
		missingNumber.set(i, missingNumber.get(j));
		missingNumber.set(j, temp);
		// System.out.println(missingNumber.toString());
		return missingNumber;
	}

	// Ham tao hoan vi
	private Vector<Vector<Integer>> permLexicalgraphic(Vector<Integer> missingNumber) throws IOException {
		// Write code here
		int n = missingNumber.size();
		Vector<Vector<Integer>> permute = new Vector<Vector<Integer>>();
		while (true) {
			// Step 1
			// System.out.println(missingNumber.toString());
			Vector<Integer> tempVector = new Vector<Integer>();
			for (int i = 0; i < missingNumber.size(); i++) {
				tempVector.add(missingNumber.elementAt(i));
			}
			permute.add(tempVector);
			// Step 2
			int j;
			for (j = n - 2; j >= 0; j--)
				if (missingNumber.elementAt(j) < missingNumber.elementAt(j + 1))
					break;
			if (j == -1)
				return permute;
			// Step 3
			int l = n - 1;
			while (missingNumber.elementAt(j) > missingNumber.elementAt(l))
				l--;
			missingNumber = swap(l, j, missingNumber);
			// Step 4
			int k;
			for (l = n - 1, k = j + 1; l > k; l--, k++) {
				missingNumber = swap(l, k, missingNumber);
			}
		}

	}

	// Ham dua hoan vi vao sudoku

	public void insertPermute(int iPermute, Vector<Integer> posZeros, Vector<Vector<Integer>> permute) {
		for (int i = 0; i < posZeros.size(); i++) {
			array[posZeros.get(i)] = permute.get(iPermute).get(i);
		}
	}

	// Ham reset ve sudoku ban dau

	public void reset(Vector<Integer> posZeros) {
		for (int i = 0; i < posZeros.size(); i++) {
			array[posZeros.get(i)] = 0;
		}
	}

	public boolean solve(int level) throws IOException {
		// Vector vi tri cua cac so 0 trong Block
		Vector<Integer> posZeros;
		// Vector Cac gia tri da co trong Block
		Vector<Integer> nonZeros;
		// Vector Cac gia tri con thieu trong Block
		Vector<Integer> missingNumber;
		// Vector chua cac truong hop hoan vi cua cac gia tri con thieu trong
		// Block
		Vector<Vector<Integer>> permute;

		if (level == nBlock)
			return true;
		posZeros = new Vector<Integer>();
		nonZeros = new Vector<Integer>();
		missingNumber = new Vector<Integer>();
		int i = 0;
		for (i = 0; i < nBoard; i++) {
			if (idBlock(i) == level) {
				if (array[i] != 0)
					nonZeros.add(array[i]);
				else
					posZeros.add(i);
			}
		}
		// System.out.println(level);
		// System.out.println(nonZeros.toString());

		for (i = 1; i <= 9; i++) {
			if (nonZeros.contains(i))
				continue;
			missingNumber.add(i);
		}

		// System.out.println(missingNumber.toString());

		permute = new Vector<Vector<Integer>>();
		permute = permLexicalgraphic(missingNumber);
		// System.out.println("size: " + permute.size());

		int count = 0;

		while (true) {
			if (count == permute.size()) {
				reset(posZeros);
				return false;
			}
			insertPermute(count, posZeros, permute);
			// System.out.print("count = " + count + '\n');
			// for (i = 0; i < nBoard; i++) {
			// System.out.print(array[i] + " ");
			// if ((i + 1) % 9 == 0)
			// System.out.print('\n');
			// }
			// System.out.print('\n');
			count++;
			// insertPermute(count);
			// count++;
			if (nBlock == level) {
				break;
			}
			if (!checkAll(level)) {
				// System.out.println("WRONG!!!");
				continue;
			}
			if (solve(level + 1))
				break;
		}
		return true;
	}

	public static void main(String args[]) throws IOException {
		SudokuSolver sol = new SudokuSolver();
		sol.readSudoku(args[0]);
		sol.solve(0);
		// for (int i = 0; i < sol.nBoard; i++) {
		// System.out.print(sol.array[i] + " ");
		// if ((i + 1) % 9 == 0)
		// System.out.print('\n');
		// }
		sol.SudokuSolver(args[1]);
	}
}
