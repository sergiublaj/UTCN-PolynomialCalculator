package model;

import java.util.ArrayList;

public abstract class Polynomial<T> {
    protected ArrayList<T> monomialList = new ArrayList<>();

    public Polynomial() {
    }

    public ArrayList<T> getMonomialList() {
        return this.monomialList;
    }

    public void addMonomialToList(T toAdd) {
        this.monomialList.add(toAdd);
    }
}
