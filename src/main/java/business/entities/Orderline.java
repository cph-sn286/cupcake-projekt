package business.entities;

public class Orderline {

    private  int ingridiensBottomId;
    private int ingridiensTopId;
    private int orderId;
    private int quantity;
    double price;

    public Orderline( int orderId, int quantity, double price) {

        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderline(int ingridiensBottomId, int ingridiensTopId, int orderId, int quantity, double price) {
        this.ingridiensBottomId = ingridiensBottomId;
        this.ingridiensTopId = ingridiensTopId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderline(int ingridiensBottomId, int ingridiensTopId, int quantity, double price) {
        this.ingridiensBottomId = ingridiensBottomId;
        this.ingridiensTopId = ingridiensTopId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIngridiensBottomId() {
        return ingridiensBottomId;
    }

    public void setIngridiensBottomId(int ingridiensBottomId) {
        this.ingridiensBottomId = ingridiensBottomId;
    }

    public int getIngridiensTopId() {
        return ingridiensTopId;
    }

    public void setIngridiensTopId(int ingridiensTopId) {
        this.ingridiensTopId = ingridiensTopId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
