package com.work.diploma;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Launcher extends Application {

    String resourcesChecker = "checkSection.fxml";
    String resourcesCalculator = "selectionSection.fxml";

    @FXML
    protected Button launchSectionChecker;
    @FXML
    protected Button launchSectionCalculator;

    @Override
    public void start(Stage mainStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("Main Menu");
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    protected void onLaunchCheckerClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesChecker));
            Parent anotherRoot = loader.load();
            Stage checkerStage = new Stage();
            checkerStage.setTitle("Check Section");
            checkerStage.setScene(new Scene(anotherRoot));
            checkerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLaunchCalculatorClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesCalculator));
            Parent anotherRoot = loader.load();
            Stage gameStage = new Stage();
            gameStage.setTitle("Calculate Section");
            gameStage.setScene(new Scene(anotherRoot));
            gameStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
