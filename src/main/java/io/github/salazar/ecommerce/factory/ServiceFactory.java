package io.github.salazar.ecommerce.factory;


import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.impl.*;
import io.github.salazar.ecommerce.service.*;
import io.github.salazar.ecommerce.service.impl.*;

public class ServiceFactory {

    private static final UserService userService = new UserService();
    private static final ProductService productService = new ProductService();
    private static final CategoryService categoryService = new CategoryService();
    private static final ProfileService profileService = new ProfileService();

    private ServiceFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Service<Integer, User> getUserService() {
        return userService;
    }

    public static Service<Integer, Product> getProductService() {
        return productService;
    }

    public static Service<Integer, Category> getCategoryService() {
        return categoryService;
    }


    public static Service<Integer, Profile> getProfileService() {
        return profileService;
    }

    public static Service<Integer, Audit> getAuditService() {
        return new AuditService();
    }
}
