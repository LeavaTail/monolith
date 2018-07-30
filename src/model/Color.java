package model;

import static monolith.MonolithConf.DEFAULT_BLOCK_SIZE;
import static monolith.MonolithConf.FOUR_IMG;
import static monolith.MonolithConf.ONE_IMG;
import static monolith.MonolithConf.THREE_IMG;
import static monolith.MonolithConf.TWO_IMG;
import static monolith.MonolithConf.ZERO_IMG;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * <code>Color</code> express color block.
 *
 * @author LeavaTail
 */
public enum Color {
	/** Block color variation. */
	BROWN(0, ZERO_IMG), GRAY(1, ONE_IMG), PINK(2, TWO_IMG), YELLOW(3, THREE_IMG), BLUE(4, FOUR_IMG);

	/** Block color number. */
	private int id;

	/** Block color file image. */
	public BufferedImage img;

	/**
	 * load color file.
	 *
	 * @param id
	 *            color number.
	 * @param path
	 *            color image file.
	 */
	private Color(int id, String path) {
		this.id = id;
		try {
			this.img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
			this.img = new BufferedImage(DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, BufferedImage.TYPE_INT_BGR);
		}
	}

	/**
	 * get Block identity number.
	 *
	 * @return Block identity number.
	 */
	public int getId() {
		return this.id;
	}
}
