package view;

import static monolith.MonolithConf.COLUMN;
import static monolith.MonolithConf.DEFAULT_GAMEHEADER_HEIGHT;
import static monolith.MonolithConf.DEFAULT_GAMEHEADER_WIDTH;
import static monolith.MonolithConf.DEFAULT_GAME_HEIGHT;
import static monolith.MonolithConf.DEFAULT_GAME_WIDTH;
import static monolith.MonolithConf.ROW;

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
	/**
	 * generate game window.
	 */
	public Game() {
		Window main = new Window();

		TableHeader rowHeader = new TableHeader(new Dimension(DEFAULT_GAME_WIDTH, DEFAULT_GAMEHEADER_HEIGHT));
		TableHeader columnHeader = new TableHeader(new Dimension(DEFAULT_GAMEHEADER_WIDTH, DEFAULT_GAME_HEIGHT));
		rowHeader.rowHeader(ROW + 1);
		columnHeader.columnHeader(COLUMN);

		main.setLayout(new GridLayout(COLUMN, ROW));
		setPreferredSize(new Dimension(DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT));

		setLayout(new BorderLayout());

		for (int c = 0; c < COLUMN; c++) {
			for (int r = 0; r < ROW; r++) {
				JLabel tmp = new JLabel("" + c + " " + r);
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