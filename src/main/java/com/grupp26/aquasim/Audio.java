package com.grupp26.aquasim;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {
    private JFXPanel jFXPanel;
    private MediaPlayer mediaPlayer;

    Audio() {
        jFXPanel = new JFXPanel();
        mediaPlayer = new MediaPlayer(null);
    }

    public void playSound(String path) {
        Media sound = loadMedia(path);

    }

    public Media loadMedia(String path) {
        Media media;
        try {
            media = new Media(path);
        } catch (Exception e) {
            throw new RuntimeException("File not found", e);
        }
        return media;
    }
}
