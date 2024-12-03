package mancala;

package mancala;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class MancalaView extends JPanel {
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 200;
    private static final int PIT_SIZE = 60;
    private static final int MANCALA_WIDTH = 70;
    private static final int MANCALA_HEIGHT = 160;
    
    public MancalaView() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.WHITE);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw main board rectangle
        g2.setColor(new Color(160, 82, 45));
        g2.fill(new RoundRectangle2D.Double(90, 20, 
                620, 160, 20, 20));
        
        // Draw Mancalas
        g2.setColor(new Color(205, 133, 63));
        // Mancala B (left)
        g2.fill(new RoundRectangle2D.Double(20, 20, 
                MANCALA_WIDTH, MANCALA_HEIGHT, 30, 30));
        // Mancala A (right)
        g2.fill(new RoundRectangle2D.Double(710, 20, 
                MANCALA_WIDTH, MANCALA_HEIGHT, 30, 30));
        
        drawPits(g2);
        drawLabels(g2);
    }
    
    private void drawPits(Graphics2D g2) {
        g2.setColor(new Color(205, 133, 63));
        
        int startX = 130;
        int spacing = 95;
        int boardCenterY = 100; // Center of the board
        int pitSpacing = 70;    // Vertical space between pit centers
        
        // Calculate Y positions for perfect vertical alignment
        int topY = boardCenterY - (pitSpacing/2) - (PIT_SIZE/2);
        int bottomY = boardCenterY + (pitSpacing/2) - (PIT_SIZE/2);
        
        // Draw pits
        for (int i = 0; i < 6; i++) {
            // Top row (B6-B1)
            g2.fill(new Ellipse2D.Double(startX + (i * spacing), topY, 
                    PIT_SIZE, PIT_SIZE));
            // Bottom row (A1-A6)
            g2.fill(new Ellipse2D.Double(startX + (i * spacing), bottomY, 
                    PIT_SIZE, PIT_SIZE));
        }
    }
    
    private void drawLabels(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Label Mancalas
        g2.drawString("B", 45, 100);
        g2.drawString("A", 735, 100);
        
        int startX = 150;
        int spacing = 95;
        int boardCenterY = 100;
        int labelSpacing = 70;
        
        // Calculate Y positions for labels
        int topLabelY = boardCenterY - (labelSpacing/2) + 5;
        int bottomLabelY = boardCenterY + (labelSpacing/2) + 5;
        
        // Top row (B6-B1)
        for (int i = 6; i >= 1; i--) {
            g2.drawString("B" + i, startX + ((6-i) * spacing), topLabelY);
        }
        
        // Bottom row (A1-A6)
        for (int i = 1; i <= 6; i++) {
            g2.drawString("A" + i, startX + ((i-1) * spacing), bottomLabelY);
        }
    }
}