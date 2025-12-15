package com.grupp26.aquasim.view;

import com.grupp26.aquasim.model.IModelFacade;

import javax.swing.*;

public interface IMainView extends IObserver {
    void setFacade(IModelFacade facade);

    JButton getAddFishButton();

    JButton getRemoveFishButton();

    JButton getAddFoodButton();

    JButton getDecorationButton();

}
