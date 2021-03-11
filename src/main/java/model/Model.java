package model;

public class Model {

    private IntegerPolynomial firstOperand;
    private IntegerPolynomial secondOperand;
    private DoublePolynomial resultTerm;
    private DoublePolynomial remainderTerm;

    public Model() {
        resultTerm = new DoublePolynomial();
    }

    public void addPolynomials() {
        this.swapOperandsIfNeeded();
        this.clearResultTerm();
        this.chekForNullOperands();
        for (Monomial<Integer> secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(new DoubleMonomial(secondIt));
        }
        PolynomialInterpreter.sortByExponents(resultTerm);
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    public void subtractPolynomials() {
        this.swapOperandsIfNeeded();
        this.clearResultTerm();
        this.chekForNullOperands();
        for (Monomial<Integer> secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(new DoubleMonomial(-secondIt.getCoefficient(), secondIt.getExponent()));
        }
        PolynomialInterpreter.sortByExponents(resultTerm);
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    private void chekForNullOperands() {
        if (firstOperand.getMonomialList().isEmpty()) {
            resultTerm = new DoublePolynomial(secondOperand);
            return;
        } else if (secondOperand.getMonomialList().isEmpty()) {
            resultTerm = new DoublePolynomial(firstOperand);
            return;
        }
        resultTerm = new DoublePolynomial(firstOperand);
    }

    public void multiplyPolynomials() {
        this.clearResultTerm();
        for(Monomial<Integer> firstTerm : firstOperand.getMonomialList()) {
            for (Monomial<Integer> secondTerm : secondOperand.getMonomialList()) {
                IntegerMonomial result = new IntegerMonomial();
                result.setCoefficient(firstTerm.getCoefficient() * secondTerm.getCoefficient());
                result.setExponent(firstTerm.getExponent() + secondTerm.getExponent());
                resultTerm.addMonomialToList(result);
            }
        }
        PolynomialInterpreter.sortByExponents(resultTerm);
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    public void dividePolynomials() {
        this.clearResultTerm();
        remainderTerm = new DoublePolynomial(firstOperand);
        while (!remainderTerm.getMonomialList().isEmpty() && remainderTerm.getMonomialList().get(0).getExponent() >= secondOperand.getMonomialList().get(0).getExponent()) {
            DoubleMonomial divisionRes = new DoubleMonomial();
            divisionRes.setCoefficient(remainderTerm.getMonomialList().get(0).getCoefficient() / secondOperand.getMonomialList().get(0).getCoefficient());
            divisionRes.setExponent(remainderTerm.getMonomialList().get(0).getExponent() - secondOperand.getMonomialList().get(0).getExponent());
            resultTerm.addMonomialToList(divisionRes);
            DoublePolynomial multiplicationRes = new DoublePolynomial();
            for (Monomial<Integer> secondIt : secondOperand.getMonomialList()) {
                DoubleMonomial result = new DoubleMonomial();
                result.setCoefficient(secondIt.getCoefficient() * divisionRes.getCoefficient());
                result.setExponent(secondIt.getExponent() + divisionRes.getExponent());
                multiplicationRes.addMonomialToList(result);
            }
            PolynomialInterpreter.sortByExponents(multiplicationRes);
            PolynomialInterpreter.reduceCoefficients(multiplicationRes);
            DoublePolynomial subtractionRes = remainderTerm;
            for (Monomial<Double> multiplyIt : multiplicationRes.getMonomialList()) {
                subtractionRes.addMonomialToList(new DoubleMonomial(-multiplyIt.getCoefficient(), multiplyIt.getExponent()));
            }
            PolynomialInterpreter.sortByExponents(subtractionRes);
            PolynomialInterpreter.reduceCoefficients(subtractionRes);
            remainderTerm = subtractionRes;
        }
    }

    public void derivePolynomial() {
        this.clearResultTerm();
        resultTerm = new DoublePolynomial();
        for (Monomial<Integer> mIterator : firstOperand.getMonomialList()) {
            IntegerMonomial result = new IntegerMonomial();
            result.setCoefficient(mIterator.getCoefficient() * mIterator.getExponent());
            result.setExponent(mIterator.getExponent() - 1);
            resultTerm.addMonomialToList(result);
        }
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    public void integratePolynomial() {
        this.clearResultTerm();
        resultTerm = new DoublePolynomial();
        for (Monomial<Integer> mIterator : firstOperand.getMonomialList()) {
            DoubleMonomial result = new DoubleMonomial();
            result.setCoefficient(mIterator.getCoefficient() * 1.0d / (mIterator.getExponent() + 1));
            result.setExponent(mIterator.getExponent() + 1.0d);
            resultTerm.addMonomialToList(result);
        }
    }

    private void swapOperandsIfNeeded() {
        if (this.firstOperandHasLowerDegree()) {
            IntegerPolynomial temp = firstOperand;
            firstOperand = secondOperand;
            secondOperand = temp;
        }
    }

    private boolean firstOperandHasLowerDegree() {
        return firstOperand.getMonomialList().get(0).getExponent() < secondOperand.getMonomialList().get(0).getExponent();
    }

    public void setFirstOperand(IntegerPolynomial newPolynomial) {
        this.firstOperand = newPolynomial;
    }

    public void setSecondOperand(IntegerPolynomial newPolynomial) {
        this.secondOperand = newPolynomial;
    }

    public void clearResultTerm() {
        this.resultTerm = new DoublePolynomial();
    }

    public DoublePolynomial getResultTerm() {
        return resultTerm;
    }

    public void clearRemainderTerm() {
        this.remainderTerm = new DoublePolynomial();
    }

    public DoublePolynomial getRemainderTerm() {
        return remainderTerm;
    }
}
