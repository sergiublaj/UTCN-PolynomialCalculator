package controller.listeners;

import model.PolynomialInterpreter;
import model.Model;
import model.Polynomial;
import view.PolynomPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionListener implements ActionListener {
    private static final String ADDITION_TITLE = "Result of addition is";
    private static final String ADDITION_FAIL = "Addition operation failed!";
    private static final String ADDITION_SUCCESS = "Addition operation completed successfully!";

    private final Model appModel;
    private final View appView;

    public AdditionListener(Model appModel, View appView) {
        this.appModel = appModel;
        this.appView = appView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Polynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));
            Polynomial secondPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.SECOND_POLYNOM));
            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(secondPolynomial);
            appModel.addPolynomials();
            Polynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, ADDITION_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, ADDITION_SUCCESS, Color.GREEN);
        } catch (Exception polynomEx) {
            appView.setAlertMessage(PolynomPanel.FIRST_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.SECOND_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, ADDITION_FAIL, Color.RED);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, View.OUTPUT_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, "");
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }
}
