package model;

import controller.ControllerFont;
import controller.ControllerIcon;

/**
 * The <code>Model</code> class responsible for managing the data of the
 * application. It receives user input from the controller. (<b>M</b> of the
 * <b>MVC</b> pattern)
 *
 * @author LeavaTail
 */
public class Model {
	/** default font files. */
	static public final ControllerFont f = new ControllerFont();

	/** default image files. */
	static public final ControllerIcon i = new ControllerIcon();

	/** current score. */
	private int score;

	/** current high score. */
	private int highscore;

	/** current time. */
	private int time;

	/** current game board. */
	private Integer[][] board;

	/**
	 * get current score.
	 *
	 * @return current score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * set Specified score.
	 *
	 * @param score
	 *            Specified score.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * get current high score.
	 *
	 * @return current high score.
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * set Specified high score.
	 *
	 * @param highscore
	 *            Specified high score.
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	/**
	 * get current time.
	 *
	 * @return current time.
	 */
	public int getTime() {
		return time;
	}

	/**
	 * set Specified time.
	 *
	 * @param time
	 *            Specified time.
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * get current play board.
	 *
	 * @return game board.
	 */
	public Integer[][] getBoard() {
		return board;
	}

	/**
	 * set Specified board.
	 *
	 * @param board
	 *            Specified board.
	 */
	public void setBoard(Integer[][] board) {
		this.board = board;
	}
}