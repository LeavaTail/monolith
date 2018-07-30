/**
 *
 */
package model;

import static monolith.MonolithConf.SCORE_A;
import static monolith.MonolithConf.SCORE_AA;
import static monolith.MonolithConf.SCORE_AAA;
import static monolith.MonolithConf.SCORE_B;
import static monolith.MonolithConf.SCORE_BB;
import static monolith.MonolithConf.SCORE_BBB;
import static monolith.MonolithConf.SCORE_C;
import static monolith.MonolithConf.SCORE_CC;
import static monolith.MonolithConf.SCORE_CCC;
import static monolith.MonolithConf.SCORE_S;

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
	C(SCORE_C, "/c.png", 1),
	CC(SCORE_CC, "/c.png", 2),
	CCC(SCORE_CCC, "/c.png", 3),
	B(SCORE_B, "/b.png", 1),
	BB(SCORE_BB, "/b.png", 2),
	BBB(SCORE_BBB, "/b.png",3),
	A(SCORE_A, "/a.png", 1),
	AA(SCORE_AA, "/a.png", 2),
	AAA(SCORE_AAA, "/a.png", 3),
	S(SCORE_S, "/s.png", 1);

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
			this.img = new BufferedImage(0, 0, BufferedImage.TYPE_INT_BGR);
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
			return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
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
