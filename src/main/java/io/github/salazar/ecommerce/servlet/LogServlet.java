package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.service.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

@WebServlet("/logs")
public class LogServlet extends HttpServlet {

    private final Service<Integer, Audit> auditService = ServiceFactory.getAuditService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Audit> allLogs = auditService.findAll();

        System.out.println("Logs: " + allLogs);

        req.setAttribute("audits", allLogs);
        req.getRequestDispatcher("pages/logs.jsp").forward(req, resp);
    }
}
