package monolith;

import java.util.Observer;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.Observable;


public class View extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	protected enum WindowPanel {
		Main,
		Status,
		Side;
	}

	protected enum PanePanel {

	}

	JPanel main = new JPanel();
	JPanel board = new JPanel();
	JPanel header = new JPanel();
	StatusLabel status = new StatusLabel();

	JPanel highscorebar = new JPanel();
	JPanel scorebar = new JPanel();
	JPanel buttonbar = new JPanel();

	JLabel score  = new JLabel();
	JLabel scorel  = new JLabel(" score: ");
	JLabel highscore = new JLabel();
	JLabel highscorel = new JLabel(" highscore: ");
	JButton undo = new JButton();
	JButton redo = new JButton();
	JButton restart = new JButton();
	JButton stop = new JButton();
	JButton home = new JButton();
	JButton hint = new JButton();
	JButton help = new JButton();
	JButton search = new JButton();
	TimeLabel time;

	MenuBar menubar = new MenuBar();

	Model model;
	int width = 0;
	int height = 0;

	public View (Controller aController) {
		model = aController.getModel();
		time = new TimeLabel();

		board.setLayout(new GridLayout(Main.column, Main.row));

		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
//			  square[c][r].setActionCommand(c + "," + r);		/* ActoinListener用のコマンド作成 */
		      board.add(model.square[r][c]);
		      switch(model.square[r][c].getColor()) {
		      case 1:
				  model.square[r][c].setIcon(new ImageIcon(model.img1));
				  break;
		      case 2:
				  model.square[r][c].setIcon(new ImageIcon(model.img2));
				  break;
		      case 3:
				  model.square[r][c].setIcon(new ImageIcon(model.img3));
				  break;
		      case 4:
				  model.square[r][c].setIcon(new ImageIcon(model.img4));
				  break;
		     default:
				  model.square[r][c].setIcon(new ImageIcon(model.img0));
				  break;
		      }
			  model.square[r][c].addActionListener(aController);
		    }
		}

		createMenu(menubar);

        setLabel(score, Main.default_font, Main.default_fontsize);
        setLabel(scorel, Main.default_font, Main.default_fontsize);
        setLabel(highscore, Main.default_font, Main.default_fontsize);
        setLabel(highscorel, Main.default_font, Main.default_fontsize);

        status.setPreferredSize(new Dimension(960, 80));

		header.setLayout(new GridLayout(8, 1, -20, -20));
		header.setPreferredSize(new Dimension(280, 460));

		scorebar.setLayout(new BorderLayout());
        highscorebar.setLayout(new BorderLayout());

		scorebar.add(scorel, BorderLayout.WEST);

		highscorebar.add(highscorel, BorderLayout.WEST);
		scorebar.add(score, BorderLayout.EAST);
		highscorebar.add(highscore, BorderLayout.EAST);

		header.add(scorebar);
	    scorebar.setBackground(Color.BLACK);
		header.add(highscorebar);
	    highscorebar.setBackground(Color.BLACK);
		header.add(time);
		time.setBackground(Color.BLACK);

		setPanel(header);
		setPanel(status);

		time.setIcon(new ImageIcon(model.imgclock));

		main.setLayout(new BorderLayout(15,16));
		main.add(board);

		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(960, 540));

		this.add(header, BorderLayout.EAST);
		this.add(main, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);

		setResizable(false);
		//j.setUndecorated(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(aController);
		this.setTitle("ダンガンロンパv3 お宝発見！モノリス");

		this.addWindowListener(aController);
		setVisible(true);
		setLabel(status.first, Main.default_font, Main.default_fontsize);
	}

	private void createMenu(MenuBar menubar) {
         setMenuBar(menubar);

         Menu Menu1 = new Menu("ファイル");
         menubar.add(Menu1);

         MenuItem newm = new MenuItem("新規作成");
         MenuItem openm = new MenuItem("開く");
         MenuItem savem = new MenuItem("保存");
         MenuItem closem = new MenuItem("閉じる");

         //メニューアイテムの追加
         Menu1.add(newm);
         Menu1.add(openm);
         Menu1.add(savem);
         Menu1.add(closem);
	}

	public Model getModel() {
		return this.model;
	}

	private void setPanel(JPanel p) {
	      LineBorder border = new LineBorder(Color.WHITE, 4, true);
	      p.setBackground(Color.BLACK);
	      p.setForeground(Color.WHITE);
	      p.setOpaque(true);
          p.setBorder(border);
	}

	private void setLabel(JLabel l, String font, int size) {
		l.setForeground(Color.WHITE);
        l.setFont(new Font(font ,Font.BOLD, size));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO 自動生成されたメソッド・スタブ
		score.setText(Integer.toString(((Model)o).getScore()));
		highscore.setText(Integer.toString(((Model)o).getHighscore()));

		View view = this;
		Model model = view.getModel();
		Block square = model.square[0][0];
		Dimension size = square.getSize();
        Image scaled;

        status.first.setText("aaa");

		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
				square = model.square[r][c];

				switch(square.getColor()) {
			    case 1:
  			          scaled = model.img1.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    case 2:
			          scaled = model.img2.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
			    	  break;
			    case 3:
			          scaled = model.img3.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    case 4:
			          scaled = model.img4.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    default:
			          scaled = model.img0.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
					  break;
			    }
		        square.setIcon(new ImageIcon(scaled));
		        view.repaint();
		    }
		}
	}
}
