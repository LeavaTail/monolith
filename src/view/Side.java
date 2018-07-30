package view;

import static monolith.MonolithConf.DEFAULT_SIDEDATA_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDEDATA_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDEEAST_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDEEAST_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDEMAIN_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDEMAIN_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDEMENU_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDEMENU_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDENORTH_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDENORTH_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDESOUTH_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDESOUTH_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDETITLE_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDETITLE_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDEWEST_HEIGHT;
import static monolith.MonolithConf.DEFAULT_SIDEWEST_WIDTH;
import static monolith.MonolithConf.DEFAULT_SIDE_MARGIN;
import static monolith.MonolithConf.DEFAULT_SIZEMAIN_MARGIN;

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
	public static final int DEFAULT_SIDEMENU = 6;
	public static final int DEFAULT_SIDEMENU_SCORE = 0;
	public static final int DEFAULT_SIDEMENU_HIGHSCORE = 1;
	public static final int DEFAULT_SIDEMENU_CONTROLL = 3;
	public static final int DEFAULT_SIDEMENU_TIME = 5;

	/**
	 * generate side bar.
	 */
	public Side() {
		super(new Dimension(DEFAULT_SIDEMAIN_WIDTH, DEFAULT_SIDEMAIN_HEIGHT), 0);

		Window main = new Window();
		Window title = new Window(new Dimension(DEFAULT_SIDETITLE_WIDTH, DEFAULT_SIDETITLE_HEIGHT), DEFAULT_SIDE_MARGIN);
		Window data = new Window(new Dimension(DEFAULT_SIDEDATA_WIDTH, DEFAULT_SIDEDATA_HEIGHT), DEFAULT_SIDE_MARGIN);
		Window menu = new Window(new Dimension(DEFAULT_SIDEMENU_WIDTH, DEFAULT_SIDEMENU_HEIGHT));

		Text text = new Text();
		text.setText("デバッグメッセージ");
		title.add(text);

		setLayout(new BorderLayout());
		main.setLayout(new BorderLayout(DEFAULT_SIZEMAIN_MARGIN, DEFAULT_SIZEMAIN_MARGIN));
		main.add(title, BorderLayout.NORTH);
		main.add(data, BorderLayout.CENTER);

		data.addWindow(menu, new Window(new Dimension(0, DEFAULT_SIZEMAIN_MARGIN)), new Window(new Dimension(0, DEFAULT_SIZEMAIN_MARGIN)),
				new Window(new Dimension(DEFAULT_SIZEMAIN_MARGIN, 0)), new Window(new Dimension(DEFAULT_SIZEMAIN_MARGIN, 0)));

		menu.setLayout(new GridLayout(DEFAULT_SIDEMENU, 1));
		MonolithItem[] item = new MonolithItem[DEFAULT_SIDEMENU];
		for (int i = 0; i < DEFAULT_SIDEMENU; i++)
			item[i] = new MonolithItem();
		item[DEFAULT_SIDEMENU_SCORE].setScore(MonolithItem.SCORE_LABEL);
		item[DEFAULT_SIDEMENU_HIGHSCORE].setScore(MonolithItem.HIGHSCORE_LABEL);
		item[DEFAULT_SIDEMENU_CONTROLL].setControll();
		item[DEFAULT_SIDEMENU_TIME].setTime();
		for (int i = 0; i < DEFAULT_SIDEMENU; i++)
			menu.add(item[i]);

		add(main, BorderLayout.CENTER);
		add(new Window(new Dimension(DEFAULT_SIDEEAST_HEIGHT, DEFAULT_SIDEEAST_WIDTH)), BorderLayout.EAST);
		add(new Window(new Dimension(DEFAULT_SIDEWEST_HEIGHT, DEFAULT_SIDEWEST_WIDTH)), BorderLayout.WEST);
		add(new Window(new Dimension(DEFAULT_SIDESOUTH_HEIGHT, DEFAULT_SIDESOUTH_WIDTH)), BorderLayout.SOUTH);
		add(new Window(new Dimension(DEFAULT_SIDENORTH_HEIGHT, DEFAULT_SIDENORTH_WIDTH)), BorderLayout.NORTH);
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