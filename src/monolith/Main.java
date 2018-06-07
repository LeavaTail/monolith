package monolith;

public class Main {
	public static final int row = 18; //　行
	public static final int column = 11;  // 列
	public static final int max_colors = 4; //　色種類
	public static final int blockscore = 5; // ブロック一個当たりのスコア
	public static final int max_text = 58; // 最大文字数
	public static final String default_font = "PixelMplus12";
	public static final int default_fontsize = 32;

	public static final int[] ranktable = {
		0, 900, 1400, 1900, 2400, 2900, 3400, 3900, 4400, 4900
	};

	public static void main(String[] args) {
		new View(new Controller(new Model()));
	}
}