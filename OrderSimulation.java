import java.util.*;
public class OrderSimulation {
    public static void main(String[] args) {
        System.out.println("=== WELCOME TO OUR SMART ORDERING SYSTEM ===");
        // Müşteri Bilgileri)
        Map<Integer, String> customers = new HashMap<>();
        customers.put(101, "Buse (VIP Customer)");
        customers.put(102, "Nur (Standard Customer)");
        customers.put(103, "Haydar (VIP Customer)");
        customers.put(104, "Can (Standard Customer)");

        // PRIORITY QUEUE (Öncelikli Sipariş Kuyruğu)
        // Öncelik skoru yüksek olan (VIP) sipariş her zaman öne geçer.
        PriorityQueue<Order> orderQueue = new PriorityQueue<>();

        System.out.println("--> Siparişler sisteme giriliyor...");
        orderQueue.add(new Order(1, 102, "Kültür", 1));
        orderQueue.add(new Order(2, 101, "Dokuma", 5));
        orderQueue.add(new Order(3, 104, "Meltem", 2));
        orderQueue.add(new Order(4, 103, "Lara", 5));

        System.out.println("Orders have been successfully received and prioritized.");

        // MAP (Rota)
        // Key: Başlangıç Noktası, Value: (Hedef Noktası -> Mesafe/Maliyet)
        Map<String, Map<String, Integer>> cityMap = new HashMap<>();
        cityMap.get("Merkez").put("Dokuma", 8);
        cityMap.get("Merkez").put("Meltem", 12);
        cityMap.get("Merkez").put("Kültür", 10);
        cityMap.get("Merkez").put("Lara", 35);

        //  STACK (Teslimat Geçmişi)
        Stack<Order> deliveryHistory= new Stack<>();
        System.out.println("=== DELIVERING PROCESS IS STARTING ===");
        while (!orderQueue.isEmpty()) {
             progressingOrder = orderQueue.poll();
            String customerName = customers.get(progressingOrder.getCustomerId());

            System.out.println("[Order is preparing] ORDER ID: " + progressingOrder.getId() + " | Customer: " + customerName);
            String targetAdress = progressingOrder.getDeliveryAdress();
            int distance = cityMap.get("Merkez").getOrDefault(targetAdress, -1);

            System.out.println("[Route created] Distance between Merkez -> " + targetAdress + distance + " KM");
            System.out.println("[Status] " + "The delivery to the address " + targetAdress + " was successfully completed.");
            deliveryHistory.push(progresingOrder);
        }
        // UNDO
        System.out.println("=== FAULTY: LAST DELIVERY WILL BE REFUNDED ===");
        if (!deliveryHistory.isEmpty()) {
            Order faultyDelivery = deliveryHistory.pop();
            String faultyCustomer = customers.get(faultyDelivery.getCustomerId());
            System.out.println("[WARNING!]" + " It was noticed that the customer named " + faultyCustomer + "was not at the address!");
            System.out.println("[Cancelled] Order ID: " + faultyDelivery.getId() + " (" + faultyDelivery.getDeliveryAdress() + ")");
        }
        System.out.println("=== YOUR ORDER HAS BEEN RECEIVED ===");
    }
}
//  Siparis sınıfı
class Order {
    private int id;
    private int customerId;
    private String deliveryAdress;
    private int priorityScore;

    public Order (int id, int customerId, String deliveryAdress, int priorityScore) {
        this.id = id;
        this.customerId = customerId;
        this.deliveryAdress = deliveryAdress;
        this.priorityScore = priorityScore;
    }

    public int getId() {
        return id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public String getDeliveryAdress() {
        return deliveryAdress;
    }
    public int getPriorityScore() {
        return priorityScore;
    }
}
