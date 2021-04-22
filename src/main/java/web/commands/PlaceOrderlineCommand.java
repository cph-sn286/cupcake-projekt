package web.commands;

import business.entities.IngridiensBottom;
import business.entities.IngridiensTop;
import business.entities.Order;
import business.entities.Orderline;
import business.exceptions.UserException;
import business.persistence.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderlineCommand extends CommandProtectedPage {
    public PlaceOrderlineCommand(String pageToShow, String role) {
        super(pageToShow, role);
//        husk at sætte facade ind
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        UserMapper userMapper = new UserMapper(database);
//        skal være via facade

//        beregner pris på ordre som består af enkelt ordrelinje
        int bottomId = Integer.parseInt(request.getParameter("bund"));
        int topId = Integer.parseInt(request.getParameter("top"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        IngridiensBottom ingridiensBottom = userMapper.getIngridiensBottomsById(bottomId);
        IngridiensTop ingridiensTop = userMapper.getIngridiensTopById(topId);

        Orderline orderline = new Orderline(ingridiensBottom, ingridiensTop, quantity);

        List<Orderline> orderlines = null;

        if (session.getAttribute("orderlineList") == null) {
            orderlines=new ArrayList<>();
            orderlines.add(orderline);
        }
        if (session.getAttribute("orderlineList") != null) {
            orderlines= (List<Orderline>) session.getAttribute("orderlineList");
                        orderlines.add(orderline);
        }





//        String[] hobbies = request.getParameterValues("hobby");
//        List<String> hobbyListStrings = null;
//        if (hobbies != null) {
//            hobbyListStrings = Arrays.asList(hobbies);
//        }
//
//        List<Integer> hobbyListIntegers = new ArrayList<>();
//        for (String hobbyListItem : hobbyListStrings) {
//            hobbyListIntegers.add(Integer.parseInt(hobbyListItem));
//        }
//
//        getServletContext().setAttribute("IngridiensBottomList", userMapper.getIngridiensBottomsList());

//        request.setAttribute("hobbies", hobbyListIntegers);

        session.setAttribute("orderlineList", orderlines);


//        for at lave en indkøbskurv skal vi have en liste af ordrelinjer som vi mapper

//        double totalPrice = orderline.getPrice();
//        Order order = new Order(3, "xx:xx", totalPrice);

//        userMapper.insertOrder(order, orderline);


        return pageToShow;
    }
}
