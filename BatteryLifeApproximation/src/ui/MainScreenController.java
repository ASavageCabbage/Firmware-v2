package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.SolarCar;

import java.io.File;
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

    private void showCarDetails() {
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
}
