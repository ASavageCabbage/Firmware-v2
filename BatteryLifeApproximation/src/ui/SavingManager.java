package ui;

import com.sun.istack.internal.NotNull;
import model.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

// The methods in this class have been manually coupled to the order of fields as declared in the individual class code.
// As such, any additions (ex. adding new fields) should be done strictly sequentially to avoid bugs. If anyone has a
// way to weaken the coupling so changes are automatically reflected, please send me suggestions.

public class SavingManager {

    private static final String SOLAR_CAR = getClassName(new SolarCar());
    private static final String AEROSHELL = getClassName(new AeroShell());
    private static final String ARRAYS = getClassName(new Arrays());
    private static final String BATTERY = getClassName(new Battery());
    private static final String MOTOR = getClassName(new Motor());
    private static final String WHEEL = getClassName(new Wheel());
    private static final String POWERED_COMPONENT = getClassName(new PoweredComponent());

    private static File saveDirectory = new File("BatteryLifeSaveData");

    // Methods for loading from saveDirectory

    // REQUIRES: saveDirectory is a valid directory
    // EFFECTS: Generates a complete SolarCar instance based on files in SaveDirectory
    public static SolarCar loadCar() {
        SolarCar car = new SolarCar();
        File[] files = saveDirectory.listFiles();
        for (File f: files) {
            String type = getFileName(f);
            if (type.equals(SOLAR_CAR)) loadCar(f, car);
            else if (type.equals(AEROSHELL)) car.setAeroShell(loadAeroShell(f));
            else if (type.equals(ARRAYS)) car.setArrays(loadArrays(f));
            else if (type.equals(BATTERY)) car.setBattery(loadBattery(f));
            else if (type.equals(MOTOR)) car.setMotor(loadMotor(f));
            else if (type.equals(WHEEL)) car.setWheels(loadWheels(f));
            else if (type.equals(POWERED_COMPONENT)) car.setComponents(loadComponents(f));
        }
        return car;
    }

    // REQUIRES: file contains at least one line
    // EFFECTS: Sets car name based on SolarCar save file
    public static void loadCar(@NotNull File file, @NotNull SolarCar car) {
        List<String> fileContents = readFile(file);
        car.setName(fileContents.iterator().next());
    }

    // REQUIRES: file contains at least 4 lines of strings parseable to double
    // EFFECTS: Generates an AeroShell instance with specification based on save file
    public static AeroShell loadAeroShell(@NotNull File file) {
        AeroShell as = new AeroShell();
        List<String> fileContents = readFile(file);
        as.setCrossArea(Double.parseDouble(fileContents.iterator().next()));
        as.setDragCoefficient(Double.parseDouble(fileContents.iterator().next()));
        as.setAeroShellMass(Double.parseDouble(fileContents.iterator().next()));
        as.setChassisMass(Double.parseDouble(fileContents.iterator().next()));
        return as;
    }

    // REQUIRES: file contains at least 3 lines of strings parseable to double
    // EFFECTS: Generates an Arrays instance with specification based on save file
    public static Arrays loadArrays(@NotNull File file) {
        Arrays arr = new Arrays();
        List<String> fileContents = readFile(file);
        arr.setBaseCharging(Double.parseDouble(fileContents.iterator().next()));
        arr.setSupplementalCharging(Double.parseDouble(fileContents.iterator().next()));
        arr.setMass(Double.parseDouble(fileContents.iterator().next()));
        return arr;
    }

    // REQUIRES: file contains at least 4 lines of strings parseable to double
    // EFFECTS: Generates a Battery instance with specification based on save file
    public static Battery loadBattery(@NotNull File file) {
        Battery bat = new Battery();
        List<String> fileContents = readFile(file);
        bat.setMaxCurrent(Double.parseDouble(fileContents.iterator().next()));
        bat.setOutVoltage(Double.parseDouble(fileContents.iterator().next()));
        bat.setCapacity(Double.parseDouble(fileContents.iterator().next()));
        bat.setMass(Double.parseDouble(fileContents.iterator().next()));
        return bat;
    }

    // REQUIRES: file contains at least 6 lines of strings parseable to double
    // EFFECTS: Generates a Motor instance with specification based on save file
    public static Motor loadMotor(@NotNull File file) {
        Motor motor = new Motor();
        List<String> fileContents = readFile(file);
        motor.setNominalVoltage(Double.parseDouble(fileContents.iterator().next()));
        motor.setPeakPower(Double.parseDouble(fileContents.iterator().next()));
        motor.setContinuousPower(Double.parseDouble(fileContents.iterator().next()));
        motor.setNoLoadSpeed(Double.parseDouble(fileContents.iterator().next()));
        motor.setPeakTorque(Double.parseDouble(fileContents.iterator().next()));
        motor.setMass(Double.parseDouble(fileContents.iterator().next()));
        return motor;
    }

    // REQUIRES: file contains at least 2 lines of strings parseable to double
    // EFFECTS: Generates a Wheel instance with specification based on save file
    public static Wheel loadWheels(@NotNull File file) {
        Wheel wh = new Wheel();
        List<String> fileContents = readFile(file);
        wh.setRadius(Double.parseDouble(fileContents.iterator().next()));
        wh.setMass(Double.parseDouble(fileContents.iterator().next()));
        return wh;
    }

    // REQUIRES: Number of lines in file is divisible by 3 (string, double, double)
    // EFFECTS: Generates a list of PoweredComponent with individual specification based on save file
    public static List<PoweredComponent> loadComponents(@NotNull File file) {
        List<PoweredComponent> components = new LinkedList<>();
        List<String> fileContents = readFile(file);
        while (fileContents.iterator().hasNext()) {
            PoweredComponent c = new PoweredComponent();
            c.setName(fileContents.iterator().next());
            c.setPowerDraw(Double.parseDouble(fileContents.iterator().next()));
            c.setMass(Double.parseDouble(fileContents.iterator().next()));
            components.add(c);
        }
        return components;
    }

    // Methods for saving to saveDirectory. Note that all saving of field data is done in the same order as they are declared in the class code.

    // EFFECTS: Saves primitive car fields in text file and saves subcomponent specifications in their own files.
    public static void saveCar(@NotNull SolarCar car) {
        File solarCar = makeSaveFile(car);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(solarCar));
            writer.write(car.getName());
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(car.getDriverMass()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveAeroShell(car.getAeroShell());
        saveArrays(car.getArrays());
        saveBattery(car.getBattery());
        saveMotor(car.getMotor());
        saveComponents(car.getComponents());
        saveWheels(car.getWheels());
    }

    // EFFECTS: Saves specification of a particular AeroShell instance
    public static void saveAeroShell(@NotNull AeroShell shell) {
        File aeroShell = makeSaveFile(shell);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(aeroShell));
            writer.write(Double.toString(shell.getCrossArea()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(shell.getDragCoefficient()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(shell.getAeroShellMass()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(shell.getChassisMass()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves specification of a particular Arrays instance
    public static void saveArrays(@NotNull Arrays arr) {
        File arrays = makeSaveFile(arr);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(arrays));
            writer.write(Double.toString(arr.getBaseCharging()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(arr.getSupplementalCharging()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(arr.getMass()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves specification of a particular Battery instance
    public static void saveBattery(@NotNull Battery bat) {
        File battery = makeSaveFile(bat);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(battery));
            writer.write(Double.toString(bat.getMaxCurrent()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(bat.getOutVoltage()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(bat.getCapacity()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(bat.getMass()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves specification of a particular Motor instance
    public static void saveMotor(@NotNull Motor mo) {
        File motor = makeSaveFile(mo);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(motor));
            writer.write(Double.toString(mo.getNominalVoltage()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(mo.getPeakPower()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(mo.getContinuousPower()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(mo.getNoLoadSpeed()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(mo.getPeakTorque()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(mo.getMass()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves specification of a particular List of PoweredComponent
    public static void saveComponents(@NotNull List<PoweredComponent> components) {
        if (components.isEmpty()) return;
        File poweredComponent = makeSaveFile(components.get(0));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(poweredComponent));
            for (PoweredComponent c: components) {
                writer.write(c.getName());
                writer.newLine();
                writer.flush();
                writer.write(Double.toString(c.getPowerDraw()));
                writer.newLine();
                writer.flush();
                writer.write(Double.toString(c.getMass()));
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Saves specification of a particular Wheel instance
    public static void saveWheels(@NotNull Wheel wheel) {
        File wheels = makeSaveFile(wheel);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(wheels));
            writer.write(Double.toString(wheel.getRadius()));
            writer.newLine();
            writer.flush();
            writer.write(Double.toString(wheel.getMass()));
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper methods

    // EFFECTS: Returns name of file truncating the filetype extension
    public static String getFileName(@NotNull File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        name = name.substring(0, index);
        return name;
    }

    // EFFECTS: Returns the class name of the argument (without the full path)
    public static String getClassName(@NotNull Object o) {
        String name = o.getClass().toString();
        int index = name.lastIndexOf(("."));
        name = name.substring(index + 1, name.length());
        return name;
    }

    // REQUIRES: file is not null
    // EFFECTS: Returns a List of String of the lines contained within the file
    public static List<String> readFile(@NotNull File file) {
        List<String> contents = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getName()));
            String line;
            while ((line = reader.readLine())!= null) contents.add(line.trim());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    // EFFECTS: Makes a new file in saveDirectory with name matching toSave class name
    public static File makeSaveFile(@NotNull Object toSave) {
        return new File(saveDirectory.getPath() + "/" + getClassName(toSave));
    }
}
