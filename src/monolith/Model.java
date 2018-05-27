package monolith;

import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Model extends Observable {
	private int score;

	public Model() {
		this(0);
	}

	public Model(int i) {
		setScore(score);
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
}


class TimeLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private DateFormat format;

    public TimeLabel(){
        this.setFont(new Font("Dialog",Font.BOLD,24));
        format = new SimpleDateFormat("HH:mm:ss");
        Timer t = new Timer();
        t.schedule(new TimerLabelTask(), 0,1000);
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