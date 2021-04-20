package web.commands;

import business.entities.Orderline;
import business.entities.User;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterOrderline extends CommandProtectedPage {
    private UserFacade userFacade;
    private Orderline orderline;
    User user;

    public RegisterOrderline(String pageToShow, String role) {
        super(pageToShow, role);
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        int ingridiensBottomId = Integer.parseInt(request.getParameter("bottomId"));
        int ingridiensTopId = Integer.parseInt(request.getParameter("topId"));
        int quantity = Integer.parseInt(request.getParameter("amount"));
        double price = Double.parseDouble(request.getParameter("price"));

        Orderline orderline = userFacade.makeCupcakeToOrderline(ingridiensBottomId, ingridiensTopId, quantity, price);
        HttpSession session = request.getSession();

        session.setAttribute("bottomId", ingridiensBottomId);
        session.setAttribute("topId", ingridiensTopId);
        session.setAttribute("orderId", orderline.getOrderId());
        session.setAttribute("quantity", quantity);
        session.setAttribute("price", price);
        return user.getRole() + "page";

    }
}
