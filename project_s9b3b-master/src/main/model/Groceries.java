package model;

import java.io.Serializable;

public class Groceries extends Expense implements Serializable {

    public Groceries(double amount, String desc) {
        super(amount, desc);
    }
}
