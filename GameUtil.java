package finishedMaybeDebuggedManual;

public class GameUtil{
	
	public static final int NUM_SPACES = 4;
	
	public static int encode(int decoded) {
		return (int)Math.pow(2, decoded);
	}
	
	public static int decode(int encoded) {
		return (int)(Math.log(encoded)/Math.log(2));
	}
	
	public static int countZeros(int[][] b) {
		int zeroCount = 0;
		for(int[] row: b) {
			for(int num: row) {
				if(num == 0) zeroCount++;
			}
		}
		return zeroCount;
	}
	
	//dir indicates the direction that the array progresses in relation to the board. 1 is top to bottom, -1 is bottom to top
	public static int[] makeCol(int[][] b, int index)
	{
		int[] col = new int[Game.boardSize+2];
		for(int i = 0; i < col.length; i++) {
			col[i] = b[i][index];
		}
		return col;
	}
	
	public static int[][] setBoard(int[][] b){
		for(int i = 0; i< b.length;i++) {
			for(int j = 0; j < b[0].length; j++) {
				if(i == 0 || 
				   i == b.length - 1 || 
				   j == 0 || 
				   j == b[0].length - 1) {
						b[i][j] = -1;
				}
			}
		}
		return b;
	}
	
	public static void printReal(int[][] b){
		for(int i = 0; i< b.length;i++) {
			printLine(b[i]);
			System.out.println("");
			System.out.println("");
		}
	}
	
	public static void printGame(int[][] b){
		for(int i = 1; i< b.length-1;i++) {
			for(int j = 1; j < b[0].length-1; j++) {
				System.out.print(" " + b[i][j]);
			}
			System.out.println("");
		}
	}
	
	public static void printLine(int[] a) {
		if(a.length > 0) {
			System.out.print(a[0]);
			for(int i = 1; i < a.length; i++) {
				int digits = countDigits(a[i-1]);
				for(int j = NUM_SPACES; j >= digits; j--) {
					System.out.print(" ");
				}
				System.out.print(a[i]);
			}
		}
	}
	
	public static int countDigits(int n) {
		if(n == 0) return 1;
		else if(n == -1) return 2;
		else {
			int sum = 0;
			while (n>0) {
				sum++;
				n /= 10;
			}
			return sum;
		}
	}
	
	public static boolean compareBoards(int[][] init, int[][] board) {
		for(int i = 0; i < init.length; i++) {
			for(int j = 0; j < init.length; j++) {
				if(init[i][j] != board[i][j]) return false;
			}
		}
		
		return true;
	}
}
