package model;

public abstract class Monomial<N extends Number> {
    private N coefficient;
    private N exponent;

    public Monomial() {
    }

    public Monomial(N coefficient, N power) {
        this.coefficient = coefficient;
        this.exponent = power;
    }

    public N getCoefficient() {
        return this.coefficient;
    }

    public void setCoefficient(N coefficient) {
        this.coefficient = coefficient;
    }

    public N getExponent() {
        return this.exponent;
    }

    public void setExponent(N power) {
        this.exponent = power;
    }
}
