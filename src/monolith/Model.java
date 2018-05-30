package monolith;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Model extends Observable {
	private int score;
	Block square[][] = new Block[Main.row][Main.column];
	public BufferedImage img0;
	public BufferedImage img1;
	public BufferedImage img2;
	public BufferedImage img3;
	public BufferedImage img4;

	public Model() {
		this(0);
		initBoard();
	}

	public Model(int i) {
		setScore(i);

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
	}

	public void initBoard() {
		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
		      square[r][c] = new Block();//Buttonの生成
		      square[r][c].setPoint(r, c);
		      square[r][c].setPreferredSize(new Dimension(32, 32));
		      square[r][c].setBackground(Color.BLACK);
		      generateRandom(square[r][c], Main.max_colors);
		    }
		}
	}

	public final int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}

	public int inc() {
		return inc(5);
	}

	public int inc(int score)  {
		setScore(getScore() + score);
		return getScore();
	}

	public int dec() {
		return dec(5);
	}

	public int dec(int score)  {
		setScore(getScore() - score);
		return getScore();
	}

	private void generateRandom(Block b, int range) {
	    Random rand = new Random();
		b.setColor(rand.nextInt(range) + 1);
	}
}


class Block extends JButton {
	private static final long serialVersionUID = 1L;
	private Point point;
	private int color;

	public Block() {
		this.point = new Point();
		this.color = 0;
	}

	public void setColor(int c) {
		this.color = c;
	}

	public int getColor() {
		return this.color;
	}

	public void setPoint(int x, int y) {
		this.point.x = x;
		this.point.y = y;
	}

	public Point getPoint() {
		return this.point;
	}

	public Boolean checkColor(int color) {
		if(this.color == color) return true;
		return false;
	}

	public Boolean chengeBlock(int color) {
		if(this.color == 0) return false;
		this.color = color;
		return true;
	}

	private int cheakDig(HashSet<Point> available, Model model, Point point, int color, int num) {
		int x = point.x;
		int y = point.y;

		if(model.square[x][y].getColor() == 0) {
			System.out.println("unable dig");
			return num;
		}
		// 上
		if(y - 1 >= 0)
			if(model.square[x][y-1].checkColor(color)) {
				Point p = new Point(x, y-1);
				if(available.add(p)) {
					cheakDig(available, model, p, color, ++num);
				}
			}
		// 右
		if(x + 1 < Main.row)
			if(model.square[x+1][y].checkColor(color)) {
				Point p = new Point(x+1, y);
				if(available.add(p)) {
					cheakDig(available, model, p, color, ++num);
				}
			}
		// 下
		if(y + 1 < Main.column)
			if(model.square[x][y+1].checkColor(color)) {
				Point p = new Point(x, y+1);
				if(available.add(p)) {
					cheakDig(available, model, p, color, ++num);
				}
			}
		// 左
		if(x -1 >= 0)
			if(model.square[x-1][y].checkColor(color)) {
				Point p = new Point(x-1, y);
				if(available.add(p)) {
					cheakDig(available, model, p, color, ++num);
				}
			}

		return num;
	}

	private void doDig(HashSet<Point> available, Model model) {
		int num = available.size();
		for (Iterator<Point> i = available.iterator(); i.hasNext();) {
		    Point p =  i.next();
		    model.square[p.x][p.y].chengeBlock(0);
		}
		model.inc(5 * num);
	}

	public void Dig(Model model) {
		int color = this.color;
		Point point = this.point;
		HashSet<Point> available = new HashSet<Point>();
		available.add(point);
		if(cheakDig(available, model, point, color, 1) > 1)
			doDig(available, model);
	}
}


class TimeLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private DateFormat format;
	private int limited;

    public TimeLabel(){
    	this.setFont(new Font("Dialog",Font.BOLD,24));
    	limited = 600000;
        format = new SimpleDateFormat("mm:ss:SSS");
        Timer t = new Timer();
        t.schedule(new TimerLabelTask(), 10,1);
    }

    public void setTime(){
        this.setText(format.format(limited--));
    }

    class TimerLabelTask extends TimerTask {

        public void run(){
        	if(limited > 0) {
                setTime();
        	}
        }
    }
}


class ScoreLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private DateFormat format;

    public ScoreLabel(){
        this.setFont(new Font("Dialog",Font.BOLD,24));
    }

    public void setTime(){
        Calendar calendar = Calendar.getInstance(Locale.JAPAN);
        this.setText(format.format(calendar.getTime()));
    }

    class TimerLabelTask extends TimerTask {

        public void run(){
            setTime();
        }
    }
}


class StatusLabel extends JLabel {
	private static final long serialVersionUID = 1L;

    public StatusLabel(){
        this.setFont(new Font("Dialog",Font.BOLD,24));
    }
}