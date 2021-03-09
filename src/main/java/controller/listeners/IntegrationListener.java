package controller.listeners;

import model.PolynomialInterpreter;
import model.Model;
import model.Polynomial;
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

    public IntegrationListener(Model appModel, View appView) {
        this.appModel = appModel;
        this.appView = appView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Polynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));
            appModel.setSecondOperand(null);
            appView.setInput(PolynomPanel.SECOND_POLYNOM, "");
            String parsedOutput;
            if(firstPolynomial.getMonomialList().size() == 1 && firstPolynomial.getMonomialList().get(0).getCoefficient() == 1 && firstPolynomial.getMonomialList().get(0).getExponent() == -1) {
                parsedOutput = "ln(x)+C";
            } else {
                appModel.setFirstOperand(firstPolynomial);
                appModel.integratePolynomial();
                Polynomial outputPolynomial = appModel.getResultTerm();
                parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);
                parsedOutput += "+C";
            }
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_SUCCESS, Color.GREEN);
        } catch (Exception polynomEx) {
            appView.setAlertMessage(PolynomPanel.FIRST_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.SECOND_POLYNOM, polynomEx.getMessage(), Color.RED);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, INTEGRATION_FAIL, Color.RED);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, View.OUTPUT_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, "");
            appModel.clearResultTerm();
            appModel.clearRemainderTerm();
        }
    }
}
