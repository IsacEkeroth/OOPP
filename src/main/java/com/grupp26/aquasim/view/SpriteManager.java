package com.grupp26.aquasim.view;

import com.grupp26.aquasim.App;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class SpriteManager {
    private static final HashMap<String, BufferedImage> CACHE = new HashMap<>();
    private static final HashMap<String, AnimationSequence> ANIMATION_MAP = new HashMap<>();

    private static final String FISH_SHEET_PATH = "images/fish_spritesheet_64.png";
    // private static final String DECOR_SHEET_PATH = "images/veryGoodAnchor.jpg";
    private static final String ANCHOR_PATH = "images/veryGoodAnchor.png";
    private static final String SEAWEED_PATH = "images/seaweed.png";
    private static final String BACKGROUND_PATH = "images/akvarium1.jpg";
    private static final String FOOD_PATH = "images/food.png";
    private static final String LOVE_FOOD_PATH = "images/lovefood.png";

    private static final int SPRITE_FRAME_WIDTH = 64;
    private static final int SPRITE_FRAME_HEIGHT = 64;

    static {
        ANIMATION_MAP.put("GOLDFISH", new AnimationSequence(1, 4, 10));
        ANIMATION_MAP.put("CLOWNFISH", new AnimationSequence(9, 4, 10));

        // Förladda bilder
        imageFromString(FISH_SHEET_PATH);
        imageFromString(BACKGROUND_PATH);
        imageFromString(FOOD_PATH);
        imageFromString(LOVE_FOOD_PATH);
        imageFromString(SEAWEED_PATH);
        imageFromString(ANCHOR_PATH);
    }

    public static BufferedImage imageFromString(String path) {
        if (CACHE.containsKey(path)) {
            return CACHE.get(path);
        }
        BufferedImage img = null;
        try {
            img = ImageIO.read(App.class.getClassLoader().getResourceAsStream(path));
            if (img != null) {
                CACHE.put(path, img);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB); // fallback
            Graphics2D errorGraphics = img.createGraphics(); // draw magenta square to indicate error
            errorGraphics.setColor(Color.MAGENTA);
            errorGraphics.fillRect(0, 0, 100, 100);
            errorGraphics.dispose();

            CACHE.put(path, img);
        }
        return img;
    }


    public static BufferedImage getSprite(String entityType, int totalTicks) {
        String type = entityType.toUpperCase();
        // Fall 1: animerad typ
        if (ANIMATION_MAP.containsKey(type)) {
            return getAnimatedFrame(type, totalTicks);
        }

        System.out.println(entityType);
        // Fall 2: Statisk typ
        switch (type) {
            case "BG":
                return imageFromString(BACKGROUND_PATH);
            case "ANCHOR":
                return imageFromString(ANCHOR_PATH);
            case "SEAWEED":
                return imageFromString(SEAWEED_PATH);
            case "FOOD":
                return imageFromString(FOOD_PATH);
            case "LOVE_FOOD":
                return imageFromString(LOVE_FOOD_PATH);
            default:
                // borde hanteras
                return null;
        }
    }


    public static BufferedImage getAnimatedFrame(String entityType, int totalTicks) {
        AnimationSequence seq = ANIMATION_MAP.get(entityType);
        // Temporärt, nu ligger allt som kan animeras i fish_sheet_path
        // Kommer behöva flera val här också beroende på om det är en DECOR, etc, likt
        // getSprite
        BufferedImage spriteSheet = imageFromString(FISH_SHEET_PATH);

        // Temporärt, behöver hanteras
        if (spriteSheet == null)
            return null;

        // Dela totalTicks med hastigheten på animationen
        // Ex) Om ticks är 15 och speed är 10, blir animationTick 1. (Vi stannar på
        // frame 1 i 10 ticks).
        int slowedTick = totalTicks / seq.TICKS_PER_FRAME;
        int currentFrameIndex = slowedTick % seq.MAX_FRAMES;

        int x = currentFrameIndex * SPRITE_FRAME_WIDTH;
        int y = seq.ROWINDEX * SPRITE_FRAME_HEIGHT;
        // Returnera utklippt bild
        // bör antagligen hantera ifall den blir null?
        return spriteSheet.getSubimage(x, y, SPRITE_FRAME_WIDTH, SPRITE_FRAME_HEIGHT);
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

}
