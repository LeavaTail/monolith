package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Model;

/**
 * The <code>Controller</code> class handle requests from user. it responds to
 * the user input and performs interactions on the data model objects. The
 * controller receives the input, optionally validates it and then passes the
 * input to the model. (<b>C</b> of the <b>MVC</b> pattern)
 *
 * @author LeavaTail
 */
public class Controller implements MouseListener, ActionListener {
	/** Control target model. */
	private Model model;

	/** set target model. */
	public Controller(Model model) {
		setModel(model);
	}

	/**
	 * get target model.
	 *
	 * @return model target model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * set target model.
	 *
	 * @param model
	 *            target model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Called just after the user clicks the listened-to component.
	 *
	 * @param e
	 *            Mouse action
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Called just after the user presses a mouse button while the cursor is over
	 * the listened-to component.
	 *
	 * @param e
	 *            Mouse action
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Called just after the user releases a mouse button after a mouse press over
	 * the listened-to component.
	 *
	 * @param e
	 *            Mouse action
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Called just after the cursor enters the bounds of the listened-to component.
	 *
	 * @param e
	 *            Mouse action
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Emphasizes enable to dig block.
	}

	/**
	 * Called just after the cursor exits the bounds of the listened-to component.
	 *
	 * @param e
	 *            Mouse action
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Release emphasizes block
	}

	/**
	 * Called just after the user performs an action.
	 *
	 * @param e
	 *            component-defined action occurred
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO dig block
	}
}
