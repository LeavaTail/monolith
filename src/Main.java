import controller.Controller;
import model.Model;
import view.View;

/**
 * <code>Main</code> call monolith simulator.
 * @author LeavaTail
 */
public class Main {

	/**
	 * @param args  command line argument (not use).
	 */
	public static void main(String[] args) {
		new View(new Controller(new Model()));
	}
}