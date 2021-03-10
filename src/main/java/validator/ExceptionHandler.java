package validator;

import model.Model;
import view.PolynomPanel;
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
        appView.setAlertMessage(PolynomPanel.FIRST_POLYNOM, exceptionMessage, Color.RED);
        appView.setAlertMessage(PolynomPanel.SECOND_POLYNOM, exceptionMessage, Color.RED);
        appView.setAlertMessage(PolynomPanel.OUTPUT_POLYNOM, titleMessage, Color.RED);
        appView.setTitleMessage(PolynomPanel.OUTPUT_POLYNOM, View.OUTPUT_TITLE);
        appView.setInput(PolynomPanel.OUTPUT_POLYNOM, "");
        appModel.clearResultTerm();
        appModel.clearRemainderTerm();
    }
}
