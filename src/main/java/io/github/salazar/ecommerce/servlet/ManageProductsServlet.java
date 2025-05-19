package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.service.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;

@WebServlet("/product")
public class ManageProductsServlet extends HttpServlet {

    private final Service<Integer, Product> productService = ServiceFactory.getProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Optional<Product> product = Optional.empty();
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            product = productService.findById(id);
            product.ifPresent(p -> req.setAttribute("productToEdit", p));
        }

        List<Product> products = productService.findAll();
        req.setAttribute("products", products);
        req.setAttribute("productToEdit", product.orElse(null));
        req.getRequestDispatcher("pages/manageProducts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            Product p = new Product();
            p.setName(req.getParameter("name"));
            p.setIdCategory(Integer.parseInt(req.getParameter("categoryId")));
            p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            p.setPrice(Double.parseDouble(req.getParameter("price")));
            productService.save(p);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.delete(id);
        }else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<Product> existing = productService.findById(id);
            if (existing.isPresent()) {
                Product p = existing.get();
                p.setName(req.getParameter("name"));
                p.setIdCategory(Integer.parseInt(req.getParameter("categoryId")));
                p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                p.setPrice(Double.parseDouble(req.getParameter("price")));
                productService.save(p);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/product");
    }
}
