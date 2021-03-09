package model;

import java.util.Comparator;

public class DescendingExponentSort implements Comparator<Monomial> {
    public int compare(Monomial termOne, Monomial termTwo) {
        return (int)(termTwo.getExponent() - termOne.getExponent());
    }
}
