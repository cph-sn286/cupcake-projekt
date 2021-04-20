package business.entities;

import java.sql.Timestamp;

public class Orders {

    private int order_id;
    private int user_id;
    private String pickuptime;
    private double totalprice;
    private Timestamp created;

    public Orders(int order_id, int user_id, String pickuptime, double totalprice) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.pickuptime = pickuptime;
        this.totalprice = totalprice;
    }

    public Orders(int order_id, int user_id, String pickuptime, double totalprice, Timestamp created) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.pickuptime = pickuptime;
        this.totalprice = totalprice;
        this.created = created;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(String pickuptime) {
        this.pickuptime = pickuptime;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
