package model;

import java.util.ArrayList;

public class IntegerPolynomial extends Polynomial<IntegerMonomial> {

    public IntegerPolynomial() {}

    public ArrayList<IntegerMonomial> getMonomialList() {
        return this.monomialList;
    }

    public void addMonomialToList(IntegerMonomial toAdd) {
        this.monomialList.add(toAdd);
    }

}
