package com.grupp26.aquasim.view;

import com.grupp26.aquasim.App;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class SpriteManager {
    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    private static final HashMap<String, AnimationSequence> ANIMATION_MAP = new HashMap<>();


    private static final BufferedImage SPRITESHEET  = SpriteManager.imageFromString("images/fish_spritesheet_64.png");
    private static final int FRAME_WIDTH = 64;
    private static final int FRAME_HEIGHT = 64;
    private static final int MAX_FRAMES = 4;




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


    public static BufferedImage getFrame(String entityType, int totalTicks) {
        AnimationSequence seq = ANIMATION_MAP.get(entityType.toUpperCase());
        if (seq == null || seq.MAX_FRAMES == 0) return null; // borde kanske hantera felet

        // Dela totalTicks med hastigheten på animationen
        // Ex) Om ticks är 15 och speed är 10, blir animationTick 1. (Vi stannar på frame 1 i 10 ticks).
        int slowedTick = totalTicks / seq.TICKS_PER_FRAME;

        int currentFrameIndex = slowedTick % seq.MAX_FRAMES;

        int x = currentFrameIndex * FRAME_WIDTH;
        int y = seq.ROWINDEX * FRAME_HEIGHT;
        // Returnera utklippt bild
        // bör antagligen hantera ifall den blir null?
        return SPRITESHEET.getSubimage(x, y, FRAME_WIDTH, FRAME_HEIGHT);
    }


    public static int getMaxFrames(String entityID) {
        return MAX_FRAMES;
    }


    private static class AnimationSequence {
        final int ROWINDEX;
        final int MAX_FRAMES;
        final int TICKS_PER_FRAME;

        AnimationSequence(int rowIndex, int maxFrames, int ticksPerFrame) {
            this.ROWINDEX = rowIndex;
            this.MAX_FRAMES = maxFrames;
            this.TICKS_PER_FRAME = ticksPerFrame;
        }
    }

    static {
        ANIMATION_MAP.put("FISH", new AnimationSequence(0,4, 5));
    }


}
