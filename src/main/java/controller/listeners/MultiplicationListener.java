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

public class MultiplicationListener implements ActionListener {
    public static final String MULTIPLICATION_TITLE = "Result of multiplication is:";
    public static final String MULTIPLICATION_FAIL = "Multiplication operation failed!";
    public static final String MULTIPLICATION_SUCCESS = "Multiplication operation completed successfully!";

    private final Model appModel;
    private final View appView;
    private final ExceptionHandler exHandler;

    public MultiplicationListener(Model appModel, View appView, ExceptionHandler exHandler) {
        this.appModel = appModel;
        this.appView = appView;
        this.exHandler = exHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.FIRST_POLYNOMIAL));
            IntegerPolynomial secondPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.SECOND_POLYNOMIAL));

            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(secondPolynomial);
            appModel.multiplyPolynomials();

            DoublePolynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);

            appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, MULTIPLICATION_TITLE);
            appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, parsedOutput);
            appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, MULTIPLICATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), MULTIPLICATION_FAIL);
        }
    }
}

