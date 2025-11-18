package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    ArrayList<IRenderedEntity> entities = new ArrayList<>();
    ArrayList<IRenderedEntity> l0 = new ArrayList<>();
    ArrayList<IRenderedEntity> l1 = new ArrayList<>();
    ArrayList<IRenderedEntity> l2 = new ArrayList<>();

    DrawPanel(int w, int h) {
        super();

        // vet ej om detta behövs
        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(w, h));
    }

    public void addEntity(IRenderedEntity e) {
        // TODO: add depth enum/ change way its handled
        this.entities.add(e);
        int depth = e.getDepth();
        if (depth == 0) {
            l0.add(e);
        } else if (depth == 1) {
            l1.add(e);
        } else if (depth == 2) {
            l2.add(e);
        } else {
            throw new Error("Illegal depth");
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (IRenderedEntity entity : l0) {
            renderEntity(entity, g);
        }
        for (IRenderedEntity entity : l1) {
            renderEntity(entity, g);
        }
        for (IRenderedEntity entity : l2) {
            renderEntity(entity, g);
        }
    }

    private void renderEntity(IRenderedEntity entity, Graphics g) {
        Point pos = entity.getPos();
        Point size = entity.getSize();

        g.drawImage(entity.getImage(), (int) pos.getX(), (int) pos.getY(), (int) size.getX(), (int) size.getY(),
                null);
    }

}