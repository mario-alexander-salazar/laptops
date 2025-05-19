package io.github.salazar.ecommerce.repository.impl;

import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class PageProfileRepository implements Repository<Integer, PageProfile> {

    @Override
    public List<PageProfile> findAll() {
        List<PageProfile> list = new ArrayList<>();
        String sql = "SELECT * FROM page_profile";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToPageProfile(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<PageProfile> findById(Integer id) {
        String sql = "SELECT * FROM page_profile WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToPageProfile(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public PageProfile save(PageProfile entity) {
        String sql = "INSERT INTO page_profile (id_profile, id_page) VALUES (?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, entity.getIdProfile());
            stmt.setInt(2, entity.getIdPage());
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
    public PageProfile update(PageProfile entity) {
        String sql = "UPDATE page_profile SET id_profile = ?, id_page = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getIdProfile());
            stmt.setInt(2, entity.getIdPage());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM page_profile WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PageProfile mapResultSetToPageProfile(ResultSet rs) throws SQLException {
        PageProfile pp = new PageProfile();
        pp.setId(rs.getInt("id"));
        pp.setIdProfile(rs.getInt("id_profile"));
        pp.setIdPage(rs.getInt("id_page"));
        return pp;
    }
}
