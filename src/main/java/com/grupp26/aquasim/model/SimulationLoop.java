package com.grupp26.aquasim.model;

import javax.swing.Timer;

public class SimulationLoop implements ISimulationLoop {
    private final Timer timer;
    
    public SimulationLoop(int delay, Runnable timerAction) {
        this.timer = new Timer(delay, e -> timerAction.run());
        
        timer.start();
    }

    @Override
    public void start() {
        timer.start();
    }
    
    @Override
    public void stop() { // Maybe we want a pause method later?
        timer.stop();
    }
}
