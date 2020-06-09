package Servlet.login;

import Service.UserService;
import User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private void redirectFromRole(HttpServletResponse response, String role) throws IOException {
        if ("admin".equals(role)) {
            response.sendRedirect("/admin/read");
        } else {
            response.sendRedirect("/user");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        UserService userService = UserService.getInstance();

        Optional<User> user = userService.findByLogin(login);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                session.setAttribute("user_id", user.get().getId());
                session.setAttribute("user_role", user.get().getRole());

                redirectFromRole(response, user.get().getRole());
                return;
            }
        }
    }
}
