package validator;

import model.monomial.DoubleMonomial;
import model.polynomial.DoublePolynomial;
import model.polynomial.IntegerPolynomial;

import java.util.ArrayList;

public class PolynomialEvaluator {
    public static void sortByExponents(DoublePolynomial doublePolynomial) {
        DescendingExponentsSort sortExponents = new DescendingExponentsSort();
        doublePolynomial.getMonomialList().sort(sortExponents);
    }

    public static void sortByExponents(IntegerPolynomial intPolynomial) {
        DescendingExponentsSort sortExponents = new DescendingExponentsSort();
        intPolynomial.getMonomialList().sort(sortExponents);
    }

    // Mentinem un pointer in urma elementului curent
    // Daca cei doi au acelasi exponent, adunam coeficientii la elementul curent
    // La final, stergem reziduurile
    public static void reduceCoefficients(DoublePolynomial crtPolynomial) {
        DoubleMonomial mFlag = new DoubleMonomial();
        ArrayList<DoubleMonomial> toRemove = new ArrayList<>();
        for (DoubleMonomial mIterator : crtPolynomial.getMonomialList()) {
            if (mIterator.getExponent().equals(mFlag.getExponent())) {
                mIterator.setCoefficient(mIterator.getCoefficient() + mFlag.getCoefficient());
                toRemove.add(mFlag);
            }
            if (mIterator.getCoefficient() == 0) {
                toRemove.add(mIterator);
            }
            mFlag = mIterator;
        }
        crtPolynomial.getMonomialList().removeAll(toRemove);
    }
}
