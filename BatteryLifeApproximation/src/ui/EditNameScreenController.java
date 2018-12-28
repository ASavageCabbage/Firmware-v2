package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.SolarCar;

public class EditNameScreenController {

    @FXML
    private Label currentName = new Label();
    @FXML
    private TextField newName = new TextField();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private ImageView nameSpiel;

    private Stage stage;
    private MainScreenController mscontroller;
    private SolarCar solarCar;

    public void setStage(Stage stage) { this.stage = stage; }

    public void setMainScreen(MainScreenController msc) {
        mscontroller = msc;
        solarCar = mscontroller.getSolarCar();
        currentName.setText(solarCar.getName());
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);
        newName.textProperty().addListener((observable) -> {
            if (newName.getText().length() > 0) saveButton.setDisable(false);
            else saveButton.setDisable(true);
        });
    }

    @FXML
    private void handleSave() {
        solarCar.setName(newName.getText());
        SavingManager.saveSolarCar(solarCar);
        currentName.setText(solarCar.getName());
    }

    @FXML
    private void handleQuit() { mscontroller.quitSubStage(stage); }
}
