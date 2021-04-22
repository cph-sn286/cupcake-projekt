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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceOrderCommand extends CommandProtectedPage {
    public PlaceOrderCommand(String pageToShow, String role) {
        super(pageToShow, role);
//        husk at sætte facade ind
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(database);
//        skal være via facade

//        beregner pris på ordre som består af enkelt ordrelinje
//        int bottomId = Integer.parseInt(request.getParameter("bund"));
//        int topId = Integer.parseInt(request.getParameter("top"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
////
//        IngridiensBottom ingridiensBottom = userMapper.getIngridiensBottomsById(bottomId);
//        IngridiensTop ingridiensTop = userMapper.getIngridiensTopById(topId);
//
//        Orderline orderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);


        List<Orderline> orderlines = null;

        if (session.getAttribute("orderlineList") == null) {
            session.setAttribute("besked", "der er ingen varer i indkøbskurven");
        }
        if (session.getAttribute("orderlineList") != null) {
            orderlines = (List<Orderline>) session.getAttribute("orderlineList");


//        getServletContext().setAttribute("IngridiensBottomList", userMapper.getIngridiensBottomsList());
//            int userId = session.getAttribute("userid");


//        for at lave en indkøbskurv skal vi have en liste af ordrelinjer som vi mapper

//        double totalPrice = orderline.getPrice();
            Order order = new Order(4, "xx:xx", 35);

            userMapper.insertOrder(order, orderlines);


        }
        return pageToShow;
    }
}
