package model;

public class Model {

    private Polynomial firstOperand;
    private Polynomial secondOperand;
    private Polynomial resultTerm;
    private Polynomial remainderTerm;

    public Model() {
        resultTerm = new Polynomial();
    }

    public void addPolynomials() {
        this.swapOperandsIfNeeded();
        this.clearResultTerm();
        this.chekForNullOperands();
        for (Monomial secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(secondIt);
        }
        PolynomialInterpreter.sortByExponents(resultTerm);
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    public void subtractPolynomials() {
        this.swapOperandsIfNeeded();
        this.clearResultTerm();
        this.chekForNullOperands();
        for (Monomial secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(new Monomial(-secondIt.getCoefficient(), secondIt.getExponent()));
        }
        PolynomialInterpreter.sortByExponents(resultTerm);
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    private void chekForNullOperands() {
        if (firstOperand.getMonomialList().isEmpty()) {
            resultTerm = secondOperand;
            return;
        } else if (secondOperand.getMonomialList().isEmpty()) {
            resultTerm = firstOperand;
            return;
        }
        resultTerm = firstOperand;
    }

    public void multiplyPolynomials() {
        this.clearResultTerm();
        for (Monomial firstTerm : firstOperand.getMonomialList()) {
            for (Monomial secondTerm : secondOperand.getMonomialList()) {
                Monomial result = new Monomial();
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
        remainderTerm = firstOperand;
        while (!remainderTerm.getMonomialList().isEmpty() && remainderTerm.getMonomialList().get(0).getExponent() >= secondOperand.getMonomialList().get(0).getExponent()) {
            Monomial divisionRes = new Monomial();
            divisionRes.setCoefficient(remainderTerm.getMonomialList().get(0).getCoefficient() / secondOperand.getMonomialList().get(0).getCoefficient());
            divisionRes.setExponent(remainderTerm.getMonomialList().get(0).getExponent() - secondOperand.getMonomialList().get(0).getExponent());
            resultTerm.addMonomialToList(divisionRes);
            Polynomial multiplicationRes = new Polynomial();
            for (Monomial secondIt : secondOperand.getMonomialList()) {
                Monomial result = new Monomial();
                result.setCoefficient(secondIt.getCoefficient() * divisionRes.getCoefficient());
                result.setExponent(secondIt.getExponent() + divisionRes.getExponent());
                multiplicationRes.addMonomialToList(result);
            }
            PolynomialInterpreter.sortByExponents(multiplicationRes);
            PolynomialInterpreter.reduceCoefficients(multiplicationRes);
            Polynomial subtractionRes = remainderTerm;
            for (Monomial multiplyIt : multiplicationRes.getMonomialList()) {
                subtractionRes.addMonomialToList(new Monomial(-multiplyIt.getCoefficient(), multiplyIt.getExponent()));
            }
            PolynomialInterpreter.sortByExponents(subtractionRes);
            PolynomialInterpreter.reduceCoefficients(subtractionRes);
            remainderTerm = subtractionRes;
        }
    }

    public void derivePolynomial() {
        this.clearResultTerm();
        for (Monomial mIterator : firstOperand.getMonomialList()) {
            Monomial result = new Monomial();
            result.setCoefficient(mIterator.getCoefficient() * mIterator.getExponent());
            result.setExponent(mIterator.getExponent() - 1);
            resultTerm.addMonomialToList(result);
        }
        PolynomialInterpreter.reduceCoefficients(resultTerm);
    }

    public void integratePolynomial() {
        this.clearResultTerm();
        for (Monomial mIterator : firstOperand.getMonomialList()) {
            Monomial result = new Monomial();
            result.setCoefficient(mIterator.getCoefficient() / (mIterator.getExponent() + 1));
            result.setExponent(mIterator.getExponent() + 1);
            resultTerm.addMonomialToList(result);
        }
    }

    private void swapOperandsIfNeeded() {
        if (this.firstOperandHasLowerDegree()) {
            Polynomial temp = firstOperand;
            firstOperand = secondOperand;
            secondOperand = temp;
        }
    }

    private boolean firstOperandHasLowerDegree() {
        return firstOperand.getMonomialList().get(0).getExponent() < secondOperand.getMonomialList().get(0).getExponent();
    }

    public void setFirstOperand(Polynomial newPolynomial) {
        this.firstOperand = newPolynomial;
    }

    public void setSecondOperand(Polynomial newPolynomial) {
        this.secondOperand = newPolynomial;
    }

    public void clearResultTerm() {
        this.resultTerm = new Polynomial();
    }

    public Polynomial getResultTerm() {
        return resultTerm;
    }

    public void clearRemainderTerm() {
        this.remainderTerm = new Polynomial();
    }

    public Polynomial getRemainderTerm() {
        return remainderTerm;
    }
}
