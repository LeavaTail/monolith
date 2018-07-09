package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import model.TableHeader;

/**
 * <code>Game</code> specialize in Black Board <code>JPanel</code>.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Game extends Window {
	/** number of row */
	public static final int ROW = 18;
	/** number of column */
	public static final int COLUMN = 11; // 列

	/**
	 * generate game window.
	 */
	public Game() {
		Window main = new Window();

		TableHeader rowHeader = new TableHeader(new Dimension(592, 16));
		TableHeader columnHeader = new TableHeader(new Dimension(32, 368));
		rowHeader.rowHeader(ROW + 1);
		columnHeader.columnHeader(COLUMN);

		main.setLayout(new GridLayout(COLUMN, ROW));
		setPreferredSize(new Dimension(592, 368));

		setLayout(new BorderLayout());

		for (int c = 0; c < COLUMN; c++) {
			for (int r = 0; r < ROW; r++) {
				JLabel tmp = new JLabel("" + c + " " + r);
				// tmp.setSize(new Dimension(32, 32));
				tmp.setPreferredSize(new Dimension(32, 32));

				tmp.setForeground(Color.BLACK);
				tmp.setBackground(Color.WHITE);
				tmp.setOpaque(true);
				BevelBorder border = new BevelBorder(BevelBorder.RAISED);
				tmp.setBorder(border);
				main.add(tmp);
			}
		}

		add(main, BorderLayout.CENTER);
		add(rowHeader, BorderLayout.NORTH);
		add(columnHeader, BorderLayout.WEST);
	}
}