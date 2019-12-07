package model;

public class InvalidMoneyAmountException extends Exception {
    public double amount;

    public InvalidMoneyAmountException(double num) {
        System.out.println("You have entered a negative value. We have used its absolute value instead.");
        amount = Math.abs(num);
    }
}
