package business.entities;



public class Order {
    private int orderId;
    private int userId;
    private String pickupTime;
    private double totalPrice;
    private String created;

    public Order(int orderId, int userId, String pickupTime, double totalPrice, String created) {
        this.orderId = orderId;
        this.userId = userId;
        this.pickupTime = pickupTime;
        this.totalPrice = totalPrice;
        this.created = created;
    }

    public Order(int userId, String pickupTime, double totalPrice) {
        this.userId = userId;
        this.pickupTime = pickupTime;
        this.totalPrice = totalPrice;

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


//    hvordan er det nu man bedst gemmer et timestamp i java?



}
