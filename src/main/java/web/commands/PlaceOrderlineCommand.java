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

        String bottomIdString = request.getParameter("bund");
        String topIdString = request.getParameter("top");
        String quantityString = request.getParameter("quantity");

        if (bottomIdString != null && topIdString !=null && quantityString !=null) {
            int bottomId = Integer.parseInt(bottomIdString);
            int topId = Integer.parseInt(topIdString);
            int quantity = Integer.parseInt(quantityString);

            IngridiensBottom ingridiensBottom = userFacade.getIngridiensBottomsById(bottomId);
            IngridiensTop ingridiensTop = userFacade.getIngridiensTopById(topId);

            Cupcake newCupcake = new Cupcake(ingridiensBottom, ingridiensTop);
            Orderline newOrderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);

            List<Orderline> orderlines;

//        hvis ordrelinjelisten er tom skal den oprettes
            if (session.getAttribute("orderlineList") == null) {
                orderlines = new ArrayList<>();
                session.setAttribute("samletpris", newOrderline.getPrice());

//            ordrelinjen skal tildeles en id som kun bruges i den pågældende indkøbskurv-sessionscope
                newOrderline.setId(1);
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
//        hvis der allerede er noget i listen skal ny id være ën større end længden, da første element har id=1
            int newOrderlineId = orderlines.size() + 1;
            newOrderline.setId(newOrderlineId);

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

        System.out.println("vi er lige før delete");
        String deleteId = request.getParameter("delete");
        System.out.println("vi har hentet et delete -statement");
//        String incId = request.getParameter("inc");
//        String decId = request.getParameter("dec");

        if (deleteId != null) {
            List<Orderline> orderlines;
            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

            System.out.println("vi nåede frem med et deleteID" + deleteId);
            for (Orderline orderline : orderlines) {
                if (orderline.getId() == Integer.parseInt(deleteId)) {
                    System.out.println("vi fandt et element at slette");
                    orderlines.remove(orderline);
//                    så skal vi have navngivet orderlineId
                    System.out.println("vi mangler kun at stemple");
//if(orderlines==null)
//                    session.setAttribute("orderlineList", orderlines);
                    session.setAttribute("orderlineList", orderlines);
                    System.out.println("vi nåede forbi");
                    break;

                }
            }
        }

        return pageToShow;


    }
}
