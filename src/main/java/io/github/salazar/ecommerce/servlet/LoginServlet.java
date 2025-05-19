package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.service.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final Service<Integer, User> userService = ServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        List<User> users = userService.findAll();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user);
                resp.sendRedirect("index.jsp");
                return;
            }
        }

        req.setAttribute("error", "Invalid email or password.");
        req.getRequestDispatcher("pages/login.jsp").forward(req, resp);
    }
}
