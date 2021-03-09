package controller.listeners;

import model.PolynomialInterpreter;
import model.Model;
import model.Polynomial;
import view.PolynomPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DerivationListener implements ActionListener {
    private static final String DERIVATION_TITLE = "Result of derivation of the first polynomial is";
    private static final String DERIVATION_FAIL = "Derivation operation failed!";
    private static final String DERIVATION_SUCCESS = "Derivation operation completed successfully!";

    private final Model appModel;
    private final View appView;

    public DerivationListener(Model appModel, View appView) {
        this.appModel = appModel;
        this.appView = appView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Polynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));
            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(null);
            appView.setInput(PolynomPanel.SECOND_POLYNOM, "");
            appModel.derivePolynomial();
            Polynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, DERIVATION_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, DERIVATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomEx) {
            appView.setAlertMessage(PolynomPanel.FIRST_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.SECOND_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, DERIVATION_FAIL, Color.RED);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, View.OUTPUT_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, "");
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }
}
