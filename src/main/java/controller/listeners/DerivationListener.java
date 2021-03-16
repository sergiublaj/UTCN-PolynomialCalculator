package controller.listeners;

import model.*;
import model.polynomial.DoublePolynomial;
import model.polynomial.IntegerPolynomial;
import validator.exceptions.ExceptionHandler;
import validator.PolynomialInterpreter;
import view.PolynomialPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DerivationListener implements ActionListener {
    public static final String DERIVATION_TITLE = "Result of derivation of the first polynomial is";
    public static final String DERIVATION_FAIL = "Derivation operation failed!";
    public static final String DERIVATION_SUCCESS = "Derivation operation completed successfully!";

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
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.FIRST_POLYNOMIAL));

            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(null);
            appModel.derivePolynomial();

            DoublePolynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);

            appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DERIVATION_TITLE);
            appView.setInput(PolynomialPanel.SECOND_POLYNOMIAL, "");
            appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, parsedOutput);
            appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DERIVATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), DERIVATION_FAIL);
        }
    }
}
