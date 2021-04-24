package web.commands;

import business.entities.*;
import business.exceptions.UserException;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class EditOrderlineCommand extends CommandProtectedPage {
    public EditOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserFacade userFacade = new UserFacade(database);
        List<Orderline> orderlines;

        String deleteId = request.getParameter("delete");

        if (deleteId != null) {

            orderlines = (List<Orderline>) session.getAttribute("orderlineList");

            System.out.println("vi nåede frem med et deleteID" + deleteId);
            for (Orderline orderline : orderlines) {
                if (orderline.getId() == Integer.parseInt(deleteId)) {
                    System.out.println("vi fandt et element at slette");
                    orderlines.remove(orderline);
                    System.out.println("vi mangler kun at stemple");

                    session.setAttribute("orderlineList", orderlines);

                }
            }
        }

        return pageToShow;
    }


}

