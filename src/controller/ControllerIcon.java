package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Piece;

/**
 * The <code>ControllerIcon</code> class handle requests from user. (<b>C</b> of
 * the <b>MVC</b> pattern).
 *
 * @author LeavaTail
 */
public class ControllerIcon {
	/** default stored icon file path. */
	// danganv3/icons/***.png
	static final String DEFAULT_ICONPATH = "./icons";

	/**
	 * loaded icon file.
	 * <p>
	 * filename name => icon instance
	 */
	private HashMap<String, BufferedImage> icons = new HashMap<String, BufferedImage>();

	/** reload font file. */
	public ControllerIcon() {
		refresh(DEFAULT_ICONPATH);
	}

	/** reload font file. */
	public ControllerIcon(String path) {
		refresh(path);
	}

	/**
	 * load icon file.
	 *
	 * @param path
	 *            icon file path
	 */
	protected void loadIcon(File[] paths) {
		BufferedImage img;
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i].getPath();
			try {
				img = ImageIO.read(new File(path));
				int index = paths[i].getName().lastIndexOf('.');
				if (index != -1) {
					icons.put(paths[i].getName().substring(0, index), img);
				} else {
					icons.put(paths[i].getName(), img);
				}
			} catch (Exception e) {
				e.printStackTrace();
				img = new BufferedImage(Piece.DEFAULT_SIZE, Piece.DEFAULT_SIZE, BufferedImage.TYPE_INT_BGR);
			}
		}
	}

	/**
	 * reload icon file.
	 */
	public void refresh(String path) {
		icons = new HashMap<String, BufferedImage>();
		loadIcon(getIconFile(path));
	}

	/**
	 * get icon file to <code>path</code>.
	 *
	 * @param path
	 *            be loading icon file directory
	 * @return be loading icon file
	 */
	protected File[] getIconFile(String path) {
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File file, String str) {
				if (str.endsWith("png")) {
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
	 * get icon data.
	 *
	 * @param name
	 *            icon file name
	 * @return loaded icon
	 */
	public BufferedImage getIcon(String name) {
		try {
			return icons.get(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}