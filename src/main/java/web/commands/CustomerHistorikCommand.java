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

        String userIdString = request.getParameter("RedigerBruger");
        List<Orders> orderListBruger = null;

        if (userIdString != null) {
            user = cupcakeFacade.getCustomerById(Integer.parseInt(userIdString));
            System.out.println("vi trykkede på knap. og vi har userid " + Integer.parseInt(userIdString));
            request.setAttribute("valgtBruger", user);

            orderListBruger = cupcakeFacade.getOrderlistByUserId(Integer.parseInt(userIdString));
            System.out.println(orderListBruger.size());
            request.setAttribute("brugerOrdreliste", orderListBruger);


//            for (int i = 0; i < order.getOrder_id(); i++) {
//            }


        } else {
            request.setAttribute("error", "Det er forbudt at fjerne kunder som har penge på saldoen");
        }
        return pageToShow;
    }

}
