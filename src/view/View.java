package view;

import static monolith.MonolithConf.DEFAULT_HEIGHT;
import static monolith.MonolithConf.DEFAULT_WIDTH;
import static monolith.MonolithConf.GAMETITLE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller.Controller;
import model.Model;
/**
 * The <code>View</code> class means presentation of the model in a particular
 * format. (<b>V</b> of the <b>MVC</b> pattern)
 *
 * @author LeavaTail
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Observer {

	/** managing the data of the application. */
	private Model model;

	/**
	 * set target controller
	 *
	 * @param controller
	 *            handle requests from user.
	 */
	public View(Controller controller) {
		Window main = new Window();
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		menubar.add(menu1);

		setModel(controller.getModel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		pack();
		main.biimLayout(new Game(), new Side(), new Status());

		this.setLayout(new BorderLayout());
		this.add(main);
		setJMenuBar(menubar);

		setTitle(GAMETITLE);
		setVisible(true);
	}

	/**
	 * This method is called whenever the observed object is changed.
	 *
	 * @param o
	 *            an observable object, or "data" in the model-view paradigm.
	 * @param arg
	 *            the root of the class hierarchy.
	 *
	 */
	@Override
	public void update(Observable o, Object arg) {
	}

	/**
	 * get target model.
	 *
	 * @return target model.
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * set Specified model.
	 *
	 * @param model
	 *            Specified model.
	 */
	public void setModel(Model model) {
		this.model = model;
	}
}