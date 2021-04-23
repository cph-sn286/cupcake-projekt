package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderlineCommand extends CommandProtectedPage {
    public PlaceOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);
//        husk at sætte facade ind
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(database);
//        skal være via facade


        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        IngridiensBottom ingridiensBottom = userMapper.getIngridiensBottomsById(bottomId);
        IngridiensTop ingridiensTop = userMapper.getIngridiensTopById(topId);

        Cupcake newCupcake = new Cupcake(ingridiensBottom, ingridiensTop);
        Orderline newOrderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);

        List<Orderline> orderlines = null;

        if (session.getAttribute("orderlineList") == null) {
            orderlines = new ArrayList<>();
            orderlines.add(newOrderline);
            session.setAttribute("orderlineList", orderlines);
            System.out.println("vi nåede til at tilføje ny ordrelinjeliste");
            return pageToShow;
        }

//        if (session.getAttribute("orderlineList") != null) {

        orderlines = (List<Orderline>) session.getAttribute("orderlineList");

//        undersøger om den nye cupcake allerede findes i listen
        for (Orderline oldOrderline : orderlines) {
            Cupcake oldCupcake = new Cupcake(oldOrderline.getIngridiensBottom(), oldOrderline.getIngridiensTop());
            if (newCupcake.equals(oldCupcake)) {
                int newQuantity = newOrderline.getQuantity() + oldOrderline.getQuantity();
                oldOrderline.setQuantity(newQuantity);
                oldOrderline.setPrice(oldCupcake.getIngridiensBottom(), oldCupcake.getIngridiensTop(), newQuantity);
                session.setAttribute("orderlineList", orderlines);
                System.out.println("vi nåede til to ens");
                return pageToShow;
            }
        }

//        hvis den nytilføjede cupcake ikke findes i listen, tilføjes den
        orderlines.add(newOrderline);
        session.setAttribute("orderlineList", orderlines);
        System.out.println("vi nåede hele vejen igennem");

        return pageToShow;
    }
}
