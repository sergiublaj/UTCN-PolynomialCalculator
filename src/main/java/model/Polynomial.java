package model;

import java.util.ArrayList;

public class Polynomial {
    private final ArrayList<Monomial> monomialList = new ArrayList<>();

    public Polynomial() {
    }

    public ArrayList<Monomial> getMonomialList() {
        return monomialList;
    }

    public void addMonomialToList(Monomial toAdd) {
        this.monomialList.add(toAdd);
    }
}
