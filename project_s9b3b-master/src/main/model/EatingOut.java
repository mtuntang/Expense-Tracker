package model;

import java.io.Serializable;

public class EatingOut extends Entertainment implements Serializable {

    public EatingOut(double amount, String desc) {
        super(amount, desc);
    }


    /*private List<Boba> bobas;

    public void addBoba(Boba boba) {
        if (!bobas.contains(boba)) {
            bobas.add(boba);
            boba.addEating(this);
        }
    }*/
}