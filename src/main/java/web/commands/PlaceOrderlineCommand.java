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

        if (bottomIdString != null && topIdString != null && quantityString != null) {
            int bottomId = Integer.parseInt(bottomIdString);
            int topId = Integer.parseInt(topIdString);
            int quantity = Integer.parseInt(quantityString);

            IngridiensBottom ingridiensBottom = userFacade.getIngridiensBottomsById(bottomId);
            IngridiensTop ingridiensTop = userFacade.getIngridiensTopById(topId);

            Cupcake newCupcake = new Cupcake(ingridiensBottom, ingridiensTop);
            Orderline newOrderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);

            List<Orderline> orderlines;

            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

//        hvis ordrelinjelisten er tom skal den oprettes
            if (orderlines == null || orderlines.size() == 0) {
                System.out.println("vi kom igennem if-tjek der ser at listen er null eller uden indhold");
                orderlines = new ArrayList<>();
                session.setAttribute("samletpris", newOrderline.getPrice());

//            ordrelinjen skal tildeles en id som kun bruges i den p??g??ldende indk??bskurv-sessionscope
                newOrderline.setId(1);
                orderlines.add(newOrderline);
                session.setAttribute("orderlineList", orderlines);
                System.out.println("orderline " + newOrderline.getIngridiensBottom() + "blev oprettet med id " + newOrderline.getId());
                System.out.println("ordrelisten har nu en l??ngde p?? " + orderlines.size());
                return pageToShow;
            }

//      hvis ordrelinjeListen har indhold:   (if (session.getAttribute("orderlineList") != null) )

            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

//        unders??ger om den nye cupcake allerede findes i listen
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
//        hvis der allerede er noget i listen skal ny id v??re ??n st??rre end l??ngden, da f??rste element har id=1
            int newOrderlineId = orderlines.size() + 1;
            newOrderline.setId(newOrderlineId);

            orderlines.add(newOrderline);

//        pris beregnes - s?? vi har den tilg??ngelig hver gang vi tilf??jer til indk??bskurv
            double totalPrice = 0;
            for (Orderline orderline : orderlines) {
                totalPrice = totalPrice + orderline.getPrice();
            }

//        hvis den nytilf??jede cupcake ikke findes i listen, tilf??jes den
            session.setAttribute("samletpris", totalPrice);
            session.setAttribute("orderlineList", orderlines);

            return pageToShow;
        }


        String deleteId = request.getParameter("delete");

//        String incId = request.getParameter("inc");
//        String decId = request.getParameter("dec");

        if (deleteId != null) {
            List<Orderline> orderlines;
            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

            System.out.println("vi n??ede frem med et deleteID" + deleteId);
            for (Orderline orderline : orderlines) {
                if (orderline.getId() == Integer.parseInt(deleteId)) {

                    orderlines.remove(orderline);
                    session.setAttribute("orderlineList", orderlines);
                    System.out.println("ordrelisten har nu en l??ngde p?? " + orderlines.size());

                    break;

                }
            }
//            samlet pris beregnes p?? ny
            double totalPrice = 0;
            for (Orderline orderline : orderlines) {
                totalPrice = totalPrice + orderline.getPrice();
            }

//        hvis den nytilf??jede cupcake ikke findes i listen, tilf??jes den
            session.setAttribute("samletpris", totalPrice);
        }

        return pageToShow;


    }
}
