/**
 *
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * <code>Icon</code> express icon image.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class Icon extends JButton {
	/**
	 * convert image to resized image.
	 *
	 * @param b
	 *            image file.
	 */
	public Icon(BufferedImage b) {
		setIcon(new ImageIcon(getScaledImage(b, 150, 120)));
		setBackground(Color.BLACK);
		LineBorder border = new LineBorder(Color.BLACK, 0, true);
		setBorder(border);
		setContentAreaFilled(false);
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
	static public BufferedImage getScaledImage(BufferedImage src, int w, int h) {
		int finalw = w;
		int finalh = h;
		double factor = 1.0d;
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
		return resizedImg;
	}
}