package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO users (email, password, role, saldo) VALUES (?, ?, ?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.setDouble(4, user.getSaldo());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id, role, saldo FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    Double saldo = rs.getDouble("saldo");
                    int id = rs.getInt("user_id");
                    User user = new User(email, password, role, saldo);
                    user.setId(id);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<IngridiensTop> getIngridiensTopsList() throws UserException {
        List<IngridiensTop> ingridiensTopList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM ingridiens_top";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("ingridient_id");
                    String name = rs.getString("ingridient_name");
                    int price = rs.getInt("price");
                    ingridiensTopList.add(new IngridiensTop(id, name, price));
                    System.out.println(ingridiensTopList);
                }
                return ingridiensTopList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<IngridiensBottom> getIngridiensBottomsList() throws UserException {
        List<IngridiensBottom> ingridiensBottoms = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM ingridiens_bottom";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("ingridient_id");
                    String name = rs.getString("ingridient_name");
                    int price = rs.getInt("price");
                    ingridiensBottoms.add(new IngridiensBottom(id, name, price));
                    System.out.println(ingridiensBottoms);
                }
                return ingridiensBottoms;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public IngridiensBottom getIngridiensBottomsById(int bottomId) throws UserException {
        IngridiensBottom ingridiensBottom=null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM cupcake.ingridiens_bottom WHERE ingridient_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, bottomId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int price = rs.getInt("price");
                    String flavor = rs.getString("ingridient_name");
                    ingridiensBottom = new IngridiensBottom(bottomId, flavor, price);
                }
                return ingridiensBottom;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public IngridiensTop getIngridiensTopById(int topId) throws UserException {
        IngridiensTop ingridiensTop=null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM cupcake.ingridiens_top WHERE ingridient_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, topId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int price = rs.getInt("price");
                    String flavor = rs.getString("ingridient_name");
                    ingridiensTop = new IngridiensTop(topId, flavor, price);
                }
                return ingridiensTop;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    public void insertOrderline(Orderline orderline, int orderId) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `cupcake`.`orderline`" +

                    "(`ingridient_bottom_id`, `Ingridient_top_id`, `order_id`, `quantity`, `price`) VALUES (?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderline.getIngridiensBottom().getBottomId());
                ps.setInt(2, orderline.getIngridiensTop().getTopId());
                ps.setInt(3, orderId);
                ps.setInt(4, orderline.getQuantity());
                ps.setDouble(5, orderline.getPrice());

                ps.executeUpdate();
//                ResultSet ids = ps.getGeneratedKeys();
//                ids.next();
//                int bmiEntryId = ids.getInt(1);

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertOrder(Order order, List<Orderline> orderlines) throws UserException {


        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `cupcake`.`orders`(`user_id`, `pickuptime`, `totalprice`) VALUES(?,?,?)";


            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, order.getUserId());
                ps.setString(2, order.getPickupTime());
                ps.setDouble(3, order.getTotalPrice());

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);


                for (Orderline orderline : orderlines) {
                insertOrderline(orderline, orderId);

                }


            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }


}
