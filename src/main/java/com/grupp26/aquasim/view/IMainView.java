package com.grupp26.aquasim.view;

import com.grupp26.aquasim.controller.ActiveMode;
import com.grupp26.aquasim.model.IModelFacade;

import javax.swing.*;

/**
 * Defines the external interface for the games main view.
 * <p>
 * This interface specifies the methods which are needed to control the user interface,
 * handle interaction-listeners and provides access to the central UI-components.
 * <p>
 * Inheriting from {@link IObserver} guarantees that the view can answer on any state changes
 * in the model.
 */
public interface IMainView extends IObserver {
    void setFacade(IModelFacade facade);

    JButton getAddFishButton();

    JButton getRemoveFishButton();

    JButton getAddFoodButton();

    JButton getDecorationButton();

    DrawPanel getDrawPanel();

    void updateActiveButton(ActiveMode mode, String fishName);

    void addFishMenuListener(FishSelectionListener listener);

    JPanel getFishMenuPanel();

}
