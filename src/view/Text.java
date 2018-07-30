package view;

import static monolith.MonolithConf.DEFAULT_FONT;
import static monolith.MonolithConf.DEFAULT_FONTSIZE;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import model.Model;

/**
 * <code>Text</code> express monolith label.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Text extends JLabel {
	public Text() {
		Font font = Model.f.getFont(DEFAULT_FONT);
		this.setFont(font.deriveFont(Font.PLAIN, DEFAULT_FONTSIZE));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
	}
}