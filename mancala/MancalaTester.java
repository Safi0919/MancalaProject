package mancala;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MancalaTester extends JFrame {
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Mancala Game");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(new MancalaView());
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	    }

}
