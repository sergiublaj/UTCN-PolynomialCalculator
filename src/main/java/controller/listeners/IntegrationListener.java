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

public class IntegrationListener implements ActionListener {
    public static final String INTEGRATION_TITLE = "Result of integration of first polynomial is:";
    public static final String INTEGRATION_FAIL = "Integration operation failed!";
    public static final String INTEGRATION_SUCCESS = "Integration operation completed successfully!";

    private final Model appModel;
    private final View appView;
    private final ExceptionHandler exHandler;

    public IntegrationListener(Model appModel, View appView, ExceptionHandler exHandler) {
        this.appModel = appModel;
        this.appView = appView;
        this.exHandler = exHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomialPanel.FIRST_POLYNOMIAL));

            if(firstPolynomial.getMonomialList().size() == 1 && firstPolynomial.getMonomialList().get(0).getExponent() == -1) {
                appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, INTEGRATION_TITLE);
                appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, firstPolynomial.getMonomialList().get(0).getCoefficient() + "ln(x)+C");
                appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, INTEGRATION_SUCCESS, Color.GREEN);
                return;
            }

            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(null);
            appModel.integratePolynomial();

            DoublePolynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial) + "+C";

            appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, INTEGRATION_TITLE);
            appView.setInput(PolynomialPanel.SECOND_POLYNOMIAL, "");
            appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, parsedOutput);
            appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, INTEGRATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), INTEGRATION_FAIL);
        }
    }
}
