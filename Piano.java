package KP;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
//interface that have the supplement dimensions for the piano keys
interface Key {
	// change WD to suit your screen
	int WD = 44;
	int HT = (WD * 9) / 2;
	// change baseNote for starting octave
	// multiples of 16 only
	int baseNote = 32;

	int getNote();
}

public class Piano extends JFrame implements KeyListener {

	JFrame mainFrame;

	final static int OCTAVES = 4;
	//Arrays with predetermined number of elements(piano keys) that are to be stored.
	static WhiteKeys[] whites = new WhiteKeys[3 * OCTAVES + 2];
	static BlackKeys[] blacks = new BlackKeys[2 * OCTAVES + 2];

	static MidiChannel channel;
	//constructor that retrieves & store the sounds from MidiSystem.
	public Piano() {

		try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			synth.loadAllInstruments(synth.getDefaultSoundbank());
			Instrument[] insts = synth.getLoadedInstruments();
			MidiChannel channels[] = synth.getChannels();
			for (int i = 0; i < channels.length; i++) {
				if (channels[i] != null) {
					channel = channels[i];
					break;
				}
			}

			for (int i = 0; i < insts.length; i++) {
				if (insts[i].toString().startsWith("Instrument Piano")) {
					channel.programChange(i);
					break;
				}
			}
		} catch (MidiUnavailableException ex) {
			ex.printStackTrace();
		}
	}
	//the graphical user interface
	@SuppressWarnings("serial")
	private void createAndShowGUI() {
		//panel that determines the programs window size automatically, sets the size by the size of and the number of piano keys.
		JPanel cP = new JPanel(null) {

			@Override
			public Dimension getPreferredSize() {
				int count = getComponentCount();
				Component last = getComponent(count - 1);
				Rectangle bounds = last.getBounds();
				int width = 10 + bounds.x + bounds.width;
				int height = 10 + bounds.y + bounds.height;

				return new Dimension(width, height);

			}

			@Override
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}

		};
 //for loops that determines the number of piano keys that the panel will have & adds a key listener to each piano key.
		for (int i = 0; i < blacks.length; i++) {
			blacks[i] = new BlackKeys(i);
			cP.add(blacks[i]);
			blacks[i].addKeyListener(this);

		}
		for (int i = 0; i < whites.length; i++) {

			whites[i] = new WhiteKeys(i);
			cP.add(whites[i]);
			whites[i].addKeyListener(this);

		}

		buttonMarks();
		mainFrame = new JFrame("Keyboard Piano");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(cP);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Piano().createAndShowGUI();

			}
		});

	}
	//method required for the usage of KeyEvents, kept empty.
	public void keyTyped(KeyEvent ew) {
	
	}
	//method used to bind keys on the keyboard, that activates a tone from the MidiChannel collection.
	@Override
	public void keyPressed(KeyEvent ew) {
		int key = ew.getKeyCode();
		// White Keys **************
		if (key == KeyEvent.VK_A) {
			channel.noteOn(32, 127);
		}
		if (key == KeyEvent.VK_S) {
			channel.noteOn(34, 127);
		}
		if (key == KeyEvent.VK_D) {
			channel.noteOn(36, 127);
		}
		if (key == KeyEvent.VK_F) {
			channel.noteOn(37, 127);
		}
		if (key == KeyEvent.VK_G) {
			channel.noteOn(39, 127);
		}
		if (key == KeyEvent.VK_H) {
			channel.noteOn(41, 127);
		}
		if (key == KeyEvent.VK_J) {
			channel.noteOn(43, 127);
		}
		if (key == KeyEvent.VK_Z) {
			channel.noteOn(44, 127);
		}
		if (key == KeyEvent.VK_X) {
			channel.noteOn(46, 127);
		}
		if (key == KeyEvent.VK_C) {
			channel.noteOn(48, 127);
		}
		if (key == KeyEvent.VK_V) {
			channel.noteOn(49, 127);
		}
		if (key == KeyEvent.VK_B) {
			channel.noteOn(51, 127);
		}
		if (key == KeyEvent.VK_N) {
			channel.noteOn(53, 127);
		}
		if (key == KeyEvent.VK_M) {
			channel.noteOn(55, 127);
		}
		// Black Keys **************
		if (key == KeyEvent.VK_Q) {
			channel.noteOn(33, 127);
		}
		if (key == KeyEvent.VK_W) {
			channel.noteOn(35, 127);
		}
		if (key == KeyEvent.VK_E) {
			channel.noteOn(38, 127);
		}
		if (key == KeyEvent.VK_R) {
			channel.noteOn(40, 127);
		}
		if (key == KeyEvent.VK_T) {
			channel.noteOn(42, 127);
		}
		if (key == KeyEvent.VK_Y) {
			channel.noteOn(45, 127);
		}
		if (key == KeyEvent.VK_U) {
			channel.noteOn(47, 127);
		}
		if (key == KeyEvent.VK_I) {
			channel.noteOn(50, 127);
		}
		if (key == KeyEvent.VK_K) {
			channel.noteOn(52, 127);
		}
		if (key == KeyEvent.VK_L) {
			channel.noteOn(54, 127);
		}
	}
	//method used to bind keys on the keyboard, that deactivates a tone from the MidiChannel collection.
	@Override
	public void keyReleased(KeyEvent ew) {
		int key = ew.getKeyCode();
		// White Keys ****************
		if (key == KeyEvent.VK_A) {

			channel.noteOff(32, 127);
		}
		if (key == KeyEvent.VK_S) {

			channel.noteOff(34, 127);
		}
		if (key == KeyEvent.VK_D) {

			channel.noteOff(36, 127);
		}
		if (key == KeyEvent.VK_F) {

			channel.noteOff(37, 127);
		}
		if (key == KeyEvent.VK_G) {

			channel.noteOff(39, 127);
		}
		if (key == KeyEvent.VK_H) {

			channel.noteOff(41, 127);
		}
		if (key == KeyEvent.VK_J) {

			channel.noteOff(43, 127);
		}
		if (key == KeyEvent.VK_Z) {

			channel.noteOff(44, 127);
		}
		if (key == KeyEvent.VK_X) {

			channel.noteOff(46, 127);
		}
		if (key == KeyEvent.VK_C) {

			channel.noteOff(48, 127);
		}
		if (key == KeyEvent.VK_V) {

			channel.noteOff(49, 127);
		}
		if (key == KeyEvent.VK_B) {

			channel.noteOff(51, 127);
		}
		if (key == KeyEvent.VK_N) {

			channel.noteOff(53, 127);
		}
		if (key == KeyEvent.VK_M) {

			channel.noteOff(55, 127);
		}
		// Black Keys****************
		if (key == KeyEvent.VK_Q) {

			channel.noteOff(33, 127);
		}
		if (key == KeyEvent.VK_W) {

			channel.noteOff(35, 127);
		}
		if (key == KeyEvent.VK_E) {

			channel.noteOff(38, 127);
		}
		if (key == KeyEvent.VK_R) {

			channel.noteOff(40, 127);
		}
		if (key == KeyEvent.VK_T) {

			channel.noteOff(42, 127);
		}
		if (key == KeyEvent.VK_Y) {

			channel.noteOff(45, 127);
		}
		if (key == KeyEvent.VK_U) {

			channel.noteOff(47, 127);
		}
		if (key == KeyEvent.VK_I) {

			channel.noteOff(50, 127);
		}
		if (key == KeyEvent.VK_K) {

			channel.noteOff(52, 127);
		}
		if (key == KeyEvent.VK_L) {

			channel.noteOff(54, 127);
		}
	}
	//method used to set text on the buttons on the piano.
	public void buttonMarks() {
		// Black Keys*****************
		((AbstractButton) blacks[0]).setText("Q");
		((AbstractButton) blacks[0]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[0]).setFocusable(false);
		((AbstractButton) blacks[0]).setForeground(Color.white);
		((AbstractButton) blacks[1]).setText("W");
		((AbstractButton) blacks[1]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[1]).setFocusable(false);
		((AbstractButton) blacks[1]).setForeground(Color.white);
		((AbstractButton) blacks[2]).setText("E");
		((AbstractButton) blacks[2]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[2]).setFocusable(false);
		((AbstractButton) blacks[2]).setForeground(Color.white);
		((AbstractButton) blacks[3]).setText("R");
		((AbstractButton) blacks[3]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[3]).setFocusable(false);
		((AbstractButton) blacks[3]).setForeground(Color.white);
		((AbstractButton) blacks[4]).setText("T");
		((AbstractButton) blacks[4]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[4]).setFocusable(false);
		((AbstractButton) blacks[4]).setForeground(Color.white);
		((AbstractButton) blacks[5]).setText("Y");
		((AbstractButton) blacks[5]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[5]).setFocusable(false);
		((AbstractButton) blacks[5]).setForeground(Color.white);
		((AbstractButton) blacks[6]).setText("U");
		((AbstractButton) blacks[6]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[6]).setFocusable(false);
		((AbstractButton) blacks[6]).setForeground(Color.white);
		((AbstractButton) blacks[7]).setText("I");
		((AbstractButton) blacks[7]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[7]).setFocusable(false);
		((AbstractButton) blacks[7]).setForeground(Color.white);
		((AbstractButton) blacks[8]).setText("K");
		((AbstractButton) blacks[8]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[8]).setFocusable(false);
		((AbstractButton) blacks[8]).setForeground(Color.white);
		((AbstractButton) blacks[9]).setText("L");
		((AbstractButton) blacks[9]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) blacks[9]).setFocusable(false);
		((AbstractButton) blacks[9]).setForeground(Color.white);
		// White Keys ****************
		((AbstractButton) whites[0]).setText("A");
		((AbstractButton) whites[0]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[0]).setFocusable(false);
		((AbstractButton) whites[1]).setText("S");
		((AbstractButton) whites[1]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[1]).setFocusable(false);
		((AbstractButton) whites[2]).setText("D");
		((AbstractButton) whites[2]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[2]).setFocusable(false);
		((AbstractButton) whites[3]).setText("F");
		((AbstractButton) whites[3]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[3]).setFocusable(false);
		((AbstractButton) whites[4]).setText("G");
		((AbstractButton) whites[4]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[4]).setFocusable(false);
		((AbstractButton) whites[5]).setText("H");
		((AbstractButton) whites[5]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[5]).setFocusable(false);
		((AbstractButton) whites[6]).setText("J");
		((AbstractButton) whites[6]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[6]).setFocusable(false);
		((AbstractButton) whites[7]).setText("Z");
		((AbstractButton) whites[7]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[7]).setFocusable(false);
		((AbstractButton) whites[8]).setText("X");
		((AbstractButton) whites[8]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[8]).setFocusable(false);
		((AbstractButton) whites[9]).setText("C");
		((AbstractButton) whites[9]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[9]).setFocusable(false);
		((AbstractButton) whites[10]).setText("V");
		((AbstractButton) whites[10]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[10]).setFocusable(false);
		((AbstractButton) whites[11]).setText("B");
		((AbstractButton) whites[11]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[11]).setFocusable(false);
		((AbstractButton) whites[12]).setText("N");
		((AbstractButton) whites[12]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[12]).setFocusable(false);
		((AbstractButton) whites[13]).setText("M");
		((AbstractButton) whites[13]).setFont(new Font("Arial", Font.BOLD, 11));
		((AbstractButton) whites[13]).setFocusable(true);
	}

}
