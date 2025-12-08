package com.grupp26.aquasim.view;

import com.grupp26.aquasim.App;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class SpriteManager {
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();
    
    public static BufferedImage imageFromString(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(App.class.getClassLoader().getResourceAsStream(path));
            if (img != null) {
                cache.put(path, img);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
            Graphics2D errorGraphics = img.createGraphics(); // draw magenta square to indicate error
            errorGraphics.setColor(Color.MAGENTA);
            errorGraphics.fillRect(0, 0, 100, 100);
            errorGraphics.dispose();
            
            cache.put(path, img);
        }
        return img;
    }
}
