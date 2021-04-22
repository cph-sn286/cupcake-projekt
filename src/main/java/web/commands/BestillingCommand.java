package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BestillingCommand extends CommandUnprotectedPage{

    public BestillingCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        Double topPris = 0.0;
        Double bundPris = 0.0;
        Double cupcakePris = 0.0;

        return pageToShow;
    }
}
