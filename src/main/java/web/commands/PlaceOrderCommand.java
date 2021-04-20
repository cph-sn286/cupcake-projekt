package web.commands;

import business.entities.IngridiensBottom;
import business.exceptions.UserException;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PlaceOrderCommand extends CommandUnprotectedPage{
    public PlaceOrderCommand(String pageToShow) {
        super(pageToShow);
//        husk at sætte facade ind
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();

        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));

//beregn pris via pris som vi har adgang til via applicationscope

//        skal være via facade
        UserMapper userMapper = new UserMapper(database);
        userMapper.insertOrderline(bottomId,topId,4,3,20);

        return pageToShow;
    }
}
