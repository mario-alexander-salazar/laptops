package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.ServiceFactory;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.service.Service;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    private final Service<Integer, Product> productService = ServiceFactory.getProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            resp.sendRedirect("pages/login.jsp");
            return;
        }
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = productService.findById(productId).orElse(null);
        if (product == null) {
            resp.sendRedirect("productByCategory"); // or error page
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(product, quantity));
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("pages/cart.jsp");
    }
}
