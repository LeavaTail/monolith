package model;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;

/**
 * The <code>Block</code> class.
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public abstract class Block extends JButton {
	/** located position. */
	protected Point p;

	/** block size. */
	static protected Dimension size;

	/**
	 * Get located position.
	 *
	 * @return located position
	 */
	public Point getPoint() {
		return p;
	}

	/**
	 * Set located position.
	 *
	 * @param p
	 *            located position
	 */
	public void setPoint(Point p) {
		this.p = p;
	}

	/** create new block. */
	abstract public void create();

	/** delete block. */
	abstract public void delete();

	/** change block. */
	abstract public void change();

	/** display block. */
	abstract public void print();
}