package finishedMaybeDebuggedManual;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Game g = new Game();
		Scanner kb = new Scanner(System.in);
		boolean running = true;
		String move = "";
		g.fillAfterMove();
		int moveCount = 0;
		int loopCount = 0;
		
		gameloop:
		while(running) {
			GameUtil.printReal(g.board);
			int[][] initial = g.getBoard();
			System.out.println("Score:" + g.score);
			loopCount++;
			System.out.println("loopCount: " + loopCount);
			
//			int phase = loopCount % 4;
//			if(phase == 0) move = "a";
//			else if(phase == 1) move = "s";
//			else if(phase == 2) move = "d";
//			else if(phase == 3) move = "w";		

			System.out.println("Move? (w is up, a is left , s is down , d is right). \"stop\" to close the game.");
			move = kb.nextLine();
//			
			switch(move) {
				case "a": 
					g.swipeLeft();
					moveCount++;
					break;
				case "d":
					g.swipeRight();
					moveCount++;
					break;
				case "w":
					g.swipeUp();
					moveCount++;
					break;
				case "s":
					g.swipeDown();
					moveCount++;
					break;
					
				case "stop":
					System.out.print("Thank you for playing!");
					break gameloop;
				default:
					System.out.println("Invalid input, please try again.");
					continue gameloop;
			}
			if(!GameUtil.compareBoards(initial,g.board)) g.fillAfterMove();
			if(g.checkGameOver()) {
				GameUtil.printReal(g.board);
				System.out.print("GAME OVER! Thank you for playing, your score was "+ g.score);
				break gameloop;
			}
		}
		kb.close();
	}
}
