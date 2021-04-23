package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.persistence.UserMapper;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderlineCommand extends CommandProtectedPage {
    public PlaceOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserFacade userFacade = new UserFacade(database);

        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        IngridiensBottom ingridiensBottom = userFacade.getIngridiensBottomsById(bottomId);
        IngridiensTop ingridiensTop = userFacade.getIngridiensTopById(topId);

        Cupcake newCupcake = new Cupcake(ingridiensBottom, ingridiensTop);
        Orderline newOrderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);

        List<Orderline> orderlines;

//        hvis ordrelinjelisten er tom skal den oprettes
        if (session.getAttribute("orderlineList") == null) {
            orderlines = new ArrayList<>();
            session.setAttribute("samletpris", newOrderline.getPrice());
            orderlines.add(newOrderline);
            session.setAttribute("orderlineList", orderlines);

            return pageToShow;
        }

//      hvis ordrelinjeListen har indhold:   (if (session.getAttribute("orderlineList") != null) )

        orderlines = (List<Orderline>) session.getAttribute("orderlineList");

//        undersøger om den nye cupcake allerede findes i listen
        for (Orderline oldOrderline : orderlines) {
            Cupcake oldCupcake = new Cupcake(oldOrderline.getIngridiensBottom(), oldOrderline.getIngridiensTop());
            if (newCupcake.equals(oldCupcake)) {
                int newQuantity = newOrderline.getQuantity() + oldOrderline.getQuantity();
                oldOrderline.setQuantity(newQuantity);
                oldOrderline.setPrice(oldCupcake.getIngridiensBottom(), oldCupcake.getIngridiensTop(), newQuantity);
                session.setAttribute("orderlineList", orderlines);

                return pageToShow;
            }
        }
        orderlines.add(newOrderline);

//        pris beregnes - så vi har den tilgængelig hver gang vi tilføjer til indkøbskurv
        double totalPrice = 0;
        for (Orderline orderline : orderlines) {
            totalPrice = totalPrice + orderline.getPrice();
        }

//        hvis den nytilføjede cupcake ikke findes i listen, tilføjes den
        session.setAttribute("samletpris", totalPrice);
        session.setAttribute("orderlineList", orderlines);

        return pageToShow;
    }
}
