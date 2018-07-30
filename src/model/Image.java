/**
 *
 */
package model;

import static monolith.MonolithConf.DEFAULT_BLOCK_SIZE;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <code>Icon</code> express icon image.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Image extends ImageIcon {
	/**
	 * convert image to resized image.
	 *
	 * @param b
	 *            image file.
	 */
	public Image(String path) {
		try {
			this.setImage(ImageIO.read(getClass().getResourceAsStream(path)));
		} catch (Exception e) {
			e.printStackTrace();
			this.setImage(new BufferedImage(DEFAULT_BLOCK_SIZE, DEFAULT_BLOCK_SIZE, BufferedImage.TYPE_INT_BGR));
		}
	}

	/**
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 *
	 * @param src
	 *            Img source image to scale
	 * @param w
	 *            desired width
	 * @param h
	 *            desired height
	 * @return the new resized image
	 */
	public Image(String path, int w, int h) {
		int finalw = w;
		int finalh = h;
		double factor = 1.0d;
		BufferedImage src;
		try {
			src = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			src = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
			e.printStackTrace();
		}

		if (src.getWidth() > src.getHeight()) {
			factor = ((double) src.getHeight() / (double) src.getWidth());
			finalh = (int) (finalw * factor);
		} else {
			factor = ((double) src.getWidth() / (double) src.getHeight());
			finalw = (int) (finalh * factor);
		}

		BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(src, 0, 0, finalw, finalh, null);
		g2.dispose();
		this.setImage(resizedImg);
	}
}