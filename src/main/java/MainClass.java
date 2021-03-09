import controller.Controller;
import model.Model;
import view.View;

// TODO
// JUnit
// Documentatie
// de reorganizat codul duplicat
//
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
