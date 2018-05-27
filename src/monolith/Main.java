package monolith;

import java.awt.Point;

import javax.swing.JButton;

public class Main {
	public static final int row = 18; //　行
	public static final int column = 11;  // 列
	public static final int max_colors = 4; //　色種類

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new View(new Controller(new Model()));
	}

}

class Block extends JButton {
	private static final long serialVersionUID = 1L;

	private Point point;
	private int color;

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
}