package monolith;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;

public class Controller extends WindowAdapter implements ActionListener, ComponentListener {
	private Model model;

	public Controller(Model model) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.model = model;
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand() == "inc") {
			System.out.println("inc");
			model.inc();
		} else {
			model.dec();
			System.out.println(actionEvent.getActionCommand());
		}
	}

	public void windowOpened(WindowEvent windowEvent) {
		model.addObserver((Observer)windowEvent.getSource());
		model.setScore(model.getScore());
	}

	public void windowClosing(WindowEvent windowEvent) {
		model.deleteObserver((Observer)windowEvent.getSource());
		((Frame)windowEvent.getSource()).dispose();
		if(model.countObservers() == 0) {
			System.exit(0);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		View view = (View)e.getComponent();
		Block square = view.square[0][0];
		Dimension size = square.getSize();
        Image scaled;

		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
				square = view.square[c][r];

				switch(square.getColor()) {
			    case 1:
  			          scaled = view.img1.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    case 2:
			          scaled = view.img2.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
			    	  break;
			    case 3:
			          scaled = view.img3.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    case 4:
			          scaled = view.img4.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    default:
			          scaled = view.img0.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    }
		        square.setIcon(new ImageIcon(scaled));
		        view.repaint();
		    }
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}
}
