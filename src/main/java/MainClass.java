import controller.Controller;
import model.*;
import view.View;

// TODO
// Documentatie
// maxim 30 linii la print!!

// BUGS
// 5 * 8

public class MainClass {
    public static void main(String[] args) {
        Model appModel = new Model();
        View appView = new View();
        Controller appController = new Controller(appModel, appView);
        appView.setVisible(true);
    }
}
