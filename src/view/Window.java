package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * <code>Window</code> specialize in Black Board <code>JPanel</code>.
 *
 * @see javax.swing.JPanel
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Window extends JPanel {
	/**
	 * Creates a Very tiny black board, and no boarder.
	 */
	public Window() {
		this(0);
	}

	/**
	 * Creates a Very tiny black board, and specified size boarder.
	 *
	 * @param thickness
	 *            boarder size by the Window
	 */
	public Window(int thickness) {
		this(new Dimension(0, 0), thickness);
	}

	/**
	 * Creates a specified size black board, and no boarder.
	 *
	 * @param d
	 *            board size by the Window
	 */
	public Window(Dimension d) {
		this(d, 0);
	}

	/**
	 * Creates a specified size black board, and no boarder.
	 *
	 * @param d
	 *            board size by the Window
	 * @param thickness
	 *            boarder size by the Window
	 */
	public Window(Dimension d, int thickness) {
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		this.setPreferredSize(d);
		LineBorder border = new LineBorder(Color.WHITE, thickness, true);
		setOpaque(true);
		setBorder(border);
	}

	/**
	 * add components as BorderLayout.
	 *
	 * @param c
	 *            locate to center
	 * @param n
	 *            locate to north
	 * @param s
	 *            locate to south
	 * @param w
	 *            locate to west
	 * @param e
	 *            locate to east
	 */
	public void addWindow(JComponent c, JComponent n, JComponent s, JComponent w, JComponent e) {
		this.setLayout(new BorderLayout());
		add(c, BorderLayout.CENTER);
		add(n, BorderLayout.NORTH);
		add(s, BorderLayout.SOUTH);
		add(w, BorderLayout.WEST);
		add(e, BorderLayout.EAST);
	}

	/**
	 * add components as GridLayout.
	 *
	 * @param w
	 *            locate to window arrays.
	 */
	public void addWindow(JComponent[] w) {
		int num = w.length;
		this.setLayout(new GridLayout(1, num));
		for (int i = 0; i < num; i++)
			add(w[i]);
	}

	/**
	 * create "Biim Layout" in Window. "Biim layout" has Mainly the following
	 * features.
	 * <ul>
	 * <li>Upper left: Display Game Screen
	 * <li>Bottom: Display main message
	 * <li>Right: Display detailed data
	 * </ul>
	 *
	 * @see <a href=
	 *      "http://dic.nicovideo.jp/a/biim%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0">Biim
	 *      System</a>
	 * @param center
	 *            Upper left: Display Game Screen <code>Window</code>
	 * @param east
	 *            Right: Display detailed data <code>Window</code>
	 * @param south
	 *            Bottom: Display main message <code>Window</code>
	 */
	public void biimLayout(Window center, Window east, Window south) {
		this.setLayout(new BorderLayout());
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		add(east, BorderLayout.EAST);

	}
}