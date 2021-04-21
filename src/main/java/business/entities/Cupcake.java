package business.entities;

public class Cupcake {

    private int id;
    private IngridiensBottom ingridiensBottom;
    private IngridiensTop ingridiensTop;
    private double price;


    public Cupcake(int id, IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, double price) {
        this.id = id;
        this.ingridiensBottom = ingridiensBottom;
        this.ingridiensTop = ingridiensTop;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
