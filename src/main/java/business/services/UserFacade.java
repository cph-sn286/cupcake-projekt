package business.services;

import business.entities.IngridiensBottom;
import business.entities.IngridiensTop;
import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserFacade {


    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password) throws UserException {
        User user = new User(email, password, "customer", 0.0);
        userMapper.createUser(user);
        return user;
    }

    public List<IngridiensTop> getIngridiensTopsList() throws UserException {
        return userMapper.getIngridiensTopsList();

    }

    public List<IngridiensBottom> getIngridiensBottomsList() throws UserException {
        return userMapper.getIngridiensBottomsList();

    }

    public int updateUser(double saldo, int userId) throws UserException {
        return userMapper.updateUser(saldo, userId);
    }

    public IngridiensBottom getIngridiensBottomsById(int bottomId) throws UserException {
        return userMapper.getIngridiensBottomsById(bottomId);
    }

    public IngridiensTop getIngridiensTopById(int topId) throws UserException {
        return userMapper.getIngridiensTopsList();
    }

}
