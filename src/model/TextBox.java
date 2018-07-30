/**
 *
 */
package model;

import static monolith.MonolithConf.DEFAULT_TEXTBOX_HEIGHT;
import static monolith.MonolithConf.DEFAULT_TEXTBOX_MARGIN;
import static monolith.MonolithConf.DEFAULT_TEXTBOX_WIDTH;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import view.Text;
import view.Window;
/**
 * <code>TextBox</code> express text box.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class TextBox extends Window {
	/** upper text box */
	private Text text1;
	/** lower text box */
	private Text text2;

	/**
	 * generate empty text box.
	 */
	public TextBox() {

	}

	/**
	 * generate resized text box.
	 *
	 * @param d
	 *            window size
	 */
	public TextBox(Dimension d) {
		super(d, 0);
		Window box = new Window(DEFAULT_TEXTBOX_MARGIN);
		Window textBar = new Window(new Dimension(DEFAULT_TEXTBOX_WIDTH, DEFAULT_TEXTBOX_HEIGHT));

		box.setLayout(new BorderLayout());
		setLayout(new BorderLayout());

		box.add(textBar, BorderLayout.CENTER);
		add(box, BorderLayout.CENTER);

		textBar.setLayout(new GridLayout(2, 1));
		text1 = new Text();
		text2 = new Text();
		text1.setText("これはデバッグメッセージです。");
		text2.setText("This is debug message.");
		textBar.add(text1);
		textBar.add(text2);

		Border border = new CompoundBorder(new EmptyBorder(10, 10, 10, 10), new EtchedBorder(EtchedBorder.RAISED));
		setBorder(border);
		Border border2 = new CompoundBorder(new EmptyBorder(4, 4, 4, 4), new EtchedBorder(EtchedBorder.RAISED));
		textBar.setBorder(border2);
	}

	/**
	 *
	 * @param text
	 *            setting message
	 * @return setting message length
	 * @deprecated do not implement method
	 */
	@Deprecated
	public int setText(String text) {
		int len = text.length();

		return len;
	}
}
