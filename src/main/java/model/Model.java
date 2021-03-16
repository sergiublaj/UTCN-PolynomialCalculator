package model;

import model.monomial.DoubleMonomial;
import model.monomial.IntegerMonomial;
import model.polynomial.DoublePolynomial;
import model.polynomial.IntegerPolynomial;
import validator.PolynomialEvaluator;

public class Model {

    private IntegerPolynomial firstOperand;
    private IntegerPolynomial secondOperand;
    private DoublePolynomial resultTerm;
    private DoublePolynomial remainderTerm;

    public Model() {
        this.clearResultTerm();
        this.clearRemainderTerm();
    }

    public void addPolynomials() {
        resultTerm = new DoublePolynomial(firstOperand);
        for (IntegerMonomial secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(new DoubleMonomial(secondIt.getCoefficient(), secondIt.getExponent()));
        }
        PolynomialEvaluator.sortByExponents(resultTerm);
        PolynomialEvaluator.reduceCoefficients(resultTerm);
    }

    public void subtractPolynomials() {
        resultTerm = new DoublePolynomial(firstOperand);
        for (IntegerMonomial secondIt : secondOperand.getMonomialList()) {
            resultTerm.addMonomialToList(new DoubleMonomial(-secondIt.getCoefficient(), secondIt.getExponent()));
        }
        PolynomialEvaluator.sortByExponents(resultTerm);
        PolynomialEvaluator.reduceCoefficients(resultTerm);
    }

    public void multiplyPolynomials() {
        this.clearResultTerm();
        for(IntegerMonomial firstTerm : firstOperand.getMonomialList()) {
            for (IntegerMonomial secondTerm : secondOperand.getMonomialList()) {
                IntegerMonomial result = new IntegerMonomial();
                result.setCoefficient(firstTerm.getCoefficient() * secondTerm.getCoefficient());
                result.setExponent(firstTerm.getExponent() + secondTerm.getExponent());
                resultTerm.addMonomialToList(result);
            }
        }
        PolynomialEvaluator.sortByExponents(resultTerm);
        PolynomialEvaluator.reduceCoefficients(resultTerm);
    }

    // Se imparte monomul cu gradul cel mai mare din primul termen (continut in rest)
    // La monomul cu gradul cel mai mare din al doilea termen
    // Monomul rezultat va fi adaugat la cat
    // Din primul termen (rest), vom scadea produsul dintre monomul rezultat si al doilea operand
    // Procesul continua pana cand deimpartitul va avea grad mai mare decat impartitorul
    // Restul va fi polinomul corespunzator primului termen care nu poate fi impartit la impartitor
    public void dividePolynomials() {
        this.clearResultTerm();
        remainderTerm = new DoublePolynomial(firstOperand);

        while (!remainderTerm.getMonomialList().isEmpty() && remainderTerm.getMonomialList().get(0).getExponent() >= secondOperand.getMonomialList().get(0).getExponent()) {
            DoubleMonomial divisionRes = new DoubleMonomial();
            divisionRes.setCoefficient(remainderTerm.getMonomialList().get(0).getCoefficient() / secondOperand.getMonomialList().get(0).getCoefficient());
            divisionRes.setExponent(remainderTerm.getMonomialList().get(0).getExponent() - secondOperand.getMonomialList().get(0).getExponent());
            resultTerm.addMonomialToList(divisionRes);

            DoublePolynomial multiplicationRes = new DoublePolynomial();
            for (IntegerMonomial secondIt : secondOperand.getMonomialList()) {
                DoubleMonomial result = new DoubleMonomial();
                result.setCoefficient(secondIt.getCoefficient() * divisionRes.getCoefficient());
                result.setExponent(secondIt.getExponent() + divisionRes.getExponent());
                multiplicationRes.addMonomialToList(result);
            }

            for (DoubleMonomial multiplyIt : multiplicationRes.getMonomialList()) {
                remainderTerm.addMonomialToList(new DoubleMonomial(-multiplyIt.getCoefficient(), multiplyIt.getExponent()));
            }
            PolynomialEvaluator.sortByExponents(remainderTerm);
            PolynomialEvaluator.reduceCoefficients(remainderTerm);
        }
    }

    public void derivePolynomial() {
        this.clearResultTerm();
        for (IntegerMonomial mIterator : firstOperand.getMonomialList()) {
            IntegerMonomial result = new IntegerMonomial();
            result.setCoefficient(mIterator.getCoefficient() * mIterator.getExponent());
            result.setExponent(mIterator.getExponent() - 1);
            resultTerm.addMonomialToList(result);
        }
        PolynomialEvaluator.reduceCoefficients(resultTerm);
    }

    public void integratePolynomial() {
        this.clearResultTerm();
        for (IntegerMonomial mIterator : firstOperand.getMonomialList()) {
            DoubleMonomial result = new DoubleMonomial();
            result.setCoefficient(mIterator.getCoefficient() * 1.0d / (mIterator.getExponent() + 1));
            result.setExponent(mIterator.getExponent() + 1.0d);
            resultTerm.addMonomialToList(result);
        }
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
