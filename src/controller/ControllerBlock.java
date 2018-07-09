package controller;

import model.Piece;

/**
 * The <code>ControllerBlock</code> class handle requests from user. (<b>C</b>
 * of the <b>MVC</b> pattern).
 *
 * @author LeavaTail
 */
public class ControllerBlock {
	/** Game board. */
	private Piece[][] game;

	/** load game board. */
	public void loadBoard() {
		// TODO load game[][]
	}

	/** load game board. */
	public ControllerBlock() {
		loadBoard();
		System.out.println(game);
	}
}