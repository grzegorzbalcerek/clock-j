package clock;

import javafx.application.Application;
import javafx.stage.Stage;
import nz.sodium.Unit;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clock");

        Display display = new Display(
                () -> System.out.println("switch"),
                () -> System.out.println("add"));
        display.setSeparator(":");
        display.setSecond("00");
        display.setMinute("00");
        display.setHour("00");

        primaryStage.setScene(display.getScene());
        primaryStage.show();
    }

}
