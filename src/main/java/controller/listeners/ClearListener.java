package controller.listeners;

import model.Model;
import view.PolynomialPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {
    private static final String CLEAR_INPUT = "Input cleared successfully!";

    private final Model appModel;
    private final View appView;
    private final int fieldNb;

    public ClearListener(Model appModel, View appView, int fieldNb) {
        this.appModel = appModel;
        this.appView = appView;
        this.fieldNb = fieldNb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        appView.setInput(fieldNb, "");
        appView.setAlertMessage(fieldNb, CLEAR_INPUT, Color.BLUE);

        if(fieldNb == PolynomialPanel.FIRST_POLYNOMIAL) {
            appModel.setFirstOperand(null);
        } else if(fieldNb == PolynomialPanel.SECOND_POLYNOMIAL) {
            appModel.setSecondOperand(null);
        } else {
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }

}
