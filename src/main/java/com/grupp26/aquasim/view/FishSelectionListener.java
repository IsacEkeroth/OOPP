package com.grupp26.aquasim.view;

/**
 * Is used in Controller and View.
 * Purpose: listens to the buttons in the fish-menu and
 */

// Denna ska kanske vara i Controller?
public interface FishSelectionListener {
    void onFishSelected(String fishType);
}
