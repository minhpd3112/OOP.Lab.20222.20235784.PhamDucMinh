import java.util.ArrayList;
import java.util.Scanner;

public class Aims {
    // Danh sách DVD của cửa hàng
    private static ArrayList<DigitalVideoDisc> store = new ArrayList<>();
    // Danh sách đơn hàng
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo cửa hàng với một vài DVD
        store.add(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.add(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.add(new DigitalVideoDisc("Aladdin", "Animation", 18.99f));

        Cart cart = new Cart();

        while (true) {
            System.out.println("\n----- Welcome to AIMS Project -----");
            System.out.println("1. Customer");
            System.out.println("2. Manager");
            System.out.println("3. Exit");
            System.out.print("Select role: ");
            int role = scanner.nextInt();
            scanner.nextLine();

            if (role == 1) {
                customerMenu(scanner, cart);
            } else if (role == 2) {
                managerMenu(scanner);
            } else if (role == 3) {
                System.out.println("Exiting system.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    // Menu dành cho khách hàng
    private static void customerMenu(Scanner scanner, Cart cart) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Browse DVDs in store");
            System.out.println("2. Search DVDs");
            System.out.println("3. View DVD Details and Play");
            System.out.println("4. Add DVD to Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Update Quantity in Cart");
            System.out.println("7. Remove DVD from Cart");
            System.out.println("8. Sort Cart");
            System.out.println("9. Apply Free Item");
            System.out.println("10. Filter Cart by ID/Title");
            System.out.println("11. Place Order");
            System.out.println("12. Back to Main Menu");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                browseStore();
            } else if (option == 2) {
                searchDVD(scanner);
            } else if (option == 3) {
                viewAndPlayDVD(scanner);
            } else if (option == 4) {
                addDVDToCart(scanner, cart);
            } else if (option == 5) {
                cart.displayCart();
            } else if (option == 6) {
                System.out.print("Enter DVD ID to update quantity: ");
                int dvdId = scanner.nextInt();
                System.out.print("Enter new quantity: ");
                int newQty = scanner.nextInt();
                scanner.nextLine();
                cart.updateQuantity(dvdId, newQty);
            } else if (option == 7) {
                System.out.print("Enter DVD ID to remove from cart: ");
                int dvdId = scanner.nextInt();
                scanner.nextLine();
                DigitalVideoDisc target = null;
                // Tìm DVD trong cart theo id (cách đơn giản: lọc qua store)
                for (DigitalVideoDisc d : store) {
                    if (d.getId() == dvdId) {
                        target = d;
                        break;
                    }
                }
                if (target != null) {
                    cart.removeDigitalVideoDisc(target);
                } else {
                    System.out.println("DVD not found.");
                }
            } else if (option == 8) {
                System.out.println("Sort by: 1. Title   2. Cost");
                int sortOpt = scanner.nextInt();
                scanner.nextLine();
                if (sortOpt == 1) {
                    cart.sortByTitle();
                } else if (sortOpt == 2) {
                    cart.sortByCost();
                } else {
                    System.out.println("Invalid sort option.");
                }
            } else if (option == 9) {
                cart.applyFreeItem();
            } else if (option == 10) {
                System.out.print("Enter DVD ID or title to filter: ");
                String query = scanner.nextLine();
                DigitalVideoDisc found = cart.filterItem(query);
                if (found != null) {
                    System.out.println("Found DVD: " + found.toString());
                } else {
                    System.out.println("Item not found in cart.");
                }
            } else if (option == 11) {
                // Đặt hàng: hiển thị giỏ hàng, nhập thông tin giao hàng, thanh toán
                if (cart.getQtyOrdered() == 0) {
                    System.out.println("Cart is empty. Cannot place order.");
                    continue;
                }
                System.out.print("Enter delivery address: ");
                String address = scanner.nextLine();
                System.out.print("Enter delivery instructions: ");
                String instructions = scanner.nextLine();
                // Tạo đơn hàng từ giỏ hàng hiện tại
                DigitalVideoDisc[] orderItems = cart.getItems();
                int quantity = cart.getQtyOrdered();
                Order order = new Order(orderItems, quantity, address, instructions);
                order.displayInvoice();

                // Thanh toán
                System.out.print("Proceed to payment? (yes/no): ");
                String payChoice = scanner.nextLine();
                if (payChoice.equalsIgnoreCase("yes")) {
                    System.out.print("Enter credit card number (16 digits): ");
                    String cardNum = scanner.nextLine();
                    System.out.print("Enter card owner: ");
                    String cardOwner = scanner.nextLine();
                    System.out.print("Enter card balance: ");
                    float balance = scanner.nextFloat();
                    scanner.nextLine();
                    CreditCard card = new CreditCard(cardNum, cardOwner, balance);
                    Payment payment = new Payment();
                    Transaction trans = payment.processPayment(card, order.getTotalCostAfterVAT());
                    System.out.println(trans.toString());
                }
                // Lưu đơn hàng vào danh sách orders
                orders.add(order);
                // Sau khi đặt hàng, reset giỏ hàng (tạo mới)
                cart = new Cart();
            } else if (option == 12) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    // Menu dành cho manager
    private static void managerMenu(Scanner scanner) {
        // Giả sử tài khoản manager: username=admin, password=admin
        System.out.print("Enter manager username: ");
        String user = scanner.nextLine();
        System.out.print("Enter manager password: ");
        String pass = scanner.nextLine();
        if (!user.equals("admin") || !pass.equals("admin")) {
            System.out.println("Invalid credentials.");
            return;
        }
        while (true) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View Pending Orders");
            System.out.println("2. Approve an Order");
            System.out.println("3. Reject an Order");
            System.out.println("4. Add new DVD to store");
            System.out.println("5. Remove DVD from store");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int mOption = scanner.nextInt();
            scanner.nextLine();
            if (mOption == 1) {
                System.out.println("Pending Orders:");
                for (Order order : orders) {
                    if (order != null) {
                        order.displayInvoice();
                    }
                }
            } else if (mOption == 2) {
                System.out.print("Enter Order ID to approve: ");
                int oid = scanner.nextInt();
                scanner.nextLine();
                for (Order order : orders) {
                    if (order.getOrderId() == oid) {
                        order.setStatus("Approved");
                        System.out.println("Order " + oid + " approved.");
                        break;
                    }
                }
            } else if (mOption == 3) {
                System.out.print("Enter Order ID to reject: ");
                int oid = scanner.nextInt();
                scanner.nextLine();
                for (Order order : orders) {
                    if (order.getOrderId() == oid) {
                        order.setStatus("Rejected");
                        System.out.println("Order " + oid + " rejected.");
                        break;
                    }
                }
            } else if (mOption == 4) {
                System.out.print("Enter DVD title: ");
                String title = scanner.nextLine();
                System.out.print("Enter DVD category: ");
                String category = scanner.nextLine();
                System.out.print("Enter DVD director: ");
                String director = scanner.nextLine();
                System.out.print("Enter DVD length: ");
                int length = scanner.nextInt();
                System.out.print("Enter DVD cost: ");
                float cost = scanner.nextFloat();
                scanner.nextLine();
                DigitalVideoDisc newDVD = new DigitalVideoDisc(title, category, director, length, cost);
                store.add(newDVD);
                System.out.println("Added new DVD to store: " + newDVD.toString());
            } else if (mOption == 5) {
                System.out.print("Enter DVD ID to remove from store: ");
                int dvdId = scanner.nextInt();
                scanner.nextLine();
                DigitalVideoDisc toRemove = null;
                for (DigitalVideoDisc dvd : store) {
                    if (dvd.getId() == dvdId) {
                        toRemove = dvd;
                        break;
                    }
                }
                if (toRemove != null) {
                    store.remove(toRemove);
                    System.out.println("Removed DVD from store: " + toRemove.getTitle());
                } else {
                    System.out.println("DVD not found in store.");
                }
            } else if (mOption == 6) {
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    // Hiển thị các DVD có trong cửa hàng (sắp xếp theo thứ tự mới nhất trước)
    private static void browseStore() {
        System.out.println("----- DVDs in Store (latest first) -----");
        for (int i = store.size() - 1; i >= 0; i--) {
            System.out.println(store.get(i).toString());
        }
    }

    // Tìm kiếm DVD theo tiêu đề, thể loại hoặc giá
    private static void searchDVD(Scanner scanner) {
        System.out.println("Search by: 1. Title  2. Category  3. Price");
        int sOpt = scanner.nextInt();
        scanner.nextLine();
        ArrayList<DigitalVideoDisc> results = new ArrayList<>();
        if (sOpt == 1) {
            System.out.print("Enter title keywords: ");
            String keywords = scanner.nextLine().toLowerCase();
            for (DigitalVideoDisc dvd : store) {
                String[] words = dvd.getTitle().toLowerCase().split("\\s+");
                for (String word : words) {
                    if (keywords.contains(word)) {
                        results.add(dvd);
                        break;
                    }
                }
            }
        } else if (sOpt == 2) {
            System.out.print("Enter category: ");
            String cat = scanner.nextLine();
            for (DigitalVideoDisc dvd : store) {
                if (dvd.getCategory() != null && dvd.getCategory().equalsIgnoreCase(cat)) {
                    results.add(dvd);
                }
            }
        } else if (sOpt == 3) {
            System.out.print("Enter minimum price (or 0 for none): ");
            float min = scanner.nextFloat();
            System.out.print("Enter maximum price: ");
            float max = scanner.nextFloat();
            scanner.nextLine();
            for (DigitalVideoDisc dvd : store) {
                if (dvd.getCost() >= min && dvd.getCost() <= max) {
                    results.add(dvd);
                }
            }
        } else {
            System.out.println("Invalid search option.");
            return;
        }
        System.out.println("Search Results:");
        for (DigitalVideoDisc dvd : results) {
            System.out.println(dvd.toString());
        }
    }

    // Xem chi tiết DVD và cho phép “play”
    private static void viewAndPlayDVD(Scanner scanner) {
        System.out.print("Enter DVD ID to view details: ");
        int dvdId = scanner.nextInt();
        scanner.nextLine();
        DigitalVideoDisc selected = null;
        for (DigitalVideoDisc dvd : store) {
            if (dvd.getId() == dvdId) {
                selected = dvd;
                break;
            }
        }
        if (selected != null) {
            System.out.println("DVD Details: " + selected.toString());
            System.out.print("Do you want to play this DVD? (yes/no): ");
            String playChoice = scanner.nextLine();
            if (playChoice.equalsIgnoreCase("yes")) {
                selected.play();
            }
        } else {
            System.out.println("DVD not found.");
        }
    }

    // Thêm DVD vào giỏ từ cửa hàng: người dùng nhập DVD ID
    private static void addDVDToCart(Scanner scanner, Cart cart) {
        System.out.print("Enter DVD ID to add to cart: ");
        int dvdId = scanner.nextInt();
        scanner.nextLine();
        DigitalVideoDisc selected = null;
        for (DigitalVideoDisc dvd : store) {
            if (dvd.getId() == dvdId) {
                selected = dvd;
                break;
            }
        }
        if (selected != null) {
            cart.addDigitalVideoDisc(selected);
        } else {
            System.out.println("DVD not found in store.");
        }
    }

    // Tính tổng chi phí sau VAT (để sử dụng trong thanh toán)
    // VAT = 10%
    public static float totalCostAfterVAT(Order order) {
        // Trong Order đã tính toán sẵn, nên ta có thể lấy thông tin đó
        // (Trong phiên bản này, đơn hàng hiển thị tổng cost sau VAT trong invoice)
        return 0; // chỉ để minh họa; thanh toán thực tế được thực hiện trong Payment
    }
}
