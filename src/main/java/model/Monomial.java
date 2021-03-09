package model;

public class Monomial {
    private double coefficient;
    private double exponent;

    public Monomial() {
        this.coefficient = 0;
        this.exponent = 0;
    }

    public Monomial(double coefficient, double exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }
}
