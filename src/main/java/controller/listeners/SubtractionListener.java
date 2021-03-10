package controller.listeners;

import model.Model;
import model.Polynomial;
import model.PolynomialInterpreter;
import validator.ExceptionHandler;
import view.PolynomPanel;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubtractionListener implements ActionListener {
    private static final String SUBTRACTION_TITLE = "Result of subtraction is:";
    private static final String SUBTRACTION_FAIL = "Subtraction operation failed!";
    private static final String SUBTRACTION_SUCCESS = "Subtraction operation completed successfully!";

    private final Model appModel;
    private final View appView;
    private final ExceptionHandler exHandler;

    public SubtractionListener(Model appModel, View appView, ExceptionHandler exHandler) {
        this.appModel = appModel;
        this.appView = appView;
        this.exHandler = exHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Polynomial firstPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.FIRST_POLYNOM));
            Polynomial secondPolynomial = PolynomialInterpreter.parseString(appView.getInput(PolynomPanel.SECOND_POLYNOM));
            appModel.setFirstOperand(firstPolynomial);
            appModel.setSecondOperand(secondPolynomial);
            appModel.subtractPolynomials();
            Polynomial outputPolynomial = appModel.getResultTerm();
            String parsedOutput = PolynomialInterpreter.parseValue(outputPolynomial);
            appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, SUBTRACTION_TITLE);
            appView.setInput(PolynomPanel.OUTPUT_POLYNOM, parsedOutput);
            appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, SUBTRACTION_SUCCESS, Color.GREEN);
        } catch (Exception polynomialEx) {
            exHandler.catchException(polynomialEx.getMessage(), SUBTRACTION_FAIL);
        }
    }
}
