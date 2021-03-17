package model;

import controller.listeners.*;
import model.monomial.DoubleMonomial;
import model.monomial.IntegerMonomial;
import model.polynomial.DoublePolynomial;
import model.polynomial.IntegerPolynomial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private final Model appModel = new Model();
    private IntegerPolynomial firstOperand;
    private IntegerPolynomial secondOperand;
    private DoublePolynomial resultTerm;
    private DoublePolynomial remainderTerm;

    @BeforeEach
    void setUp() {
        firstOperand = new IntegerPolynomial();
        secondOperand = new IntegerPolynomial();
        resultTerm = new DoublePolynomial();
        remainderTerm = new DoublePolynomial();
    }

    @Test
    void addPolynomials() {
        firstOperand.addMonomialToList(new IntegerMonomial(2, 3));
        firstOperand.addMonomialToList(new IntegerMonomial(1, 2));
        firstOperand.addMonomialToList(new IntegerMonomial(3, 0));
        appModel.setFirstOperand(firstOperand);

        secondOperand.addMonomialToList(new IntegerMonomial(1, 3));
        secondOperand.addMonomialToList(new IntegerMonomial(2, 2));
        secondOperand.addMonomialToList(new IntegerMonomial(4, 0));
        appModel.setSecondOperand(secondOperand);

        appModel.addPolynomials();
        resultTerm = appModel.getResultTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(3, 3));
        expectedResult.addMonomialToList(new DoubleMonomial(3, 2));
        expectedResult.addMonomialToList(new DoubleMonomial(7, 0));
        assertTrue(resultTerm.equalsPolynomial(expectedResult), AdditionListener.ADDITION_FAIL);
    }

    @Test
    void subtractPolynomials() {
        firstOperand.addMonomialToList(new IntegerMonomial(2, 4));
        firstOperand.addMonomialToList(new IntegerMonomial(5, 1));
        firstOperand.addMonomialToList(new IntegerMonomial(3, 0));
        appModel.setFirstOperand(firstOperand);

        secondOperand.addMonomialToList(new IntegerMonomial(4, 3));
        secondOperand.addMonomialToList(new IntegerMonomial(2, 2));
        secondOperand.addMonomialToList(new IntegerMonomial(5, 0));
        appModel.setSecondOperand(secondOperand);

        appModel.subtractPolynomials();
        resultTerm = appModel.getResultTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(2, 4));
        expectedResult.addMonomialToList(new DoubleMonomial(-4, 3));
        expectedResult.addMonomialToList(new DoubleMonomial(-2, 2));
        expectedResult.addMonomialToList(new DoubleMonomial(5, 1));
        expectedResult.addMonomialToList(new DoubleMonomial(-2, 0));
        assertTrue(resultTerm.equalsPolynomial(expectedResult), SubtractionListener.SUBTRACTION_FAIL);
    }

    @Test
    void multiplyPolynomials() {
        firstOperand.addMonomialToList(new IntegerMonomial(1, 4));
        firstOperand.addMonomialToList(new IntegerMonomial(2, 1));
        firstOperand.addMonomialToList(new IntegerMonomial(2, 0));
        appModel.setFirstOperand(firstOperand);

        secondOperand.addMonomialToList(new IntegerMonomial(1, 3));
        secondOperand.addMonomialToList(new IntegerMonomial(1, 0));
        appModel.setSecondOperand(secondOperand);

        appModel.multiplyPolynomials();
        resultTerm = appModel.getResultTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(1, 7));
        expectedResult.addMonomialToList(new DoubleMonomial(3, 4));
        expectedResult.addMonomialToList(new DoubleMonomial(2, 3));
        expectedResult.addMonomialToList(new DoubleMonomial(2, 1));
        expectedResult.addMonomialToList(new DoubleMonomial(2, 0));
        assertTrue(resultTerm.equalsPolynomial(expectedResult), MultiplicationListener.MULTIPLICATION_FAIL);
    }

    @Test
    void dividePolynomials() {
        firstOperand.addMonomialToList(new IntegerMonomial(1, 3));
        firstOperand.addMonomialToList(new IntegerMonomial(1, 1));
        firstOperand.addMonomialToList(new IntegerMonomial(5, 0));
        appModel.setFirstOperand(firstOperand);

        secondOperand.addMonomialToList(new IntegerMonomial(1, 2));
        secondOperand.addMonomialToList(new IntegerMonomial(1, 1));
        secondOperand.addMonomialToList(new IntegerMonomial(-3, 0));
        appModel.setSecondOperand(secondOperand);

        appModel.dividePolynomials();
        resultTerm = appModel.getResultTerm();
        remainderTerm = appModel.getRemainderTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(1, 1));
        expectedResult.addMonomialToList(new DoubleMonomial(-1, 0));

        DoublePolynomial expectedRemainder = new DoublePolynomial();
        expectedRemainder.addMonomialToList(new DoubleMonomial(5, 1));
        expectedRemainder.addMonomialToList(new DoubleMonomial(2, 0));
        assertTrue(resultTerm.equalsPolynomial(expectedResult) && remainderTerm.equalsPolynomial(expectedRemainder), DivisionListener.DIVISION_FAIL);
    }

    @Test
    void derivePolynomial() {
        firstOperand.addMonomialToList(new IntegerMonomial(7, 4));
        firstOperand.addMonomialToList(new IntegerMonomial(2, 1));
        firstOperand.addMonomialToList(new IntegerMonomial(3, 0));
        appModel.setFirstOperand(firstOperand);

        appModel.derivePolynomial();
        resultTerm = appModel.getResultTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(28, 3));
        expectedResult.addMonomialToList(new DoubleMonomial(2, 0));
        assertTrue(resultTerm.equalsPolynomial(expectedResult), DerivationListener.DERIVATION_FAIL);
    }

    @Test
    void integratePolynomial() {
        firstOperand.addMonomialToList(new IntegerMonomial(3, 2));
        firstOperand.addMonomialToList(new IntegerMonomial(4, 1));
        firstOperand.addMonomialToList(new IntegerMonomial(3, 0));
        appModel.setFirstOperand(firstOperand);

        appModel.integratePolynomial();
        resultTerm = appModel.getResultTerm();
        DoublePolynomial expectedResult = new DoublePolynomial();
        expectedResult.addMonomialToList(new DoubleMonomial(1, 3));
        expectedResult.addMonomialToList(new DoubleMonomial(2, 2));
        expectedResult.addMonomialToList(new DoubleMonomial(3, 1));
        assertTrue(resultTerm.equalsPolynomial(expectedResult), IntegrationListener.INTEGRATION_FAIL);
    }
}