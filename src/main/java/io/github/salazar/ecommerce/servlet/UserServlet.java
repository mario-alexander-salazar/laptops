package io.github.salazar.ecommerce.servlet;

import io.github.salazar.ecommerce.factory.ServiceFactory;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.service.Service;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final Service<Integer, User> userService = ServiceFactory.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Optional<User> user = userService.findById(id);
            user.ifPresent(u -> req.setAttribute("userToEdit", u));
        }

        List<User> users = userService.findAll();
        List<Profile> profiles = ServiceFactory.getProfileService().findAll();
        Map<Integer, Profile> profileMap = profiles.stream()
                .collect(Collectors.toMap(Profile::getId, p -> p));
        List<UserProfile> userProfiles = users.stream()
                .map(u -> {
                    Profile profile = profileMap.get(u.getIdProfile());
                    return new UserProfile(
                            u.getId(),
                            profile != null ? profile.getDescription() : "Sin perfil",
                            u.getName(),
                            u.getMaritalStatus(),
                            u.getDni(),
                            u.getEmail()
                    );
                })
                .collect(Collectors.toList());
        req.setAttribute("users", userProfiles);
        req.getRequestDispatcher("pages/manageUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setDni(req.getParameter("dni"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            user.setMaritalStatus(req.getParameter("maritalStatus"));
            user.setIdProfile(Integer.parseInt(req.getParameter("profileId")));
            userService.save(user);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            userService.delete(id);
        }

        resp.sendRedirect(req.getContextPath() + "/user");
    }
}
