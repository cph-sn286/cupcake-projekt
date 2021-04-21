package business.entities;

public class Orderline {
    //    Cupcake cupcake;
    private IngridiensBottom ingridiensBottom;
    private IngridiensTop ingridiensTop;
    private int orderId;
    private int quantity=1;
    private int price=1;

    public Orderline(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, int orderId, int quantity, int price) {
        this.ingridiensBottom = ingridiensBottom;
        this.ingridiensTop = ingridiensTop;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

    public Orderline(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop) {
        this.ingridiensBottom = ingridiensBottom;
        this.ingridiensTop = ingridiensTop;
    }

    public IngridiensBottom getIngridiensBottom() {
        return ingridiensBottom;
    }

    public void setIngridiensBottom(IngridiensBottom ingridiensBottom) {
        this.ingridiensBottom = ingridiensBottom;
    }

    public IngridiensTop getIngridiensTop() {
        return ingridiensTop;
    }

    public void setIngridiensTop(IngridiensTop ingridiensTop) {
        this.ingridiensTop = ingridiensTop;
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
