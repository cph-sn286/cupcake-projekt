package business.entities;

public class IngridiensBottom {
    private int bottomId;
    private String flavor;
    private double price;

    public IngridiensBottom(int bottomId, String flavor, double price) {
        this.bottomId = bottomId;
        this.flavor = flavor;
        this.price = price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
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
