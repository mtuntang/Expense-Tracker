package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Groceries;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceriesTest {
    Groceries groc;
    @BeforeEach
    public void setup() {
         groc = new Groceries(25, "food");
    }

    @Test
    public void methodsTest() {
        assertEquals(25.0, groc.getMoneyOut(), 0.01);
        assertEquals("food", groc.getDescription());
    }
}
