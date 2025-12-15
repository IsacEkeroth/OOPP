package com.grupp26.aquasim;

public interface IAudio extends ISoundObserver {
    void playSound(String filePath);

    void playMusic(String filePath);

}
