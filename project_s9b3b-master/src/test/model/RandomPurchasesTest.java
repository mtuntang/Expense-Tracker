package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RandomPurchases;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomPurchasesTest {
    RandomPurchases purchases;
    @BeforeEach
    public void setup() {
         purchases = new RandomPurchases(25.0, "impulse");
    }

    @Test
    public void methodsTest() {
        assertEquals(25.0, purchases.getMoneyOut(), 0.01);
        assertEquals("impulse", purchases.getDescription());
    }
}
