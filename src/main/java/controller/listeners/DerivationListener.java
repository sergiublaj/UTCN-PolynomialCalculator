package controller.listeners;

import model.*;
import validator.ExceptionHandler;
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
    private final ExceptionHandler exHandler;

    public DerivationListener(Model appModel, View appView, ExceptionHandler exHandler) {
        this.appModel = appModel;
        this.appView = appView;
        this.exHandler = exHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));
            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(null);
            appModel.derivePolynomial();

            DoublePolynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);

            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, DERIVATION_TITLE);
            appView.setInput(PolynomPanel.SECOND_POLYNOM, "");
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, DERIVATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), DERIVATION_FAIL);
        }
    }
}
