package view;

import static monolith.MonolithConf.DEFAULT_CLICK_ICON;
import static monolith.MonolithConf.DEFAULT_ICON;
import static monolith.MonolithConf.DEFAULT_ICON_HEIGHT;
import static monolith.MonolithConf.DEFAULT_ICON_WIDTH;
import static monolith.MonolithConf.DEFAULT_STATUSBAR_HEIGHT;
import static monolith.MonolithConf.DEFAULT_STATUSBAR_WIDTH;
import static monolith.MonolithConf.DEFAULT_STATUSTEXT_HEIGHT;
import static monolith.MonolithConf.DEFAULT_STATUSTEXT_WIDTH;

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

		TextBox text = new TextBox(new Dimension(DEFAULT_STATUSTEXT_WIDTH, DEFAULT_STATUSTEXT_HEIGHT));
		icon = new Icon(Model.i.getIcon(DEFAULT_ICON));
		icon.setPreferredSize(new Dimension(DEFAULT_ICON_HEIGHT, DEFAULT_ICON_WIDTH ));
		icon.setPressedIcon(new ImageIcon(Icon.getScaledImage(Model.i.getIcon(DEFAULT_CLICK_ICON), 150, 120)));

		setPreferredSize(new Dimension(DEFAULT_STATUSBAR_WIDTH, DEFAULT_STATUSBAR_HEIGHT));
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
