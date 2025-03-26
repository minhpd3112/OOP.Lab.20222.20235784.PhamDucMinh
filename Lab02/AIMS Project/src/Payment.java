public class Payment {
    // Giả lập quá trình thanh toán qua thẻ tín dụng
    public Transaction processPayment(CreditCard card, float amount) {
        if (card.getCardNumber().length() == 16 && card.getBalance() >= amount) {
            card.setBalance(card.getBalance() - amount);
            String msg = "Payment successful.";
            Transaction trans = new Transaction(card.getCardOwner(), amount, msg, card.getBalance());
            System.out.println("Payment processed successfully.");
            return trans;
        } else {
            String msg = "Payment failed. Invalid card or insufficient balance.";
            Transaction trans = new Transaction(card.getCardOwner(), amount, msg, card.getBalance());
            System.out.println("Payment failed.");
            return trans;
        }
    }
}

