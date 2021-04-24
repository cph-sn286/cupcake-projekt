package web.commands;

import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CupcakeFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CustomerHistorikCommand extends CommandProtectedPage {

    CupcakeFacade cupcakeFacade;
    UserFacade userFacade;
    User user;
    Orders order;

    public CustomerHistorikCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.cupcakeFacade = new CupcakeFacade(database);
        this.userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String customerHistorik = request.getParameter("RedigerBruger");


        if (customerHistorik != null) {
            user = cupcakeFacade.getCustomerById(Integer.parseInt(customerHistorik));
            request.setAttribute("valgtBruger", user);

            order = cupcakeFacade.getOrderByUserId(Integer.parseInt(customerHistorik));
                request.setAttribute("brugerOrdre", order);
                System.out.println(order.getOrder_id());


//            for (int i = 0; i < order.getOrder_id(); i++) {
//            }


        } else {
            request.setAttribute("error", "Det er forbudt at fjerne kunder som har penge på saldoen");
        }
        return pageToShow;
    }

}
