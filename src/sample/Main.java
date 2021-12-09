package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.models.building.Logger;

import java.util.logging.Level;

public class Main extends Application {
    private static Stage ps;

    public static Stage getPs() {
        return ps;
    }

    public static void setPs(Stage pstg) {
        ps = pstg;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPs(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("FXML/sample.fxml"));
        primaryStage.setTitle("Elevator simulation");
        Screen screen = Screen.getPrimary();
        double sceneWidth = 1366.0;
        double sceneHeight = 728.0;
        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight));
        primaryStage.show();
        Logger.ConfigLogger();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
