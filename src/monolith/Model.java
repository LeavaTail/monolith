package monolith;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Model extends Observable {
	public int default_status_size = 32;
	public int default_score_size = 28;
	public int max_time = 600000; // 制限時間

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
		      square[r][c] = new Block();
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

	public void printBoard(Model model) {
		for(int c = 0; c < Main.column; c++){
			for(int r = 0; r < Main.row; r++) {
				System.out.print(model.square[r][c].getColor() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}

	public int inc() {
		return inc(Main.blockscore);
	}

	public int inc(int score)  {
		setScore(getScore() + score);
		return getScore();
	}

	public int dec() {
		return dec(Main.blockscore);
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

	/*
	 * is block specified color?
	 * @filter: specified color. if filter less than -1, get all ambient block
	 * */
	public Boolean checkFilter(int filter) {
		if(this.color > 0 && filter < 0) return true;
		if(this.color == filter) return true;
		return false;
	}

	public Boolean chengeBlock(int color) {
		if(this.color == 0) return false;
		this.color = color;
		return true;
	}

	/*
	 * Get A set of same color Ambient block "p"
	 * @ambient: A Set of Ambient block "p"
	 * @model: Present board
	 * @p: search target
	 * @filter: Get only specified color. if filter less than -1, get all ambient block
	 * */
	private void getAmbientBlock(HashSet<Point> ambient, Model model, Point p, int filter) {
		Block b;
		int x = p.x;
		int y = p.y;

		if(y - 1 >= 0) {
			b = model.square[x][y-1];
			if(b.checkFilter(filter)) {
				ambient.add(new Point(x, y-1));
			}
		}
		if(x + 1 < Main.row) {
			b = model.square[x+1][y];
			if(b.checkFilter(filter)) {
				ambient.add(new Point(x+1, y));
			}
		}
		if(y + 1 < Main.column) {
			b = model.square[x][y+1];
			if(b.checkFilter(filter)) {
				ambient.add(new Point(x, y+1));
			}
		}
		if(x - 1 >= 0) {
			b = model.square[x-1][y];
			if(b.checkFilter(filter)) {
				ambient.add(new Point(x-1, y));
			}
		}
	}

	/*
	 * Get A set of same color ambient a set of ambient
	 * @available: Return a set of same color ambient a set of block
	 * @ambient: Input search a set of blocks
	 * @model: Present board
	 * */
	private int checkDig(HashSet<Point> available, HashSet<Point> ambient, Model model) {
	    HashSet<Point> tmp = new HashSet<Point>();

		for (Iterator<Point> i = ambient.iterator(); i.hasNext();) {
		    Point p =  i.next();
		    if(available.add(p)) {
		    	tmp.add(p);
		    }
		}

		for (Iterator<Point> i = tmp.iterator(); i.hasNext();) {
		    Point p =  i.next();
		    if(model.square[p.x][p.y].getColor() <= 0)
		    	return 0;

		    HashSet<Point> nextAmbient = new HashSet<Point>();
			getAmbientBlock(nextAmbient, model, p, this.color);
			if(tmp.size() > 0)
				checkDig(available, nextAmbient, model);
		}
		return available.size();
	}

	/*
	 * Change color block in ambient. 1 -> 2 -> 3 -> 4 -> 1 -> ...
	 * @ambient: Input search a set of blocks
	 * @model: Present board
	 * */
	private void changeAmbientBlock(HashSet<Point> ambient, Model model) {
		int c = 0;
		for (Iterator<Point> i = ambient.iterator(); i.hasNext();) {
			Point p =  i.next();
			Block b = model.square[p.x][p.y];

			c = (b.color > 0) && ++(b.color) > 4 ? b.color % 4 : b.color;
			b.chengeBlock(c);
		}
	}

	/*
	 * Change color ambient block in available
	 * @available: a set of dug block
	 * @model: Present board
	 * */
	private void doAmbientBlock(HashSet<Point> available, Model model) {
	    HashSet<Point> ambient = new HashSet<Point>();

		for (Iterator<Point> i = available.iterator(); i.hasNext();) {
			Point p =  i.next();
			getAmbientBlock(ambient, model, p, -1);
		}
		changeAmbientBlock(ambient, model);
	}

	private void doDig(HashSet<Point> available, Model model) {
		for (Iterator<Point> i = available.iterator(); i.hasNext();) {
		    Point p =  i.next();
		    model.square[p.x][p.y].chengeBlock(0);
		}
	}

	public void Dig(Model model) {
		HashSet<Point> available = new HashSet<Point>();
		HashSet<Point> ambient = new HashSet<Point>();

		ambient.add(this.point);
		if(checkDig(available, ambient, model) > 1) {
			doDig(available, model);
			doAmbientBlock(available, model);
			model.inc(5 * available.size());
		}

	}
}

class TimeLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private DateFormat format;
	private int limited;
	private Model model;

    public TimeLabel(){
    	this(new Model());
    }

    public TimeLabel(Model m){
    	this.model = m;
    	this.setFont(new Font(Main.default_font12, Font.BOLD, model.default_score_size));
    	limited = model.max_time;
        format = new SimpleDateFormat("mm:ss:SSS");
        Timer t = new Timer();
        t.schedule(new TimerLabelTask(), 10, 1);
    }

    public void setTime(){
        this.setText(format.format(--limited));
    }

    class TimerLabelTask extends TimerTask {

        public void run(){
        	if(limited > 0) {
                setTime();
        	} else {
        		cancel();
        		System.out.println(limited);
        	}
        }
    }
}


class ScoreLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private Model model;
	private DateFormat format;

    public ScoreLabel(){
        this(new Model());
    }

    public ScoreLabel(Model m){
    	this.model = m;
        this.setFont(new Font(Main.default_font12 ,Font.BOLD, model.default_score_size));
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
	Model model;
	Font font;

	public StatusLabel(){
    	this("　");
    }

	public StatusLabel(String text){
		this(text, new Model());
	}

	public StatusLabel(String text, Model model){
		super(text);
		this.model = model;
		font = new Font(Main.default_font10, Font.PLAIN, model.default_status_size);
		this.setFont(font);
        setForeground(Color.WHITE);
	}
}

class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;

	Model model;
	StatusLabel first;
	StatusLabel second;

    public StatusBar(){
    	this(new Model());
    }

    public StatusBar(Model model){
    	this.model = model;
    	setLayout(new BorderLayout());
        first = new StatusLabel();
        second = new StatusLabel();

        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        this.add(first, BorderLayout.NORTH);
        this.add(second, BorderLayout.SOUTH);

        setOpaque(true);
        LineBorder border = new LineBorder(Color.WHITE, 4, true);
        setBorder(border);
    }
}