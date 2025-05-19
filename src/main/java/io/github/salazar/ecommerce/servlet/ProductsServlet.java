package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.ServiceFactory;
import io.github.salazar.ecommerce.model.Category;
import io.github.salazar.ecommerce.model.Product;
import io.github.salazar.ecommerce.service.Service;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private final Service<Integer, Product> productService = ServiceFactory.getProductService();
    private final Service<Integer, Category> categoryService = ServiceFactory.getCategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("categoryId");

        List<Product> allProducts = productService.findAll();
        List<Category> allCategories = categoryService.findAll();

        List<Product> filtered = allProducts;

        if (param != null && !param.isEmpty()) {
            int categoryId = Integer.parseInt(param);
            filtered = allProducts.stream()
                    .filter(p -> p.getIdCategory() == categoryId)
                    .collect(Collectors.toList());
        }

        req.setAttribute("categories", allCategories);
        req.setAttribute("products", filtered);
        req.getRequestDispatcher("pages/products.jsp").forward(req, resp);
    }
}
