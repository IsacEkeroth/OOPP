package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    ArrayList<IRenderedEntity> entities = new ArrayList<>();
    BufferedImage image;

    DrawPanel(BufferedImage image, int x, int y) {
        super();

        this.image = image;

        // vet ej om detta behövs
        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(x, y));
    }

    public void addEntity(IRenderedEntity e) {
        this.entities.add(e);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (IRenderedEntity entity : entities) {
            Point pos = entity.getPos();
            Point size = entity.getSize();

            g.drawImage(entity.getImage(), (int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY(),
                    null);
        }

        // g.drawImage(image, 0, 0, 50, 50, null);
    }

}