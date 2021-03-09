package controller.listeners;

import model.PolynomialInterpreter;
import model.Model;
import model.Polynomial;
import view.PolynomPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivisionListener implements ActionListener {
    private static final String DIVISION_TITLE = "Result of division is:";
    private static final String DIVISION_FAIL = "Division operation failed!";
    private static final String DIVISION_SUCCESS = "Division operation completed successfully!";

    private final Model appModel;
    private final View appView;

    public DivisionListener(Model appModel, View appView) {
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
            appModel.dividePolynomials();
            Polynomial firstOutput = appModel.getResultTerm();
            String firstParsed = PolynomialInterpreter.parseValue(firstOutput);
            Polynomial secondOutput = appModel.getRemainderTerm();
            String secondParsed = PolynomialInterpreter.parseValue(secondOutput);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, DIVISION_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, firstParsed + " (R: " + secondParsed + ")");
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, DIVISION_SUCCESS, Color.GREEN);
        } catch (Exception polynomEx) {
            appView.setAlertMessage(PolynomPanel.FIRST_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.SECOND_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, DIVISION_FAIL, Color.RED);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, View.OUTPUT_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, "");
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }
}
