package controller.listeners;

import model.Model;
import view.PolynomPanel;
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

        if(fieldNb == PolynomPanel.FIRST_POLYNOM) {
            appModel.setFirstOperand(null);
        } else if(fieldNb == PolynomPanel.SECOND_POLYNOM) {
            appModel.setSecondOperand(null);
        } else {
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }

}
