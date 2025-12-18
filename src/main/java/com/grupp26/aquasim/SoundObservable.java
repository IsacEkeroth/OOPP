package com.grupp26.aquasim;

import java.util.ArrayList;
import java.util.List;

/**
 * A concrete implementation of the {@link ISoundObservable} interface that
 * manages sound-related notifications using the Observer pattern.
 * <p>
 *     This class acts as a subject (Observable) that maintains a list of sound observers. It
 *     provides a simplified internal API for the rest of the simulation to
 *     trigger audio events, such as sound effects or music, without being
 *     coupled to the underlying audio playback implementation.
 * </p>
 */
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
