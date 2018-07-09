package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.Score;

/**
 * <code>Side</code> express Biim layout side bar.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Side extends Window {
	/** monolith score. */
	private Score score;

	/**
	 * generate side bar.
	 */
	public Side() {
		super(new Dimension(352, 358), 0);

		Window main = new Window();
		Window title = new Window(new Dimension(330, 60), 4);
		Window data = new Window(new Dimension(330, 280), 4);
		Window menu = new Window(new Dimension(310, 260));

		Text text = new Text();
		text.setText("デバッグメッセージ");
		title.add(text);

		setLayout(new BorderLayout());
		main.setLayout(new BorderLayout(10, 10));
		main.add(title, BorderLayout.NORTH);
		main.add(data, BorderLayout.CENTER);

		data.addWindow(menu, new Window(new Dimension(0, 10)), new Window(new Dimension(0, 10)),
				new Window(new Dimension(10, 0)), new Window(new Dimension(10, 0)));

		menu.setLayout(new GridLayout(6, 1));
		MonolithItem[] item = new MonolithItem[6];
		for (int i = 0; i < 6; i++)
			item[i] = new MonolithItem();
		item[0].setScore(MonolithItem.SCORE_LABEL);
		item[1].setScore(MonolithItem.HIGHSCORE_LABEL);
		item[3].setControll();
		item[5].setTime();
		for (int i = 0; i < 6; i++)
			menu.add(item[i]);

		add(main, BorderLayout.CENTER);
		add(new Window(new Dimension(12, 378)), BorderLayout.EAST);
		add(new Window(new Dimension(10, 378)), BorderLayout.WEST);
		add(new Window(new Dimension(0, 4)), BorderLayout.SOUTH);
		add(new Window(new Dimension(0, 4)), BorderLayout.NORTH);
	}

	/**
	 * load icon image to path.
	 *
	 * @param path
	 *            icon image file.
	 * @return icon image.
	 */
	public BufferedImage loadIcon(String path) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * load score data.
	 */
	public void loadScore() {
		setScore(Score.getScore(800));
	}

	/**
	 * get previous score.
	 *
	 * @return previous score.
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * set previous score.
	 *
	 * @param score
	 *            previous score.
	 */
	public void setScore(Score score) {
		this.score = score;
	}
}