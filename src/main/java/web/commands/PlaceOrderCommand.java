package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.UserMapper;
import business.services.UserFacade;

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
//        UserMapper userMapper = new UserMapper(database);
        UserFacade userFacade = new UserFacade(database);
        String pickuptime = (request.getParameter("pickuptime"));

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
            return pageToShow;
        }
        if (session.getAttribute("orderlineList") != null) {
            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

            double totalPrice = 0;
            for (Orderline orderline : orderlines) {
                totalPrice = totalPrice + orderline.getPrice();

            }
            System.out.println("samlet pris er " + totalPrice);
            System.out.printf("saldo er" + session.getAttribute("saldo"));
            if ((double) session.getAttribute("saldo") >= totalPrice) {
                System.out.println("der var penge nok");
                double nySaldo = (double) session.getAttribute("saldo") - totalPrice;
                int userId = (int) session.getAttribute("userid");
                Order order = new Order(userId, "xx:xx", totalPrice);
                userFacade.insertOrder(order, orderlines);
                session.setAttribute("saldo", nySaldo);
                userFacade.updateUser(nySaldo, userId);
                session.setAttribute("ordre", order);
                session.setAttribute("orderlineRecieptList", orderlines);
                session.setAttribute("orderlineList", null);
                return pageToShow;
            } else {
                session.setAttribute("lowsaldomessage", "Ups du har ikke penge nok på kontoen Tank op for at bestille:)");
            }
        }
        return "customerpage";
    }
}
