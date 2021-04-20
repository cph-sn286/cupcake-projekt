package business.entities;

public class IngridiensTop {
    private int id;
    private String flavor;
    private double price;

    public IngridiensTop(int id, String flavor, double price) {
        this.id = id;
        this.flavor = flavor;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
