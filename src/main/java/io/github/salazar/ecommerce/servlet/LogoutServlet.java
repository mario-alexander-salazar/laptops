package io.github.salazar.ecommerce.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Get existing session, don’t create a new one
        if (session != null) {
            session.invalidate(); // Destroy session
        }
        resp.sendRedirect("pages/login.jsp"); // Redirect to login page
    }
}
