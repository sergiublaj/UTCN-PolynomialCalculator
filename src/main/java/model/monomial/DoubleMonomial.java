package model.monomial;

public class DoubleMonomial extends Monomial<Double> {

    public DoubleMonomial() {
        super();
    }

    public DoubleMonomial(Double coefficient, Double exponent) {
        super(coefficient, exponent);
    }

    public DoubleMonomial(Integer coefficient, Integer exponent) {
        super(coefficient.doubleValue(), exponent.doubleValue());
    }

    public Double getCoefficient() {
        return super.getCoefficient();
    }

    public void setCoefficient(Double coefficient) {
        super.setCoefficient(coefficient);
    }

    public Double getExponent() {
        return super.getExponent();
    }

    public void setExponent(Double exponent) {
        super.setExponent(exponent);
    }

    public boolean equalsMonomial(DoubleMonomial toEvaluate) {
        return this.getCoefficient().equals(toEvaluate.getCoefficient()) && this.getExponent().equals(toEvaluate.getExponent());
    }
}
