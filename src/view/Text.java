package view;

import static view.View.DEFAULT_FONT;

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
		this.setFont(font.deriveFont(Font.PLAIN, 32f));
		this.setForeground(Color.WHITE);
		this.setBackground(Color.BLACK);
	}
}