public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private DigitalVideoDisc[] items;
    private int quantity; // Số lượng DVD trong đơn
    private String deliveryAddress;
    private String deliveryInstructions;
    private float totalCostBeforeVAT;
    private float totalCostAfterVAT;
    private float deliveryFee;
    private String status; // "Pending", "Approved", "Rejected"

    public Order(DigitalVideoDisc[] items, int quantity, String deliveryAddress, String deliveryInstructions) {
        orderCounter++;
        this.orderId = orderCounter;
        this.items = items;
        this.quantity = quantity;
        this.deliveryAddress = deliveryAddress;
        this.deliveryInstructions = deliveryInstructions;
        this.status = "Pending";
        calculateCosts();
    }

    // Tính toán tổng chi phí, VAT (10%) và phí giao hàng (giả sử mỗi DVD nặng 0.1kg, phí $5/kg)
    private void calculateCosts() {
        float sum = 0.0f;
        for (DigitalVideoDisc dvd : items) {
            sum += dvd.getCost();
        }
        totalCostBeforeVAT = sum;
        totalCostAfterVAT = totalCostBeforeVAT * 1.1f;
        float totalMass = quantity * 0.1f;
        deliveryFee = totalMass * 5;
    }

    public void displayInvoice() {
        System.out.println("----- Invoice for Order #" + orderId + " -----");
        for (int i = 0; i < quantity; i++) {
            System.out.println((i+1) + ". " + items[i].toString());
        }
        System.out.printf("Total Cost (Before VAT): %.2f\n", totalCostBeforeVAT);
        System.out.printf("Total Cost (After VAT): %.2f\n", totalCostAfterVAT);
        System.out.printf("Delivery Fee: %.2f\n", deliveryFee);
        System.out.println("Order Status: " + status);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public float getTotalCostAfterVAT() {
        return totalCostAfterVAT;
    }

}
