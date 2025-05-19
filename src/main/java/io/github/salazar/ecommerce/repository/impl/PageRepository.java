package io.github.salazar.ecommerce.repository.impl;

import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class PageRepository implements Repository<Integer, Page> {

    @Override
    public List<Page> findAll() {
        List<Page> list = new ArrayList<>();
        String sql = "SELECT * FROM page";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToPage(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<Page> findById(Integer id) {
        String sql = "SELECT * FROM page WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToPage(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Page save(Page entity) {
        String sql = "INSERT INTO page (description, path) VALUES (?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getDescription());
            stmt.setString(2, entity.getPath());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                entity.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Page update(Page entity) {
        String sql = "UPDATE page SET description = ?, path = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getDescription());
            stmt.setString(2, entity.getPath());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM page WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Page mapResultSetToPage(ResultSet rs) throws SQLException {
        Page page = new Page();
        page.setId(rs.getInt("id"));
        page.setDescription(rs.getString("description"));
        page.setPath(rs.getString("path"));
        return page;
    }
}
