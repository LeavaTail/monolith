/**
 *
 */
package model;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * <code>Score</code> express monolith score label.
 *
 * @author LeavaTail
 */
public enum Score {
	/**
	 * monolith Score label variation.
	 */
	C(0, "/c.png", 1), CC(900, "/c.png", 2), CCC(1400, "/c.png", 3), B(1900, "/b.png", 1), BB(2400, "/b.png", 2), BBB(
			2900, "/b.png",
			3), A(3400, "/a.png", 1), AA(3900, "/a.png", 2), AAA(4400, "/a.png", 3), S(4900, "/s.png", 1);

	/** monolith score. */
	private int score;

	/** score image. */
	private BufferedImage img;

	/** image repeated count. */
	private int num;

	/**
	 * load score data.
	 *
	 * @param score
	 *            number until arrive this score.
	 * @param path
	 *            score image path.
	 * @param num
	 *            score image repeated count.
	 */
	private Score(int score, String path, int num) {
		this.score = score;
		this.num = num;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
			this.img = new BufferedImage(Piece.DEFAULT_SIZE, Piece.DEFAULT_SIZE, BufferedImage.TYPE_INT_BGR);
		}
	}

	/**
	 * get correspond to <code>score</code>.
	 *
	 * @param score
	 *            current score.
	 * @return score label
	 *
	 */
	static public Score getScore(int score) {
		if (score <= CC.score)
			return C;
		if (score <= CCC.score)
			return CC;
		if (score <= B.score)
			return CCC;
		if (score <= BB.score)
			return B;
		if (score <= BBB.score)
			return BB;
		if (score <= A.score)
			return BBB;
		if (score <= AA.score)
			return A;
		if (score <= AAA.score)
			return AA;
		if (score <= S.score)
			return AAA;
		return S;
	}

	/**
	 * get score label.
	 *
	 * @return score label.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * get score label image.
	 *
	 * @return score label image.
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * get score label image (specified order).
	 *
	 * @param i
	 * @return
	 */
	public BufferedImage getImg(int i) {
		if (this.num <= i + 1) {
			return img;
		} else {
			return new BufferedImage(30, 40, BufferedImage.TYPE_INT_RGB);
		}
	}

	/**
	 * get score number.
	 *
	 * @return score number.
	 */
	public int getNum() {
		return num;
	}
}
