package KP;

import java.awt.Color;
import javax.swing.JButton;


//class that implements the white piano keys to its array, by using the key interface.
public class WhiteKeys extends JButton implements Key {
	
	
	static int WWD = (WD * 3) / 2;
	static int WHT = (HT * 3) / 2;
	 int note;
	//constructor that creates the font for the white piano keys.
	public WhiteKeys(int pos) {
		
		note = baseNote + 2 * pos - (pos + 4) / 7 - pos / 7;
		
		int left = 10 + WWD * pos;
	    
		setBackground (Color.WHITE);
		setBounds(left, 10, WWD, WHT);

	}
	
	public int getNote() {
		return note;
	}

	
}
