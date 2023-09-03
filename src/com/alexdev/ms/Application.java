package com.alexdev.ms;

import com.alexdev.ms.model.Board;
import com.alexdev.ms.vision.BoardConsole;

public class Application {

	public static void main(String[] args) {
		Board board = new Board(5, 5, 5);
		new BoardConsole(board);
	}
}
