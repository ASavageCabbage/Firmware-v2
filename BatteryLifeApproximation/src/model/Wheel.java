package model;

public class Wheel {

    // Specifications
    private double radius; // Units: Metres
    private double mass; // Units: Kilograms

    // Getters and Setters

    public double getRadius() {
        return radius;
    }

    public double getCircumference() {
        return Math.PI * radius * radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
}
