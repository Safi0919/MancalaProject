package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MancalaView extends JPanel {
	private static final int BOARD_WIDTH = 800;
	private static final int BOARD_HEIGHT = 300;
	private static final int PIT_DIAMETER = 80;
	private static final int MANCALA_WIDTH = 80;
	private static final int MANCALA_HEIGHT = 200;

	public MancalaView() {
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Main Board
		g2.setColor(new Color(139, 69, 19)); // Brown
		g2.fill(new RoundRectangle2D.Double(40, 50, BOARD_WIDTH - 80, BOARD_HEIGHT - 100, 20, 20));

		// Draw Mancalas
		g2.setColor(new Color(205, 133, 63)); // Light Brown
		// Mancala A
		g2.fill(new RoundRectangle2D.Double(BOARD_WIDTH - 120, 30, MANCALA_WIDTH, MANCALA_HEIGHT, 20, 20));
		// Mancala B
		g2.fill(new RoundRectangle2D.Double(40, 30, MANCALA_WIDTH, MANCALA_HEIGHT, 20, 20));

		// Pits on each side
		drawPits(g2);

		// Draw labels
		drawLabels(g2);
	}

	private void drawPits(Graphics2D g2) {
		g2.setColor(new Color(205, 133, 63));
		int startX = 160;
		int topY = 80;
		int bottomY = 170;

		// B1-B6 (top row)
		for (int i = 0; i < 6; i++) {
			g2.fill(new Ellipse2D.Double(startX + (i * 100), topY, PIT_DIAMETER, PIT_DIAMETER));
		}

		// A1-A6 (bottom row)
		for (int i = 0; i < 6; i++) {
			g2.fill(new Ellipse2D.Double(startX + (i * 100), bottomY, PIT_DIAMETER, PIT_DIAMETER));
		}
	}

	private void drawLabels(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		// Label Mancalas
		g2.drawString("B", 70, 140);
		g2.drawString("A", BOARD_WIDTH - 90, 140);

		// Label pits
		int startX = 190;
		int topY = 120;
		int bottomY = 210;

		// Top row (B1-B6)
		for (int i = 1; i <= 6; i++) {
			g2.drawString("B" + (7 - i), startX + ((i - 1) * 100), topY);
		}
		// Bottom row (A1-A6)
		for (int i = 1; i <= 6; i++) {
			g2.drawString("A" + i, startX + ((i - 1) * 100), bottomY);
		}
	}
}