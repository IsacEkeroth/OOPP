package com.grupp26.aquasim.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import java.util.Map;
import java.util.TreeMap;

public class DrawPanel extends JPanel {
    Map<Integer, ArrayList<IRenderedEntity>> layers = new HashMap<>();

    DrawPanel(int w, int h) {
        super();

        // vet ej om detta behövs
        this.setDoubleBuffered(true);

        this.setPreferredSize(new Dimension(w, h));
    }

    public void addEntity(IRenderedEntity e) {
        int depth = e.getDepth();
        layers.putIfAbsent(depth, new ArrayList<>());
        if (!layers.get(depth).contains(e)) {
            layers.get(depth).add(e);
        }
    }

    public void removeEntity(IRenderedEntity e) {
        int depth = e.getDepth();
        if (layers.containsKey(depth)) {
            layers.get(depth).remove(e);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ArrayList<IRenderedEntity> layer : layers.values()) {
            for (IRenderedEntity entity : layer) {
                renderEntity(entity, g);
            }
        }
    }

    private void renderEntity(IRenderedEntity entity, Graphics g) {
        Point pos = entity.getPos();
        Point size = entity.getSize();

        if (entity.getImage() != null) {
            g.drawImage(entity.getImage(),
                    (int) pos.getX(), (int) pos.getY(),
                    (int) size.getX(), (int) size.getY(),
                    null);
        }
    }
    
    public void clear() {
        layers.clear();
    }

}