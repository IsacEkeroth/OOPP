package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    BufferedImage image;

    DrawPanel(BufferedImage image, int x, int y) {
        super();

        this.image = image;

        // vet ej om detta behövs
        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(x, y));

        // this.setBackground(Color.pink);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 50, 50, null);
    }

}