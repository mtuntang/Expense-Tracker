package model;

import java.io.*;
import java.util.ArrayList;

public class TotalExpenses implements Serializable {
    public ArrayList<Expense> listExpense = new ArrayList<>();

    public double totalGroceries = 0;

    //EFFECTS : gets total money spent on groceries
    public double getTotalGroceries() {
        double total = 0;
        for (Expense expense : listExpense) {
            if (expense instanceof Groceries) {
                total += expense.getMoneyOut();
            }
        }
        return total;
    }

    //EFFECTS : gets total money spent eating out
    public double getTotalEatingOut() {
        double total = 0;
        for (Expense expense : listExpense) {
            if (expense instanceof EatingOut) {
                total += expense.getMoneyOut();
            }
        }
        return total;
    }

    //EFFECTS : gets total money spent on random purchases
    public double getTotalRandomPurchases() {
        double total = 0;
        for (Expense expense : listExpense) {
            if (expense instanceof RandomPurchases) {
                total += expense.getMoneyOut();
            }
        }
        return total;
    }

    //EFFECTS : gets total money spent on entertainment (eating out + random purchases)
    public double getTotalEntertainment() {
        double total = getTotalRandomPurchases() + getTotalEatingOut();
        return total;
    }

    //EFFECTS : gets total money spent
    public double calculateTotal() {
        double total = getTotalGroceries() + getTotalEatingOut() + getTotalRandomPurchases();
        return total;
    }

    //REQUIRES : save.txt exists in model package
    //MODIFIES : save.txt
    //EFFECTS : overwrites save data
    public void save(TotalExpenses totalList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.dat"));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    //REQUIRES : save.txt exists in model package
    //EFFECTS : loads save data, makes a pointer to local TotalExpenses field
    public TotalExpenses load(TotalExpenses totalList) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.dat"));
            totalList = (TotalExpenses) in.readObject();
            in.close();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound Exception");
        } catch (IOException e) {
            return totalList;
        } finally {
            return totalList;
        }
    }

    //MODIFIES : this
    //EFFECTS : overwrites save data with empty list
    public void clearData() {
        listExpense = new ArrayList<>();
        save(this);
    }

 /*   public String compareExpensesWithBudget(String name, TotalExpenses totalExpenses) {
        Budget holder = budgetHashMap.get(name);
        if (holder.getAmount() > totalExpenses.calculateTotal()) {
            return "You are within budget! :) -> $" + (holder.getAmount() - totalExpenses.calculateTotal());
        } else {
            return "You are over budget! :( -> $" + (holder.getAmount() - totalExpenses.calculateTotal());
        }
    }*/
}
