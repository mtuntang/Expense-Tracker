package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.EatingOut;

import static org.junit.jupiter.api.Assertions.*;

public class EatingOutTest {
    EatingOut eat;
    @BeforeEach
    public void setup() {
        eat = new EatingOut(25.0, "meal");
    }

    @Test
    public void methodsTest() {
        assertEquals(25.0, eat.getMoneyOut(), 0.01);
        assertEquals("meal", eat.getDescription());
    }
}
