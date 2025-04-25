public class CreditCard{
    private String cardNumber;
    private String cardOwner;
    private float balance;
    public CreditCard(String cardNumber, String cardOwner, float balance) {
        this.cardNumber = cardNumber;
        this.cardOwner = cardOwner;
        this.balance = balance;
    }
    public String getCardNumber() { return cardNumber; }
    public String getCardOwner() { return cardOwner; }
    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }
    @Override
    public String toString(){
        return "CreditCard [Owner=" + cardOwner + ", Number=" + cardNumber + ", Balance=" + balance + "]";
    }
}

