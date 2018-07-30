package view;

import static monolith.MonolithConf.DEFAULT_FONT;
import static monolith.MonolithConf.DEFAULT_RANK_FONTSIZE;
import static monolith.MonolithConf.DEFAULT_RANK_HEIGHT;
import static monolith.MonolithConf.DEFAULT_RANK_WIDTH;
import static monolith.MonolithConf.DEFAULT_TIME_FONTSIZE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import model.Model;
import model.Score;

/**
 * <code>MonolithItem</code> express monolith sidebar item.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class MonolithItem extends Window implements WindowItem {
	/** temporary score information */
	final Score score = Score.A;
	/** default font information */
	Font font = Model.f.getFont(DEFAULT_FONT);

	/** temporary text information */
	Text index = new Text();

	/** default time format */
	private DateFormat format = new SimpleDateFormat(" mm:ss:SSS");

	/** temporary limited time */
	private int limited = 600000;

	/** temporary text information */
	Text text = new Text();

	/** default score label */
	public static String SCORE_LABEL = "Score    : ";

	/** default high score label */
	public static String HIGHSCORE_LABEL = "HighScore: ";

	public static final int DEFAULT_MONOLITHITEM = 4;
	public static final String DEFAULT_MONOLITHITEM_UNDO = "/undo.png";
	public static final String DEFAULT_MONOLITHITEM_REDO = "/redo.png";
	public static final String DEFAULT_MONOLITHITEM_PAUSE = "/pause.png";
	public static final String DEFAULT_MONOLITHITEM_HOME = "/home.png";
	public static final int DEFAULT_MAXRANK_NUM = 3;
	public static final String DEFAULT_MONOLITHITEM_CLOCK = "/clock.png";

	/** generate Window */
	public MonolithItem() {
		super();
	}

	/**
	 * generate resized Window.
	 *
	 * @param d
	 */
	public MonolithItem(Dimension d) {
		super(d);
	}

	/**
	 * load image icon.
	 *
	 * @param path
	 *            image file.
	 * @return image data.
	 */
	public BufferedImage loadIcon(String path) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception ex) {
			return null;
		}
	}

	public void setTitle(String text) {
		Text title = new Text();
		title.setText(text);
		this.add(title);
	}

	/**
	 * set control buttons. control buttons as follows.
	 * <ul>
	 * <li>undo: performed to reverse the action of an earlier action.</li>
	 * <li>redo: performed on a computer that does any undo function again.</li>
	 * <li>pause: temporarily suspend play of a game.</li>
	 * <li>home: return to title screen.</li>
	 * </ul>
	 */
	public void setControll() {
		JButton[] controllButton = new JButton[DEFAULT_MONOLITHITEM];

		controllButton[0] = new JButton(new ImageIcon(loadIcon(DEFAULT_MONOLITHITEM_UNDO)));
		controllButton[1] = new JButton(new ImageIcon(loadIcon(DEFAULT_MONOLITHITEM_REDO)));
		controllButton[2] = new JButton(new ImageIcon(loadIcon(DEFAULT_MONOLITHITEM_PAUSE)));
		controllButton[3] = new JButton(new ImageIcon(loadIcon(DEFAULT_MONOLITHITEM_HOME)));

		for (int i = 0; i < DEFAULT_MONOLITHITEM; i++) {
			controllButton[i].setBackground(Color.BLACK);
			LineBorder border = new LineBorder(Color.BLACK, 0, true);
			controllButton[i].setBorder(border);
			controllButton[i].setContentAreaFilled(false);
		}
		this.addWindow(controllButton);
	}

	/**
	 * set score label.
	 *
	 * @param text
	 *            score label
	 */
	public void setScore(String text) {
		Text index = new Text();
		Text point = new Text();
		Window rank = new Window(new Dimension(DEFAULT_RANK_HEIGHT, DEFAULT_RANK_WIDTH));

		index.setFont(font.deriveFont(Font.BOLD, DEFAULT_RANK_FONTSIZE));
		index.setText(text);
		index.setHorizontalAlignment(JLabel.LEFT);

		point.setFont(font.deriveFont(Font.BOLD, DEFAULT_RANK_FONTSIZE));
		point.setText(String.valueOf(score.getScore()));
		point.setHorizontalAlignment(JLabel.LEFT);

		rank.setLayout(new GridLayout(1, DEFAULT_MAXRANK_NUM));

		for (int i = 0; i < DEFAULT_MAXRANK_NUM; i++) {
			rank.add(new JLabel(new ImageIcon(score.getImg(i))));
		}

		add(index);
		add(point);
		add(rank);
	}

	/**
	 * set time label.
	 */
	public void setTime() {
		text.setFont(font.deriveFont(Font.BOLD, DEFAULT_TIME_FONTSIZE));
		text.setVerticalTextPosition(JLabel.CENTER);
		text.setText(format.format(limited));

		text.setIcon(new ImageIcon(loadIcon(DEFAULT_MONOLITHITEM_CLOCK)));

		Timer t = new Timer();
		t.schedule(new TimerLabelTask(), 0, 1);

		this.add(text);
	}

	/**
	 * timer controller. 1millisecond schedule execute.
	 */
	public void setTimer() {
		text.setText(format.format(--limited));
	}

	/**
	 * <code>TimerLabelTask</code>a task that is scheduled to run once or
	 * repeatedly.
	 *
	 * @author LeavaTail
	 */
	class TimerLabelTask extends TimerTask {

		@Override
		public void run() {
			if (limited > 0) {
				setTimer();
			} else {
				cancel();
				System.out.println(limited);
			}
		}
	}
}
