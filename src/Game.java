/* direction is broken down into horizontal and vertical components with
 * 1 being right and -1 being left for the horizontal and
 * 1 being up and -1 being down for the vertical
 */

public class Game{
	public int[][] board;
	public int score;
	public static final int boardSize = 4;
	
	public Game(){
		board = new int[boardSize+2][boardSize+2];
		board = GameUtil.setBoard(board);
		
	}
	
	public Game(Game game) {
		this.board = game.getBoard();
		this.score = game.getScore();		
	}
	
	public boolean checkGameOver() {
		Game test;
		
		test = new Game(this);
		test.swipeUp();
		if(!GameUtil.compareBoards(test.board, board)) return false;
		
		test = new Game(this);
		test.swipeRight();
		if(!GameUtil.compareBoards(test.board, board)) return false;
		
		test = new Game(this);
		test.swipeLeft();
		if(!GameUtil.compareBoards(test.board, board)) return false;
		
		test = new Game(this);
		test.swipeDown();
		if(!GameUtil.compareBoards(test.board, board)) return false;
		
		return true;
	}
	
	public void fillAfterMove() {
		int zeroCount = GameUtil.countZeros(board);
		
		int fill = 2;
		if(Math.random() < .25) fill = 4;
		int target = (int)(Math.random()*zeroCount);
		zeroCount = 0;
		
		outerLoop:
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == 0) {
				
					if(zeroCount == target) {
						board[i][j] = fill;
						break outerLoop;
					}
					
					zeroCount++;
				}	
			}
		}
	}
	
	public void swipeRight() {
		for(int i = 1; i < board.length - 1; i++) {
			replaceRow(i, handleLine(board[i]));
		}	
	}
	
	public void swipeLeft() {
		for(int i = 1; i < board.length - 1; i++) {
			replaceRow(i, flip(handleLine(flip(board[i]))));
		}	
	}
	
	public void swipeDown() {
		for(int i = 1; i < board.length - 1; i++) {
			replaceCol(i, handleLine(GameUtil.makeCol(board, i)));
		}	
	}
	
	public void swipeUp() {
		for(int i = 1; i < board.length - 1; i++) {
			replaceCol(i, flip(handleLine(flip(GameUtil.makeCol(board, i)))));
		}	
	}
	
	public int[] flip(int[] nums) {
		int[] res = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			res[i] = nums[nums.length - 1 - i];
		}
		return res;
	}
	
	public int[] handleLine(int[] in1) {
		for(int i = in1.length - 2; i > 0; i--) {
			if(in1[i] != 0) in1 = handleBlock(in1, i);
		}
		return in1;
	}
	
	public int[] handleBlock(int[] in2, int index) {
		if(in2[index+1] == 0) return handleBlock(push(in2, index),index+1);
		else return handleCollide(in2, index, index+1);
	}
	
	public int[] push(int[] in, int index) {
		in[index+1] = in[index];
		in[index] = 0;
		return in;
	}
	
	//bat is used to represent the index of the moving block/num. ball is the index of the stationary one being hit
	public int[] handleCollide(int[] in3, int bat, int ball) {
		if(in3[bat] != in3[ball]) return in3;
		else {
			in3[ball] *= 2;
			score += in3[ball];
			in3[bat] = 0;
		}
		return in3;		
	}
	
	public void replaceRow(int index, int[] replacement) {
		for(int i = 0; i < replacement.length; i++) {
			board[index][i] = replacement[i];
		}
	}
	
	public void replaceCol(int index, int[] replacement) {
		for(int i = 0; i < replacement.length; i++) {
			board[i][index] = replacement[i];
		}
	}
	
	public int[][] getBoard(){
		int[][] res = new int[board.length][board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				res[i][j] = board[i][j];
			}
		}
		return res;
	}
	
	public int getScore() {
		return score;
	}
}
