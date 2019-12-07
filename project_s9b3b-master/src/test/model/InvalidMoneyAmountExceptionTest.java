package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InvalidMoneyAmountExceptionTest {
    TotalExpenses totalExp;

    @BeforeEach
    public void setup() {
        totalExp = new TotalExpenses();
    }

    @Test
    public void negativeValueTest1() {
        double amount = 0;
        try {
            throw new InvalidMoneyAmountException(-10);
        } catch (InvalidMoneyAmountException e) {
            amount = e.amount;
        }
        assertEquals(10, amount, 0.01);
    }
    public void negativeValueTest2() {
        double amount = 0;
        try {
            throw new InvalidMoneyAmountException(10);
        } catch (InvalidMoneyAmountException e) {
            fail("get exception when shouldn't have");
        }

    }

}
