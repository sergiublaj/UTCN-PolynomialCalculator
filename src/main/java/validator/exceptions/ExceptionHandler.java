package validator.exceptions;

import model.Model;
import view.PolynomialPanel;
import view.View;

import java.awt.*;

public class ExceptionHandler {
    private final Model appModel;
    private final View appView;

    public ExceptionHandler(Model appModel, View appView) {
        this.appModel = appModel;
        this.appView = appView;
    }

    public void catchException(String exceptionMessage, String titleMessage) {
        appView.setAlertMessage(PolynomialPanel.FIRST_POLYNOMIAL, exceptionMessage, Color.RED);
        appView.setAlertMessage(PolynomialPanel.SECOND_POLYNOMIAL, exceptionMessage, Color.RED);
        appView.setAlertMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, titleMessage, Color.RED);
        appView.setTitleMessage(PolynomialPanel.OUTPUT_POLYNOMIAL, View.OUTPUT_TITLE);
        appView.setInput(PolynomialPanel.OUTPUT_POLYNOMIAL, "");
        appModel.clearResultTerm();
        appModel.clearRemainderTerm();
    }
}
