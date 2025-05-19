package io.github.salazar.ecommerce.repository.impl;


import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class ProfileRepository implements Repository<Integer, Profile> {

    @Override
    public List<Profile> findAll() {
        List<Profile> list = new ArrayList<>();
        String sql = "SELECT * FROM profile";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToProfile(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<Profile> findById(Integer id) {
        String sql = "SELECT * FROM profile WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToProfile(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Profile save(Profile entity) {
        String sql = "INSERT INTO profile (description) VALUES (?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getDescription());
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
    public Profile update(Profile entity) {
        String sql = "UPDATE profile SET description = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getDescription());
            stmt.setInt(2, entity.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM profile WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Profile mapResultSetToProfile(ResultSet rs) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getInt("id"));
        profile.setDescription(rs.getString("description"));
        return profile;
    }
}
