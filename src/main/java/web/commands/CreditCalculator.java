package web.commands;

import business.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class CreditCalculator extends CommandProtectedPage {
    public CreditCalculator(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Double credit = 0.0;
        if (request.getParameter("credit") != null) {
            credit = Double.parseDouble(request.getParameter("credit"));
        }
        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");
        String userEmail = user.getEmail();
        Double usersaldo = user.getSaldo();
        Double nysaldo = usersaldo + credit;
        try (Connection connection = database.connect()) {
            String sql = "UPDATE users SET saldo = ? WHERE email = email";
            try (
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, nysaldo);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setSaldo(nysaldo);
        session.setAttribute("saldo", nysaldo);
        session.setAttribute("user", user);
        return pageToShow;
    }
}
