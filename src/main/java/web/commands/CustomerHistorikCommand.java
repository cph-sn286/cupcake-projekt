package web.commands;

import business.entities.Orders;
import business.entities.User;
import business.exceptions.UserException;
import business.services.CupcakeFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerHistorikCommand extends CommandProtectedPage {

    CupcakeFacade cupcakeFacade;
    User user;

    public CustomerHistorikCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.cupcakeFacade = new CupcakeFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        request.getServletContext().setAttribute("userList", cupcakeFacade.getAllCustomers());

        String customerHistorik = request.getParameter("seeCustomer");


        if (customerHistorik != null) {
            request.getServletContext().setAttribute("userList", cupcakeFacade.getCustomerById(user.getId()));
            System.out.println("kommer vi her til ?");
        } else {
            request.setAttribute("error", "Det er forbudt at fjerne kunder som har penge på saldoen");
        }
        return pageToShow;
    }

}
