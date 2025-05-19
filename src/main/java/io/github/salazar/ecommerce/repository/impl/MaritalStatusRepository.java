package io.github.salazar.ecommerce.repository.impl;

import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class MaritalStatusRepository implements Repository<Integer, MaritalStatus> {

    @Override
    public List<MaritalStatus> findAll() {
        List<MaritalStatus> list = new ArrayList<>();
        String sql = "SELECT * FROM marital_status";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToMaritalStatus(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<MaritalStatus> findById(Integer id) {
        String sql = "SELECT * FROM marital_status WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToMaritalStatus(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public MaritalStatus save(MaritalStatus entity) {
        String sql = "INSERT INTO marital_status (description) VALUES (?)";

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
    public MaritalStatus update(MaritalStatus entity) {
        String sql = "UPDATE marital_status SET description = ? WHERE id = ?";

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
        String sql = "DELETE FROM marital_status WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MaritalStatus mapResultSetToMaritalStatus(ResultSet rs) throws SQLException {
        MaritalStatus status = new MaritalStatus();
        status.setId(rs.getInt("id"));
        status.setDescription(rs.getString("description"));
        return status;
    }
}
