package controller;

import controller.listeners.*;
import model.Model;
import validator.exceptions.ExceptionHandler;
import view.PolynomialPanel;
import view.View;

public class Controller {

    private final Model appModel;
    private final View appView;

    public Controller(Model appModel, View appView) {
        this.appModel = appModel;
        this.appView = appView;
        this.addEventListeners();
    }

    private void addEventListeners() {
        this.addClearListeners();
        this.addOperationListeners();
    }

    private void addClearListeners() {
        appView.addClearListener(PolynomialPanel.FIRST_POLYNOMIAL, new ClearListener(appModel, appView, PolynomialPanel.FIRST_POLYNOMIAL));
        appView.addClearListener(PolynomialPanel.SECOND_POLYNOMIAL, new ClearListener(appModel, appView, PolynomialPanel.SECOND_POLYNOMIAL));
        appView.addClearListener(PolynomialPanel.OUTPUT_POLYNOMIAL, new ClearListener(appModel, appView, PolynomialPanel.OUTPUT_POLYNOMIAL));
    }

    private void addOperationListeners() {
        ExceptionHandler exHandler = new ExceptionHandler(appModel, appView);
        appView.addOperationListener(View.ADD_BTN, new AdditionListener(appModel, appView, exHandler));
        appView.addOperationListener(View.SUB_BTN, new SubtractionListener(appModel, appView, exHandler));
        appView.addOperationListener(View.MUL_BTN, new MultiplicationListener(appModel, appView, exHandler));
        appView.addOperationListener(View.DIV_BTN, new DivisionListener(appModel, appView, exHandler));
        appView.addOperationListener(View.DER_BTN, new DerivationListener(appModel, appView, exHandler));
        appView.addOperationListener(View.INT_BTN, new IntegrationListener(appModel, appView, exHandler));
    }
}
