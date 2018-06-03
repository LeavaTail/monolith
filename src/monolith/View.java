package monolith;

import java.util.Observer;
import java.awt.*;
import javax.swing.*;
import java.util.Observable;

public class View extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	JPanel board = new JPanel();
	JPanel header = new JPanel();
	TimeLabel label;
	ScoreLabel score;
	MenuBar menubar;
	StatusBar status;
	Model model;
	int width = 0;
	int height = 0;

	public View (Controller aController) {
		model = aController.getModel();
		status = new StatusBar(model);
		score = new ScoreLabel(model);
		label = new TimeLabel(model);
		menubar = new MenuBar();

		board.setLayout(new GridLayout(Main.column, Main.row));
		header.setLayout(new GridLayout(1, 6));

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

		header.add(new JPanel());
		header.add(score);
		header.add(new JPanel());
		header.add(new JPanel());
		header.add(label);
		header.add(new JPanel());

		this.add(header, BorderLayout.NORTH);
		this.add(board, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.addComponentListener(aController);
		this.setTitle("ダンガンロンパv3 お宝発見！モノリス");

		this.addWindowListener(aController);
		// コンポーネントの推奨サイズと設定したレイアウトからコンテナーのサイズを決定する
		pack();
		setVisible(true);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO 自動生成されたメソッド・スタブ
		score.setText(Integer.toString(((Model)o).getScore()));

		View view = this;
		Model model = view.getModel();
		Block square = model.square[0][0];
		Dimension size = square.getSize();
        Image scaled;

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
