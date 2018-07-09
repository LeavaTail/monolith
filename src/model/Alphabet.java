package model;

/**
 * The <code>Alphabet</code> class represents English alphabet character set.
 * 
 * @author LeavaTail
 */
public class Alphabet {
	/** English Alphabet Circular strings */
	private static final String alphabets = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Get lower English alphabet of the specified number.
	 * 
	 * @param i
	 *            index number
	 * @return specified lower English alphabet
	 */
	public static char lower(int i) {
		if (i < 0)
			return ' ';
		int index = i % alphabets.length();
		return alphabets.charAt(index);
	}

	/**
	 * Get upper English alphabet of the specified number.
	 * 
	 * @param i
	 *            index number
	 * @return specified upper English alphabet
	 */
	public static char upper(int i) {
		if (i < 0)
			return ' ';
		int index = i % alphabets.length();
		return alphabets.toUpperCase().charAt(index);
	}
}