package com.grupp26.aquasim.view;

/**
 * A listener-interface which handles events when a user chooses a specific fish
 * in the fish-menu (UI). This is used to toggle the fish menu (open/close).
 * <p>
 * The view notifies the listener which type of fish that has been selected (which
 * button that has been pressed).
 */

// Denna ska kanske vara i Controller?
public interface FishSelectionListener {
    void onFishSelected(String fishType);
}
