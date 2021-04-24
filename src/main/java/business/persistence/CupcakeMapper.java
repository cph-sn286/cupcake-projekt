package business.persistence;

import business.entities.Order;
import business.entities.Orders;
import business.entities.User;
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

                    if (rowaAffected == 0) {
                        rowaAffected = 1;
                    }
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


    public List<User> getAllCustomers() throws UserException {

        List<User> userList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM users WHERE users.role = 'customer'";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int user_id = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    double saldo = rs.getDouble("saldo");
                    userList.add(new User(user_id, email, password, role, saldo));

                }
                return userList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public User getCustomerById(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    double saldo = rs.getDouble("saldo");
                    return new User(user_id,email, password, role, saldo);
                }
                throw new UserException("Ordren findes ikke for order_id = " + user_id);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public int deleteCustomer(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM users " +
                    "WHERE user_id = ? AND saldo = 0";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                int rowaAffected = ps.executeUpdate();
                return rowaAffected;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }

    }

    public List<Orders> getOrderlistByUserId(int userId) throws UserException {
        List<Orders> orderListUser = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int order_id = rs.getInt("order_id");
                    int user_id = rs.getInt("user_id");
                    String pickuptime = rs.getString("pickuptime");
                    double totalprice = rs.getDouble("totalprice");
                    Timestamp created = rs.getTimestamp("created");
                    orderListUser.add(new Orders(order_id, user_id, pickuptime, totalprice, created));
                }
                    return orderListUser;

            } catch (SQLException ex) {
                throw new UserException("der findes ingen ordre for user = " + userId);
//                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
