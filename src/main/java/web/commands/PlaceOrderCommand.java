package web.commands;

import business.entities.IngridiensBottom;
import business.entities.IngridiensTop;
import business.entities.Order;
import business.entities.Orderline;
import business.exceptions.UserException;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PlaceOrderCommand extends CommandUnprotectedPage {
    public PlaceOrderCommand(String pageToShow) {
        super(pageToShow);
//        husk at sætte facade ind
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(database);
//        skal være via facade

        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));
        IngridiensBottom ingridiensBottom = userMapper.getIngridiensBottomsById(bottomId);
        IngridiensTop ingridiensTop = userMapper.getIngridiensTopById(topId);

//        Orderline orderline = new Orderline(ingridiensBottom, ingridiensTop);

//        attrap-ordre
        Order order = new Order(4, "18:99", 100);


//beregn pris via pris som vi har adgang til via applicationscope


        userMapper.insertOrder(order, bottomId, topId);


        return pageToShow;
    }
}
