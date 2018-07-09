package view;

import static view.View.DEFAULT_CLICK_ICON;
import static view.View.DEFAULT_ICON;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;

import model.Icon;
import model.Model;
import model.TextBox;

/**
 * <code>Status</code> specialize in Black Board <code>JPanel</code>.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Status extends Window {
	/**
	 * character icon image.
	 */
	private Icon icon;

	/**
	 * set status bar.
	 */
	public Status() {
		super(0);

		TextBox text = new TextBox(new Dimension(794, 100));
		icon = new Icon(Model.i.getIcon(DEFAULT_ICON));
		icon.setPreferredSize(new Dimension(150, 120));
		icon.setPressedIcon(new ImageIcon(Icon.getScaledImage(Model.i.getIcon(DEFAULT_CLICK_ICON), 150, 120)));

		setPreferredSize(new Dimension(944, 120));
		setLayout(new BorderLayout());

		add(text, BorderLayout.CENTER);
		add(icon, BorderLayout.WEST);
	}

	/**
	 * set status bar resize window.
	 *
	 * @param d
	 *            dimension.
	 */
	public Status(Dimension d) {
		this();
		setPreferredSize(d);
	}

	/**
	 * set status text.
	 *
	 * @param text
	 *            text message.
	 */
	public void setStatus(String[] text) {

	}
}
