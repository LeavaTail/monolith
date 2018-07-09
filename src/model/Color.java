package model;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * <code>Color</code> express color block.
 *
 * @author LeavaTail
 */
public enum Color {
	/** Block color variation. */
	BROWN(0, "/zero.png"), GRAY(1, "/one.png"), PINK(2, "/two.png"), YELLOW(3, "/three.png"), BLUE(4, "/four.png");

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
			this.img = new BufferedImage(Piece.DEFAULT_SIZE, Piece.DEFAULT_SIZE, BufferedImage.TYPE_INT_BGR);
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
