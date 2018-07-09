package model;

import static model.Alphabet.upper;
import static view.View.DEFAULT_FONT;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import view.Text;
import view.Window;

/**
 * <code>TableHeader</code> experss game table header.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class TableHeader extends Window {
	/** default font data */
	Font font = Model.f.getFont(DEFAULT_FONT);

	/** generate window to table header */
	public TableHeader() {
		super();
	}

	/** generate window to table header(resize window) */
	public TableHeader(Dimension d) {
		super(d);
	}

	/**
	 * specified column number. generated window
	 *
	 * @param column
	 *            column number
	 */
	public void columnHeader(int column) {
		setLayout(new GridLayout(column, 1));
		for (int c = 1; c <= column; c++) {
			Text index = new Text();
			index.setFont(font.deriveFont(Font.BOLD, 16f));
			index.setText(c + "");
			index.setPreferredSize(new Dimension(16, 32));
			index.setHorizontalAlignment(JLabel.RIGHT);
			this.add(index);
		}
	}

	/**
	 * specified row number. generated window
	 *
	 * @param row
	 *            row number
	 */
	public void rowHeader(int row) {
		setLayout(new GridLayout(1, row));
		for (int r = 0; r < row; r++) {
			Text index = new Text();
			index.setText(" " + upper(r - 1));
			index.setFont(font.deriveFont(Font.BOLD, 16f));
			index.setPreferredSize(new Dimension(32, 32));
			this.add(index);
		}
	}
}
