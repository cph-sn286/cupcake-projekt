package business.entities;

import java.util.Objects;

public class Cupcake {

//    private int id;
    private IngridiensBottom ingridiensBottom;
    private IngridiensTop ingridiensTop;
//    private double price;


//    public Cupcake(int id, IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop, double price) {
////        this.id = id;
//        this.ingridiensBottom = ingridiensBottom;
//        this.ingridiensTop = ingridiensTop;
////        this.price = price;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cupcake cupcake = (Cupcake) o;
        return ingridiensBottom.equals(cupcake.ingridiensBottom) && ingridiensTop.equals(cupcake.ingridiensTop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingridiensBottom, ingridiensTop);
    }

    public Cupcake(IngridiensBottom ingridiensBottom, IngridiensTop ingridiensTop) {
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
}
