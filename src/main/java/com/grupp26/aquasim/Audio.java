package com.grupp26.aquasim;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Audio {
    // private JFXPanel jFXPanel;
    Map<String, Media> cache = new HashMap<String, Media>();

    Audio() {
        JFXPanel jFXPanel = new JFXPanel(); // this needs to be created once to be able to play sound.

    }

    public void playSound(String fileName) {
        try {
            MediaPlayer mediaPlayer;

            if (!cache.containsKey(fileName)) {
                cache.put(fileName, loadMedia(fileName, "effects"));
            }

            mediaPlayer = new MediaPlayer(cache.get(fileName));
            mediaPlayer.play();

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playMusic(String fileName) {
        try {
            MediaPlayer mediaPlayer;

            if (!cache.containsKey(fileName)) {
                cache.put(fileName, loadMedia(fileName, "music"));
            }
            mediaPlayer = new MediaPlayer(cache.get(fileName));
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public Media loadMedia(String fileName, String category) {
        Media media;
        try {
            URL resource = getClass().getResource("/audio/" + category + "/" + fileName + ".mp3");
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
