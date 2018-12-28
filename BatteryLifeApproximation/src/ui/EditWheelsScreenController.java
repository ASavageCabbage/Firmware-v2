package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Wheel;

public class EditWheelsScreenController {

    @FXML
    private Label radius = new Label();
    @FXML
    private Label mass = new Label();

    @FXML
    private TextField newRadius = new TextField();
    private double radiusValue;
    @FXML
    private TextField newMass = new TextField();
    private double massValue;

    @FXML
    private Button save = new Button();

    private Wheel wheel;
    private MainScreenController mscontroller;
    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

    public void setMainScreen(MainScreenController msc) {
        mscontroller = msc;
        setWheel(mscontroller.getSolarCar().getWheels());
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
        showWheelDetails();
    }

    private void showWheelDetails() {
        radius.setText(Double.toString(wheel.getRadius()));
        mass.setText(Double.toString(wheel.getMass()));
    }

    @FXML
    private void initialize() {
        save.setDisable(true);

        newRadius.textProperty().addListener((observable -> {
            if (isInputValid()) save.setDisable(false);
            else save.setDisable(true);
        }));
        newMass.textProperty().addListener((observable -> {
            if (isInputValid()) save.setDisable(false);
            else save.setDisable(true);
        }));
    }

    private boolean isInputValid() {
        String radius = newRadius.getText();
        String mass = newMass.getText();
        try {
            if (!radius.isEmpty() || !mass.isEmpty()) {
                if (!radius.isEmpty()) radiusValue = Double.parseDouble(radius);
                if (!mass.isEmpty()) massValue = Double.parseDouble(mass);
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void handleSave() {
        wheel.setRadius(radiusValue);
        wheel.setMass(massValue);
        SavingManager.saveWheels(wheel);
        showWheelDetails();
    }

    @FXML
    private void handleQuit() { mscontroller.quitSubStage(stage); }
}
