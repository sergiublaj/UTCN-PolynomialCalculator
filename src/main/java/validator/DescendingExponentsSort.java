package validator;

import model.monomial.Monomial;

import java.util.Comparator;

public class DescendingExponentsSort implements Comparator<Monomial<? extends Number>> {

    // sortam in ordinea inversa a exponentilor,
    // aducand polinomul la forma ax^n + bx^n-1 ... + z
    @Override
    public int compare(Monomial<?> firstMonomial, Monomial<?> secondMonomial) {
        return secondMonomial.getExponent().intValue() - firstMonomial.getExponent().intValue();
    }
}
