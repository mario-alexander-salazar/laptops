package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.model.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            resp.sendRedirect("pages/login.jsp");
            return;
        }
        String cardNumber = req.getParameter("cardNumber");
        String expiry = req.getParameter("expiry");
        String cvv = req.getParameter("cvv");

        if (!cardNumber.matches("\\d{16}") ||
            !expiry.matches("\\d{2}/\\d{2}") ||
            !cvv.matches("\\d{3}")) {

            req.setAttribute("error", "Invalid payment data.");
            req.getRequestDispatcher("pages/checkout.jsp").forward(req, resp);
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("pages/cart.jsp");
            return;
        }

        session.removeAttribute("cart"); // clear the cart
        resp.sendRedirect("pages/thankyou.jsp");
    }
}
