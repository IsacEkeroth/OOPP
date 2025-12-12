package com.grupp26.aquasim;

import java.util.ArrayList;
import java.util.List;

public class SoundObservable implements ISoundObservable {
    private final List<ISoundObserver> soundObservers = new ArrayList<>();

    public void addSoundObserver(ISoundObserver observer) {
        soundObservers.add(observer);
    }

    public void removeSoundObserver(ISoundObserver observer) {
        soundObservers.remove(observer);
    }

    public void notifyPlaySound(String soundName) {
        for (ISoundObserver observer : soundObservers) {
            observer.playSound(soundName);
        }
    }

    public void notifyPlayMusic(String musicName) {
        for (ISoundObserver observer : soundObservers) {
            observer.playMusic(musicName);
        }
    }
}
