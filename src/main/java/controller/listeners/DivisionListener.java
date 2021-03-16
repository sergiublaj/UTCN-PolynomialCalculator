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

public class DivisionListener implements ActionListener {
    public static final String DIVISION_TITLE = "Result of division is:";
    public static final String DIVISION_FAIL = "Division operation failed!";
    public static final String DIVISION_SUCCESS = "Division operation completed successfully!";
    public static final String DIVISION_ZERO = "Division by zero error!";

    private final Model appModel;
    private final View appView;
    private final ExceptionHandler exHandler;

    public DivisionListener(Model appModel, View appView, ExceptionHandler exHandler) {
        this.appModel = appModel;
        this.appView = appView;
        this.exHandler = exHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.FIRST_POLYNOMIAL));
            IntegerPolynomial secondPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.SECOND_POLYNOMIAL));

            if(secondPolynomial.getMonomialList().size() == 1 && secondPolynomial.getMonomialList().get(0).getCoefficient() == 0) {
                appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DIVISION_TITLE);
                appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, DIVISION_ZERO);
                appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DIVISION_FAIL, Color.RED);
                return;
            }

            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(secondPolynomial);
            appModel.dividePolynomials();

            DoublePolynomial firstOutput = appModel.getResultTerm();
            String firstParsed = PolynomialInterpreter.parseValue(firstOutput);
            DoublePolynomial secondOutput = appModel.getRemainderTerm();
            String secondParsed = PolynomialInterpreter.parseValue(secondOutput);

            appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DIVISION_TITLE);
            appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, firstParsed + " (R: " + secondParsed + ")");
            appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, DIVISION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), DIVISION_FAIL);
        }
    }
}
