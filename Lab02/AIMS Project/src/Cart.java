import java.util.Random;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    // Đánh dấu vị trí free item (-1 nếu chưa áp dụng)
    private int freeItemIndex = -1;

    // Thêm DVD đơn lẻ
    public void addDigitalVideoDisc(DigitalVideoDisc dvd) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = dvd;
            qtyOrdered++;
            System.out.println("Added DVD: " + dvd.getTitle());
        } else {
            System.out.println("Cart is full. Cannot add " + dvd.getTitle());
        }
    }

    // Overloading: thêm một mảng các DVD
    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        for (DigitalVideoDisc dvd : dvdList) {
            addDigitalVideoDisc(dvd);
        }
    }

    // Overloading: thêm 2 DVD cùng lúc
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    // Xoá DVD khỏi giỏ (xoá lần xuất hiện đầu tiên)
    public void removeDigitalVideoDisc(DigitalVideoDisc dvd) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getId() == dvd.getId()) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("Removed DVD: " + dvd.getTitle());
                // Nếu freeItemIndex bị ảnh hưởng, reset
                if (freeItemIndex >= qtyOrdered) {
                    freeItemIndex = -1;
                }
                break;
            }
        }
        if (!found) {
            System.out.println("DVD " + dvd.getTitle() + " not found in cart.");
        }
    }

    // Cập nhật số lượng của một DVD (tìm theo id)
    // Ta xoá hết các bản sao của DVD đó rồi thêm lại newQuantity bản sao nếu đủ chỗ.
    public void updateQuantity(int dvdId, int newQuantity) {
        int count = 0;
        // Đếm số lượng hiện có
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getId() == dvdId) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("DVD with ID " + dvdId + " not found in cart.");
            return;
        }
        // Tính số lượng hiện có của các DVD khác
        int others = qtyOrdered - count;
        if (others + newQuantity > MAX_NUMBERS_ORDERED) {
            System.out.println("Cannot update quantity; cart capacity exceeded.");
            return;
        }
        // Lưu DVD cần update (giả sử tất cả các bản sao đều giống nhau)
        DigitalVideoDisc target = null;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getId() == dvdId) {
                target = itemsOrdered[i];
                break;
            }
        }
        // Xoá tất cả các bản sao của DVD đó
        for (int i = 0; i < count; i++) {
            removeDigitalVideoDisc(target);
        }
        // Thêm lại newQuantity bản sao
        for (int i = 0; i < newQuantity; i++) {
            addDigitalVideoDisc(target);
        }
        System.out.println("Updated quantity of DVD \"" + target.getTitle() + "\" to " + newQuantity + ".");
    }

    // Sắp xếp giỏ hàng theo title (alphabetically, nếu trùng thì DVD có giá cao hơn đứng trước)
    public void sortByTitle() {
        for (int i = 0; i < qtyOrdered - 1; i++) {
            for (int j = i + 1; j < qtyOrdered; j++) {
                String t1 = itemsOrdered[i].getTitle().toLowerCase();
                String t2 = itemsOrdered[j].getTitle().toLowerCase();
                if (t1.compareTo(t2) > 0 || (t1.equals(t2) && itemsOrdered[i].getCost() < itemsOrdered[j].getCost())) {
                    DigitalVideoDisc temp = itemsOrdered[i];
                    itemsOrdered[i] = itemsOrdered[j];
                    itemsOrdered[j] = temp;
                }
            }
        }
        System.out.println("Cart sorted by title.");
    }

    // Sắp xếp giỏ hàng theo cost (giảm dần, nếu trùng thì theo title tăng dần)
    public void sortByCost() {
        for (int i = 0; i < qtyOrdered - 1; i++) {
            for (int j = i + 1; j < qtyOrdered; j++) {
                if (itemsOrdered[i].getCost() < itemsOrdered[j].getCost() ||
                        (itemsOrdered[i].getCost() == itemsOrdered[j].getCost() &&
                                itemsOrdered[i].getTitle().compareToIgnoreCase(itemsOrdered[j].getTitle()) > 0)) {
                    DigitalVideoDisc temp = itemsOrdered[i];
                    itemsOrdered[i] = itemsOrdered[j];
                    itemsOrdered[j] = temp;
                }
            }
        }
        System.out.println("Cart sorted by cost.");
    }

    // Lọc DVD trong giỏ theo ID (so sánh chính xác) hoặc theo tiêu đề chứa chuỗi
    public DigitalVideoDisc filterItem(String query) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (String.valueOf(itemsOrdered[i].getId()).equals(query) ||
                    itemsOrdered[i].getTitle().toLowerCase().contains(query.toLowerCase())) {
                return itemsOrdered[i];
            }
        }
        return null;
    }

    // Áp dụng free item: chọn ngẫu nhiên một DVD và đánh dấu nó miễn phí (sẽ được trừ khỏi tổng chi phí)
    public void applyFreeItem() {
        if (qtyOrdered == 0) {
            System.out.println("Cart is empty. No free item available.");
            return;
        }
        Random rand = new Random();
        freeItemIndex = rand.nextInt(qtyOrdered);
        System.out.println("Congratulations! DVD \"" + itemsOrdered[freeItemIndex].getTitle() + "\" is free!");
    }

    // Tính tổng chi phí (nếu free item được áp dụng, trừ chi phí của DVD đó)
    public float totalCost() {
        float sum = 0.0f;
        for (int i = 0; i < qtyOrdered; i++) {
            sum += itemsOrdered[i].getCost();
        }
        if (freeItemIndex != -1) {
            sum -= itemsOrdered[freeItemIndex].getCost();
        }
        return sum;
    }

    // Hiển thị giỏ hàng: số thứ tự, tiêu đề và cost của từng DVD, rồi tổng chi phí
    public void displayCart() {
        System.out.println("----- Cart Items -----");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%d. %s   %.2f\n", i+1, itemsOrdered[i].getTitle(), itemsOrdered[i].getCost());
        }
        System.out.printf("Total Cost: %.2f\n", totalCost());
    }

    // Getter cho số lượng DVD hiện có
    public int getQtyOrdered() {
        return qtyOrdered;
    }

    // Trả về một bản sao (mảng) các DVD trong giỏ để sử dụng cho đơn hàng
    public DigitalVideoDisc[] getItems() {
        DigitalVideoDisc[] copy = new DigitalVideoDisc[qtyOrdered];
        for (int i = 0; i < qtyOrdered; i++) {
            copy[i] = itemsOrdered[i];
        }
        return copy;
    }
}
