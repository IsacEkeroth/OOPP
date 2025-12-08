package com.grupp26.aquasim;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Audio {
    // private JFXPanel jFXPanel;
    private MediaPlayer mediaPlayer;

    Audio() {
        JFXPanel jFXPanel = new JFXPanel(); // this needs to be created once to be able to play sound.

    }

    public void playSound(String fileName) {
        Media media = loadMedia(fileName);
        mediaPlayer = new MediaPlayer(media);
        // for music to loop
        // mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }

    // bubble-pop.mp3
    public Media loadMedia(String fileName) {
        Media media;
        try {
            URL resource = getClass().getResource("/audio/" + fileName);
            if (resource == null) {
                throw new RuntimeException("File not found");
            }
            media = new Media(resource.toExternalForm());
        } catch (Exception e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
        return media;
    }
}
