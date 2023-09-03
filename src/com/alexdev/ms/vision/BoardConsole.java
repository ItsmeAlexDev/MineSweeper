package com.alexdev.ms.vision;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static java.util.Arrays.stream;

import java.util.Iterator;
import java.util.Scanner;

import com.alexdev.ms.exception.ExplosionException;
import com.alexdev.ms.exception.ExitException;
import com.alexdev.ms.model.Board;

public class BoardConsole {

	private Board board;
	private Scanner input = new Scanner(in);
	
	public BoardConsole(Board board) {
		this.board = board;

		executeGame();
	}
	
	private void executeGame() {
		try {
			boolean dontStop = true;
			
			while (dontStop) {
				gameLoop();
				
				System.out.println("new game? (Y/n)");
				String answerInput = input.nextLine();
				
				if("n".equalsIgnoreCase(answerInput))
					dontStop = false;
				else
					board.restartGame();
			}
		} catch(ExitException e) {
			System.out.println("GAME OVER!");
		} finally {
			input.close();
		}
	}

	private void gameLoop() {
		try {
			
			while(!board.goalAchieved()) {
				System.out.println(board);
				
				String input = getInput("Type (x, y): ");
				
				Iterator<Integer> xy = stream(input.split(","))
					.map(e -> parseInt(e.trim()))
					.iterator();
				
				input = getInput("1 - Open / 2 - Mark/Mark Off");
				
				if ("1".equals(input))
					board.open(xy.next(), xy.next());
				if ("2".equals(input))
					board.toggleMarkup(xy.next(), xy.next());
			}

			System.out.println(board);
			System.out.println("You won, gg :D");
		} catch (ExplosionException e) {
			System.out.println(board);
			System.out.println("You lost :/");
		}
	}
	
	private String getInput(String text) {
		System.out.println(text);
		String input = this.input.nextLine();
		
		if("exit".equalsIgnoreCase(input))
			throw new ExitException();
		
		return input;
	}
}
