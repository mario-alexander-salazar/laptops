package io.github.salazar.ecommerce.repository.impl;


import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class ProductRepository implements Repository<Integer, Product> {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (id_category, name, quantity, price, picture) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, product.getIdCategory());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setBytes(5, product.getPicture());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                product.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE product SET id_category=?, name=?, quantity=?, price=?, picture=? WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, product.getIdCategory());
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setBytes(5, product.getPicture());
            stmt.setInt(6, product.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setIdCategory(rs.getInt("id_category"));
        product.setName(rs.getString("name"));
        product.setQuantity(rs.getInt("quantity"));
        product.setPrice(rs.getDouble("price"));
        product.setPicture(rs.getBytes("picture"));
        return product;
    }
}
