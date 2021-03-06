package business.services;

import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.CupcakeMapper;
import business.persistence.Database;

import java.util.List;

public class CupcakeFacade
{
    CupcakeMapper cupcakeMapper;

    public CupcakeFacade(Database database)
    {
        cupcakeMapper = new CupcakeMapper(database);
    }

    public int deleteOrder(int order_id) throws UserException{
        return cupcakeMapper.deleteOrder(order_id);
    }
    public List<Orders> getAllOrders() throws UserException{
        return cupcakeMapper.getAllOrders();
    }

    public Orders getOrderById(int orderId) throws UserException{
        return cupcakeMapper.getOrderById(orderId);
    }

    public int updateOrder(String order_id, String pickuptime) throws UserException{
        return cupcakeMapper.updateOrder(pickuptime);
    }

    public List<User> getAllCustomers() throws UserException {
        return cupcakeMapper.getAllCustomers();
    }

    public int deleteCustomer(int user_id) throws UserException{
        return cupcakeMapper.deleteCustomer(user_id);
    }

    public User getCustomerById(int user_id) throws UserException {
        return cupcakeMapper.getCustomerById(user_id);
    }
    public List<Orders> getOrderlistByUserId(int userId) throws UserException{
        return cupcakeMapper.getOrderlistByUserId(userId);
    }
}
