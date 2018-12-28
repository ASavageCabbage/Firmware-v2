package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AeroShell;

public class EditAeroShellScreenController {

    @FXML
    private Label crossArea = new Label();
    @FXML
    private Label dragCoeff = new Label();
    @FXML
    private Label aeroMass = new Label();
    @FXML
    private Label chassisMass = new Label();

    @FXML
    private TextField newCrossArea = new TextField();
    private double crossAreaValue;
    @FXML
    private TextField newDragCoeff = new TextField();
    private double dragCoeffValue;
    @FXML
    private TextField newAeroMass = new TextField();
    private double aeroMassValue;
    @FXML
    private TextField newChassisMass = new TextField();
    private double chassisMassValue;

    @FXML
    private Button saveButton = new Button();

    private MainScreenController mscontroller;
    private AeroShell aeroShell;
    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

    public void setMainScreen(MainScreenController msc) {
        mscontroller = msc;
        aeroShell = mscontroller.getSolarCar().getAeroShell();
        showAeroshellDetails();
    }

    private void showAeroshellDetails() {
        crossArea.setText(Double.toString(aeroShell.getCrossArea()));
        dragCoeff.setText(Double.toString(aeroShell.getDragCoefficient()));
        aeroMass.setText(Double.toString(aeroShell.getAeroShellMass()));
        chassisMass.setText(Double.toString(aeroShell.getChassisMass()));
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);
        newCrossArea.textProperty().addListener((observable) -> {
            if (isInputValid()) saveButton.setDisable(false);
            else saveButton.setDisable(true);
        });
        newDragCoeff.textProperty().addListener((observable) -> {
            if (isInputValid()) saveButton.setDisable(false);
            else saveButton.setDisable(true);
        });
        newAeroMass.textProperty().addListener((observable) -> {
            if (isInputValid()) saveButton.setDisable(false);
            else saveButton.setDisable(true);
        });
        newChassisMass.textProperty().addListener((observable) -> {
            if (isInputValid()) saveButton.setDisable(false);
            else saveButton.setDisable(true);
        });
    }

    private boolean isInputValid() {
        String crossArea = newCrossArea.getText();
        String dragCoeff = newDragCoeff.getText();
        String aeroMass = newAeroMass.getText();
        String chassisMass = newChassisMass.getText();
        try {
            if (!crossArea.isEmpty() || !dragCoeff.isEmpty() || !aeroMass.isEmpty() || !chassisMass.isEmpty()) {
                if (!crossArea.isEmpty()) crossAreaValue = Double.parseDouble(crossArea);
                if (!dragCoeff.isEmpty()) dragCoeffValue = Double.parseDouble(dragCoeff);
                if (!aeroMass.isEmpty()) aeroMassValue = Double.parseDouble(aeroMass);
                if (!chassisMass.isEmpty()) chassisMassValue = Double.parseDouble(chassisMass);
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void handleSave() {
        aeroShell.setCrossArea(crossAreaValue);
        aeroShell.setDragCoefficient(dragCoeffValue);
        aeroShell.setAeroShellMass(aeroMassValue);
        aeroShell.setChassisMass(chassisMassValue);
        SavingManager.saveAeroShell(aeroShell);
        showAeroshellDetails();
    }

    @FXML
    private void handleQuit() { mscontroller.quitSubStage(stage); }
}
