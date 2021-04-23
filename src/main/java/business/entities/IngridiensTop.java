package business.entities;

import java.util.Objects;

public class IngridiensTop {
    private int topId;
    private String flavor;
    private int price;

    public IngridiensTop(int TopId, String flavor, int price) {
        this.topId = TopId;
        this.flavor = flavor;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngridiensTop that = (IngridiensTop) o;
        return topId == that.topId && price == that.price && flavor.equals(that.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topId, flavor, price);
    }

    public int getTopId() {
        return topId;
    }

    public void setTopId(int topId) {
        this.topId = topId;
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
