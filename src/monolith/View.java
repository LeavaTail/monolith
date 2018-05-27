package monolith;

import java.util.Observer;
import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Observable;

public class View extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	JLabel status = new JLabel("ステータスバー：ここに文字が出力されます。");
	JPanel board = new JPanel();
	JPanel header = new JPanel();
	TimeLabel label = new TimeLabel();
	ScoreLabel score = new ScoreLabel();
	Block square[][] = new Block[Main.column][Main.row];
	public BufferedImage img0;
	public BufferedImage img1;
	public BufferedImage img2;
	public BufferedImage img3;
	public BufferedImage img4;

	public View (Controller aController) {
		MenuBar menubar = new MenuBar();


		board.setLayout(new GridLayout(Main.column, Main.row));
		header.setLayout(new GridLayout(1, 6));
		try {
			img0 = ImageIO.read(getClass().getResourceAsStream("/zero.png"));
			img1 = ImageIO.read(getClass().getResourceAsStream("/one.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/two.png"));
			img3 = ImageIO.read(getClass().getResourceAsStream("/three.png"));
			img4 = ImageIO.read(getClass().getResourceAsStream("/four.png"));
	    } catch (Exception ex) {
		    System.out.println(ex);
		    System.exit(1);
		}

		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
		      square[c][r] = new Block();//Buttonの生成
		      square[c][r].setPreferredSize(new Dimension(31, 31));
		      square[c][r].setBackground(Color.BLACK);
		      generateRandom(square[c][r], Main.max_colors);
		      switch(square[c][r].getColor()) {
		      case 1:
				  square[c][r].setIcon(new ImageIcon(img1));
				  break;
		      case 2:
				  square[c][r].setIcon(new ImageIcon(img2));
				  break;
		      case 3:
				  square[c][r].setIcon(new ImageIcon(img3));
				  break;
		      case 4:
				  square[c][r].setIcon(new ImageIcon(img4));
				  break;
		     default:
				  square[c][r].setIcon(new ImageIcon(img0));
				  break;
		      }
			  square[c][r].setActionCommand(c + "," + r);		/* ActoinListener用のコマンド作成 */
		      board.add(square[c][r]);//生成したボタンをパネルに追加
			  square[c][r].addActionListener(aController);
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

//		this.add(value, BorderLayout.CENTER);
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

	private void generateRandom(Block b, int range) {
	    Random rand = new Random();
		b.setColor(rand.nextInt(range) + 1);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO 自動生成されたメソッド・スタブ
		score.setText(Integer.toString(((Model)o).getScore()));
		System.out.println(((Model)o).getScore());
	}
}
