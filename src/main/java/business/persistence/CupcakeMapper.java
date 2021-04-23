package business.persistence;

import business.entities.Orders;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {

    private Database database;

    public CupcakeMapper(Database database) {
        this.database = database;
    }

    public int deleteOrder(int order_id) throws UserException {
        int rowaAffected = 0;
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM orderline " +
                    "WHERE order_id = ?";
            String sql2 = "DELETE FROM orders " +
                    "WHERE order_id = ?";

            while (rowaAffected == 0) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, order_id);
                    rowaAffected = ps.executeUpdate();

                    if (rowaAffected==0){rowaAffected=1;}
                } catch (SQLException ex) {
                    throw new UserException(ex.getMessage());
                }
            }
            while (rowaAffected >= 1) {
                try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                    ps.setInt(1, order_id);
                    rowaAffected += ps.executeUpdate();
                    break;
                } catch (SQLException ex) {
                    throw new UserException(ex.getMessage());
                }
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }

        return rowaAffected;
    }

    public List<Orders> getAllOrders() throws UserException {
        List<Orders> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    String pickuptime = rs.getString("pickuptime");
                    double totalprice = rs.getDouble("totalprice");
                    Timestamp created = rs.getTimestamp("created");
                    orderList.add(new Orders(order_id, user_id, pickuptime, totalprice, created));

                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Orders getOrderById(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE order_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    String pickuptime = rs.getString("pickuptime");
                    double totalprice = rs.getDouble("totalprice");
                    Timestamp created = rs.getTimestamp("created");
                    return new Orders(order_id, user_id, pickuptime, totalprice, created);
                }
                throw new UserException("Ordren findes ikke for order_id = " + orderId);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int updateOrder(String pickuptime) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET pickuptime = ? WHERE order_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, pickuptime);
                int rowsInserted = ps.executeUpdate();
                return rowsInserted;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


}
