package model;

import java.util.Comparator;

public class DescendingExponentsSort implements Comparator<Monomial<? extends Number>> {

    @Override
    public int compare(Monomial<?> o1, Monomial<?> o2) {
        return o2.getExponent().intValue() - o1.getExponent().intValue();
    }
}
