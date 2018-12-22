package model;

public class PoweredComponent {

    // Description
    private String name;
    private double powerDraw; // Units: Watts
    private double mass; // Units: Kilograms

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPowerDraw() {
        return powerDraw;
    }

    public void setPowerDraw(double powerDraw) {
        this.powerDraw = powerDraw;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
