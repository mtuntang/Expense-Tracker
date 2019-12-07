package model;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TotalExpensesTest {

    TotalExpenses totalExp = new TotalExpenses();

    @BeforeEach
    public void setup() {
        totalExp.listExpense.add(new Groceries(25, "ss"));
        totalExp.listExpense.add(new EatingOut(30, "aaa"));
        totalExp.listExpense.add(new RandomPurchases(30, "bbb"));
    }

    @Test
    public void testTotalGroceries() {
        assertEquals(25.0, totalExp.getTotalGroceries(), 0.01);
    }

    @Test
    public void testTotalEatingOut() {
        assertEquals(30.0, totalExp.getTotalEatingOut(), 0.01);
    }

    @Test
    public void testTotalRandomPurchases() {
        assertEquals(30.0, totalExp.getTotalRandomPurchases(), 0.01);
    }

    @Test
    public void testTotalEntertainment() {
        assertEquals(60.0, totalExp.getTotalEntertainment(), 0.01);
    }

    @Test
    public void testCalculateTotal() {
        assertEquals(85, totalExp.calculateTotal(), 0.01);
    }

    @Test
    public void testTotalGroceries2() {
        totalExp.listExpense.add(new Groceries(20, "ss"));
        assertEquals(45.0, totalExp.getTotalGroceries(), 0.01);
    }

    @Test
    public void testTotalEatingOut2() {
        totalExp.listExpense.add(new EatingOut(10, "ttt"));
        assertEquals(40, totalExp.getTotalEatingOut(), 0.01);
    }

    @Test
    public void testCalculateTotal2() {
        totalExp.listExpense.add(new Groceries(20.0, "ss"));
        totalExp.listExpense.add(new EatingOut(10.0, "ttt"));
        assertEquals(115.0, totalExp.calculateTotal(), 0.01);
    }

    @Test
    public void saveLoadTest(){
        totalExp.save(totalExp);
        totalExp.load(totalExp);
        assertEquals(25.0, totalExp.getTotalGroceries(), 0.01);
        assertEquals(30.0, totalExp.getTotalEatingOut(), 0.01);
    }
}

