package controller;

import static monolith.MonolithConf.DEFAULT_FONTPATH;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

/**
 * The <code>ControllerFont</code> class handle requests from user. (<b>C</b> of
 * the <b>MVC</b> pattern).
 *
 * @author LeavaTail
 */
public class ControllerFont {
	private String suffix = "ttf";
	/**
	 * loaded font file.
	 * <p>
	 * Family name => Font instance
	 */
	private HashMap<String, Font> fonts = new HashMap<String, Font>();

	/** reload font file. */
	public ControllerFont() {
		refresh(DEFAULT_FONTPATH);
	}

	/** reload font file. */
	public ControllerFont(String path) {
		refresh(path);
	}

	/**
	 * load font file.
	 *
	 * @param path
	 *            font file path
	 */
	protected void loadFont(File[] path) {
		Font font;
		for (int i = 0; i < path.length; i++) {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(path[i].getPath()));
				fonts.put(font.getFamily(), font);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * reload font file.
	 */
	public void refresh(String path) {
		fonts = new HashMap<String, Font>();
		loadFont(getFontFile(path));
	}

	/**
	 * get font file to <code>path</code>.
	 *
	 * @param path
	 *            be loading font file directory
	 * @return be loading font file
	 */
	protected File[] getFontFile(String path) {
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File file, String str) {
				if (str.endsWith(suffix)) {
					return true;
				} else {
					return false;
				}
			}
		};
		File dir = new File(path);
		return dir.listFiles(filter);
	}

	/**
	 * get font data.
	 *
	 * @param name
	 *            font file name
	 * @return loaded font
	 */
	public Font getFont(String name) {
		return fonts.get(name);
	}
}