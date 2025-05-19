package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.model.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));

        HttpSession session = req.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId() == productId);
        }

        resp.sendRedirect("pages/cart.jsp");
    }
}
