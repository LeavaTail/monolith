package monolith;

public class Main {
	public static final int row = 18; //　行
	public static final int column = 11;  // 列
	public static final int max_colors = 4; //　色種類

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new View(new Controller(new Model()));
	}
}