package business.entities;

public class Orderline {
    //    Cupcake cupcake;
    private IngridiensBottom ingridiensBottom;
    private IngridiensTop ingridiensTop;
    private int orderId;
    private int quantity;
    private double price;
    private int id;

    public Orderline(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, int orderId, int quantity, double price) {
        this.ingridiensBottom = ingridiensBottom;
        this.ingridiensTop = ingridiensTop;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
    }

//    beregner pris når ordrelinjen instantieres
    public Orderline(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, int quantity) {
        this.ingridiensBottom = ingridiensBottom;
        this.ingridiensTop = ingridiensTop;
        this.quantity = quantity;
        setPrice(ingridiensBottom, ingridiensTop, quantity);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, int quantity) {
        this.price = Double.valueOf((ingridiensBottom.getPrice() + ingridiensTop.getPrice()) * quantity);
    }
}
