package model.monomial;

public class IntegerMonomial extends Monomial<Integer> {

    public IntegerMonomial() {
        super();
    }

    public IntegerMonomial(Integer coefficient, Integer exponent) {
        super(coefficient, exponent);
    }

    public Integer getCoefficient() {
        return super.getCoefficient();
    }

    public void setCoefficient(Integer coefficient) {
        super.setCoefficient(coefficient);
    }

    public Integer getExponent() {
        return super.getExponent();
    }

    public void setExponent(Integer exponent) {
        super.setExponent(exponent);
    }
}
