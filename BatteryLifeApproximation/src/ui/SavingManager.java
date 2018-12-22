package ui;

import model.SolarCar;

import java.io.File;

public class SavingManager {

    private File saveDirectory = new File("BatteryLifeSaveData");

    public static SolarCar loadCar() {
        SolarCar car = new SolarCar();

        return car;
    }
}
