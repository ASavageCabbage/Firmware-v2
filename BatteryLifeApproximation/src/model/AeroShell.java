package model;

public class AeroShell {

    // Specification
    private double crossArea; // Cross-sectional surface area from front; units: Square metres
    private double dragCoefficient;
    private double aeroShellMass; // Units: Kilograms
    private double chassisMass; // Units: Kilograms

    // Getters and Setters

    public double getCrossArea() {
        return crossArea;
    }

    public void setCrossArea(double crossArea) {
        this.crossArea = crossArea;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public double getAeroShellMass() {
        return aeroShellMass;
    }

    public void setAeroShellMass(double aeroShellMass) {
        this.aeroShellMass = aeroShellMass;
    }

    public double getChassisMass() {
        return chassisMass;
    }

    public void setChassisMass(double chassisMass) {
        this.chassisMass = chassisMass;
    }
}
