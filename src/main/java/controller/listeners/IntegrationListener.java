package controller.listeners;

import model.*;
import validator.ExceptionHandler;
import view.PolynomPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegrationListener implements ActionListener {
    private static final String INTEGRATION_TITLE = "Result of integration of first polynomial is:";
    private static final String INTEGRATION_FAIL = "Integration operation failed!";
    private static final String INTEGRATION_SUCCESS = "Integration operation completed successfully!";

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
            IntegerPolynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));

            if(firstPolynomial.getMonomialList().size() == 1 && firstPolynomial.getMonomialList().get(0).getExponent() == -1) {
                appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_TITLE);
                appView.setInput(PolynomPanel.OUTPUT_POLYNOM, firstPolynomial.getMonomialList().get(0).getCoefficient() + "ln(x)+C");
                appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_SUCCESS, Color.GREEN);
                return;
            }

            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(null);
            appModel.integratePolynomial();

            DoublePolynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial) + "+C";

            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_TITLE);
            appView.setInput(PolynomPanel.SECOND_POLYNOM, "");
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), INTEGRATION_FAIL);
        }
    }
}
