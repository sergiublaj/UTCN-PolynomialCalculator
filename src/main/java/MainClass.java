import controller.Controller;
import model.*;
import view.View;

public class MainClass {
    public static void main(String[] args) {
        Model appModel = new Model();
        View appView = new View();
        Controller appController = new Controller(appModel, appView);
        appView.setVisible(true);
    }
}
