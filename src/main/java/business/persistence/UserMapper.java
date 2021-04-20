package business.persistence;

import business.entities.*;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{
    private Database database;

    public UserMapper(Database database)
    {
        this.database = database;
    }

    public void createUser(User user) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO users (email, password, role, saldo) VALUES (?, ?, ?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.setDouble(4,user.getSaldo());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT user_id, role, saldo FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    Double saldo = rs.getDouble("saldo");
                    int id = rs.getInt("user_id");
                    User user = new User(email, password, role, saldo);
                    user.setId(id);
                    return user;
                } else
                {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
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
                    Double price = rs.getDouble("price");
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
            String sql = "SELECT * FROM ingridiens_top";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("ingridient_id");
                    String name = rs.getString("ingridient_name");
                    Double price = rs.getDouble("price");
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

}
