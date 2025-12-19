package com.grupp26.aquasim.view;

import com.grupp26.aquasim.controller.ActiveMode;
import com.grupp26.aquasim.model.IModelFacade;

import javax.swing.*;

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
