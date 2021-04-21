package web.commands;

import business.entities.Orders;
import business.exceptions.UserException;
import business.services.CupcakeFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCupcakeCommand extends CommandProtectedPage {

    CupcakeFacade cupcakeFacade;

    public ManageCupcakeCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.cupcakeFacade = new CupcakeFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String deleteId = request.getParameter("delete");
        String editId = request.getParameter("edit");
        String update = request.getParameter("update");


        if (deleteId != null) {
            int rowsAffected = cupcakeFacade.deleteOrder(Integer.parseInt(deleteId));
            if (rowsAffected > 0) {
                request.getServletContext().setAttribute("orderList", cupcakeFacade.getAllOrders());
            } else {
                request.setAttribute("error", "Det er forbudt at fjerne en ordrer!!");
            }
        } else if (editId != null) {
            Orders orders = cupcakeFacade.getOrderById(Integer.parseInt(editId));
            request.setAttribute("orderItem", orders);
            return "editsportspage"; // KIG HER!!!
        } else if (update != null) {

            String pickuptime = request.getParameter("pickuptime");
            String order_id = request.getParameter("order_id");
            int rowsInserted = cupcakeFacade.updateOrder((order_id), pickuptime);
            if (rowsInserted == 1) {
                request.getServletContext().setAttribute("orderList", cupcakeFacade.getAllOrders());
            }
            System.out.printf("nyt tidspunkt " + pickuptime + " for order_id = " + order_id );
        }

        return pageToShow;
    }


}
