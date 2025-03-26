import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private static int transactionCounter = 0;
    private int transactionId;
    private String cardOwner;
    private float amount;
    private String message;
    private float remainingBalance;
    private String transactionDate;

    public Transaction(String cardOwner, float amount, String message, float remainingBalance) {
        transactionCounter++;
        this.transactionId = transactionCounter;
        this.cardOwner = cardOwner;
        this.amount = amount;
        this.message = message;
        this.remainingBalance = remainingBalance;
        this.transactionDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Card Owner: " + cardOwner +
                ", Amount: " + amount + ", Message: " + message + ", Remaining Balance: " +
                remainingBalance + ", Date: " + transactionDate;
    }
}

