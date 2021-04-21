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

//        beregner pris på ordre som består af enkelt ordrelinje
        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        IngridiensBottom ingridiensBottom = userMapper.getIngridiensBottomsById(bottomId);
        IngridiensTop ingridiensTop = userMapper.getIngridiensTopById(topId);

        Orderline orderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);
        double totalPrice = orderline.getPrice();

//        for at lave en indkøbskurv skal vi have en liste af ordrelinjer som vi mapper

        Order order = new Order(3, "xx:xx", totalPrice);

        userMapper.insertOrder(order, orderline);


        return pageToShow;
    }
}
