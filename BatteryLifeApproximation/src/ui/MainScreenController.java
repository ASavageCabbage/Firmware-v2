package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SolarCar;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainScreenController {

    @FXML
    private Label name = new Label();
    @FXML
    private Label totalMass = new Label();
    @FXML
    private Label topSpeed = new Label();
    @FXML
    private Label batteryLife = new Label();

    @FXML
    private Button toSimulator = new Button();
    @FXML
    private Label errorMessage = new Label();

    @FXML
    private ImageView poster = new ImageView();
    private File posterImage = new File("SolarLogo.png");

    private Main mainApp;
    private SolarCar solarCar;

    public MainScreenController() {
        solarCar = SavingManager.loadCar();
    }

    public SolarCar getSolarCar() {
        return solarCar;
    }

    @FXML
    public void initialize() {
        try {
            poster.setImage(new Image(posterImage.toURI().toURL().toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        resetLabels();
        showCarDetails();
    }

    public void showCarDetails() {
        name.setText(solarCar.getName());
        totalMass.setText(Integer.toString((((Double) solarCar.getTotalMass()).intValue())));
        topSpeed.setText(Integer.toString((((Double) solarCar.getMaxSpeed()).intValue())));
        batteryLife.setText(Integer.toString((((Double) solarCar.getBatteryLife()).intValue())));
    }

    private void resetLabels() {
        name.setText("");
        totalMass.setText("");
        topSpeed.setText("");
        batteryLife.setText("");
        errorMessage.setText("");

        handleSimulatorButton();
    }

    private void handleSimulatorButton() {
        if (solarCar.isValid()) toSimulator.setDisable(false);
        else {
            toSimulator.setDisable(true);
            errorMessage.setText(solarCar.errorMessage());
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void showEditNameScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ui/EditNameScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Rename Solar Car");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setResizable(false);

            EditNameScreenController controller = loader.getController();
            controller.setStage(stage);
            controller.setMainScreen(this);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showEditAeroshellScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ui/EditAeroShellScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Edit Aeroshell/Chassis Specification");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setResizable(false);

            EditAeroShellScreenController controller = loader.getController();
            controller.setStage(stage);
            controller.setMainScreen(this);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showEditWheelsScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/ui/EditWheelsScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Edit Wheels Specification");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            stage.setScene(scene);
            stage.setResizable(false);

            EditWheelsScreenController controller = loader.getController();
            controller.setStage(stage);
            controller.setMainScreen(this);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleQuit() {
        mainApp.getPrimaryStage().close();
    }

    public void quitSubStage(Stage stage) {
        showCarDetails();
        stage.close();
    }
}
