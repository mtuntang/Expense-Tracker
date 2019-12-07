package ui;

import model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static TotalExpenses total = new TotalExpenses();

    public static void main(String[] args) {
        TotalExpenses total = new TotalExpenses();
        total = total.load(total);
        new DefaultFrame(total);


    }
/*

    public static void loop(TotalExpenses total) {
        boolean exit = false;
        while (exit == false) {
            total = addExpense(total);
            in.nextLine();
            System.out.println("Continue ? Y/N");
            String ans = in.nextLine();
            if (ans.toUpperCase().equals("N")) {
                exit = true;
                printSummary(total);
                total.save(total);
            }
        }
    }


    public static TotalExpenses specifyCategory(TotalExpenses total, double moneySpent, String descr) {
        System.out.println("Specify category: 1 Eating Out, 2 Random Purchases");
        int input = in.nextInt();
        if (input == 1) {
            total.listExpense.add(new EatingOut(moneySpent, descr));
        } else if (input == 2) {
            total.listExpense.add(new RandomPurchases(moneySpent, descr));
        }
        return total;
    }

    public static TotalExpenses addExpense(TotalExpenses total) {
        int input = in.nextInt();
        if (input == 0) {
            total.clearData();
        } else {
            double moneySpent = 0;
            try {
                System.out.println("Enter money spent: ");
                moneySpent = in.nextDouble();
                if (moneySpent < 0) {
                    throw new InvalidMoneyAmountException(moneySpent);
                }
            } catch (InvalidMoneyAmountException e) {
                moneySpent = e.amount;
            } finally {
                addToList(total, moneySpent, input);
            }
        }
        return total;
    }

    public static void addToList(TotalExpenses total, double moneySpent, int input) {
        in.nextLine();
        System.out.println("Enter description: ");
        String descr = in.nextLine();
        if (input == 1) {
            total.listExpense.add(new Groceries(moneySpent, descr));
        } else if (input == 2) {
            specifyCategory(total, moneySpent, descr);
        }
    }

    //
    public static void printAllItems(TotalExpenses total) {
        printAllGroceries(total);
        printAllEatingOut(total);
        printAllRandomPurchases(total);
    }

    public static void printAllGroceries(TotalExpenses total) {
        for (Expense expense : total.listExpense) {
            if (expense instanceof Groceries) {
                System.out.println("Groceries ==> " + expense.getDescription());
                System.out.println("$" + expense.getMoneyOut());
                System.out.println("---------------------------------");
            }
        }
    }

    public static void printAllEatingOut(TotalExpenses total) {
        for (Expense expense : total.listExpense) {
            if (expense instanceof EatingOut) {
                System.out.println("Eating Out ==> " + expense.getDescription());
                System.out.println("$" + expense.getMoneyOut());
                System.out.println("---------------------------------");
            }
        }
    }

    public static void printAllRandomPurchases(TotalExpenses total) {
        for (Expense expense : total.listExpense) {
            if (expense instanceof RandomPurchases) {
                System.out.println("Random Purchases ==> " + expense.getDescription());
                System.out.println("$" + expense.getMoneyOut());
                System.out.println("---------------------------------");
            }
        }
    }

    public static void printSummary(TotalExpenses total) {
        printAllItems(total);
        System.out.println("Groceries    : $" + total.getTotalGroceries());
        System.out.println("Entertainment: $" + total.getTotalEntertainment());
        System.out.println("    Eating Out      : $" + total.getTotalEatingOut());
        System.out.println("    Random Purchases: $" + total.getTotalRandomPurchases());
        System.out.println("Total expenses: $" + total.calculateTotal());
    }

*/
/*  Link doesn't work anymore
    public static String printWebMessage() {
        String url = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html";
        try {
            // fetch the document over HTTP
            Document doc = Jsoup.connect(url).get();
            return doc.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "boo";
    }*//*


*/


}
