package business.entities;

import java.util.Objects;

public class IngridiensBottom {
    private int bottomId;
    private String flavor;
    private int price;

    public IngridiensBottom(int bottomId, String flavor, int price) {
        this.bottomId = bottomId;
        this.flavor = flavor;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngridiensBottom that = (IngridiensBottom) o;
        return bottomId == that.bottomId && price == that.price && flavor.equals(that.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottomId, flavor, price);
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

    public void setPrice(int price) {
        this.price = price;
    }
}
