package com.grupp26.aquasim;

public interface ISoundObservable {

    void addSoundObserver(ISoundObserver observer);

    void removeSoundObserver(ISoundObserver observer);

    void notifyPlaySound(String soundName);

    void notifyPlayMusic(String musicName);
}
