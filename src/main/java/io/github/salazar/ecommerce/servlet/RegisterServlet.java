package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.ServiceFactory;
import io.github.salazar.ecommerce.model.User;
import io.github.salazar.ecommerce.service.Service;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final Service<Integer, User> userService = ServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String dni = req.getParameter("dni");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String maritalStatusId = req.getParameter("maritalStatus");
        int profileId = Integer.parseInt(req.getParameter("profileId"));

        // Check if user already exists
        List<User> users = userService.findAll();
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                req.setAttribute("error", "User already registered with this email.");
                req.getRequestDispatcher("pages/register.jsp").forward(req, resp);
                return;
            }
        }

        User user = new User();
        user.setName(name);
        user.setDni(dni);
        user.setEmail(email);
        user.setPassword(password);
        user.setMaritalStatus(maritalStatusId);
        user.setIdProfile(profileId);

        userService.save(user);
        resp.sendRedirect("pages/login.jsp");
    }
}
