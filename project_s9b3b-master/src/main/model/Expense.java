package model;

import java.io.Serializable;

public class Expense implements Serializable {
    private double moneyOut;
    private String description;

    public Expense(double amount, String descr) {
        this.moneyOut = amount;
        this.description = descr;
    }

    public double getMoneyOut() {
        return moneyOut;
    }

    public String getDescription() {
        return description;
    }


}
