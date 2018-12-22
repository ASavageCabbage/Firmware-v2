package model;

public class Arrays {

    // Specifications
    private double baseCharging; // Base charging rate; units: Watts
    private double supplementalCharging; // Additional charging rate provided by supplemental arrays; units: Watts
    private double mass; // Units: Kilograms

    private boolean isSupplemented; // Are the supplemental arrays in use?

    // Getters and Setters

    public double getBaseCharging() {
        return baseCharging;
    }

    public void setBaseCharging(double baseCharging) {
        this.baseCharging = baseCharging;
    }

    public double getSupplementalCharging() {
        return supplementalCharging;
    }

    public void setSupplementalCharging(double supplementalCharging) {
        this.supplementalCharging = supplementalCharging;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public boolean isSupplemented() {
        return isSupplemented;
    }

    public void setSupplemented(boolean supplemented) {
        isSupplemented = supplemented;
    }
}
