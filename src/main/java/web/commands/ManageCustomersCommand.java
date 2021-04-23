package web.commands;

import business.entities.Orders;
import business.exceptions.UserException;
import business.persistence.CupcakeMapper;
import business.services.CupcakeFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCustomersCommand extends CommandProtectedPage {

    CupcakeFacade cupcakeFacade;

    public ManageCustomersCommand(String pageToShow, String role) {
        super(pageToShow, role);
        this.cupcakeFacade = new CupcakeFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        CupcakeMapper cupcakeMapper = new CupcakeMapper(database);
        request.getServletContext().setAttribute("userList", cupcakeMapper.getAllCustomers());




        return pageToShow;
    }


}
