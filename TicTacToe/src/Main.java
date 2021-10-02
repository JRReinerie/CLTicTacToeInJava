import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	private static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	//Main Function
	public static void main(String[] args) {
		char gameBoard[][] = 
			{
			{' ', '|', ' ', '|', ' '},
			{'-', '+', '-', '+', '-'},
			{' ', '|', ' ', '|', ' '},
			{'-', '+', '-', '+', '-'},
			{' ', '|', ' ', '|', ' '},
			};
		
		drawGameBoard(gameBoard);
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter number 1-9");
			int playerPos = scanner.nextInt();

			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("position is taken! enter new position!");
				playerPos = scanner.nextInt();
			}
			
			getPosition(playerPos, gameBoard, "player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random random = new Random();
			int cpuPos = random.nextInt(9) + 1;
			
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				System.out.println("position is taken! enter new position!");
				cpuPos = random.nextInt(9) + 1;
			}
			
			getPosition(cpuPos, gameBoard, "cpu");
			drawGameBoard(gameBoard);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
		}
	}
	
	//Draw the gameBoard
	private static void drawGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char r : row) {
				System.out.print(r);
			}
			System.out.println();
		}
	}
	
	//get The position of the gameBoard
	private static void getPosition(int pos, char[][] gameBoard, String user) {
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")){
			 symbol = 'O';
			 cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}
	}
	
	private static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(7, 5, 3);
		List cross2 = Arrays.asList(1, 5, 9);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "You win!";
			}else if(cpuPositions.containsAll(l)) {
				return "You lose!";
			}else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "TIE game!";
			}
		}
		
		return "";
	}
}
