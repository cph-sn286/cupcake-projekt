package business.entities;

public class Orderline {
    Cupcake cupcake;
    private int orderId;
    private int quantity;
    private int price;

    public Orderline(Cupcake cupcake, int orderId, int quantity, int price) {
        this.cupcake = cupcake;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void setCupcake(Cupcake cupcake) {
        this.cupcake = cupcake;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
