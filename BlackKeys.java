package KP;

import java.awt.Color;

import javax.swing.JButton;


//class that implements the black piano keys to its array, by using the key interface.
public class BlackKeys extends JButton implements Key {
	
	
	final int note;
//constructor that creates the font for the black piano keys
	public BlackKeys(int pos) {
		note = baseNote + 1 + 2 * pos + (pos + 3) / 5 + pos / 5;
		int left = 10 + WD + ((WD * 3) / 2) * (pos + (pos / 5) + ((pos + 3) / 5));
		setBackground(Color.BLACK);
		setBounds(left, 10, WD, HT);
	}

	public int getNote() {
		return note;	}

}
