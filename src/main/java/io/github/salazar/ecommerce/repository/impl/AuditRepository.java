package io.github.salazar.ecommerce.repository.impl;

import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.Audit;
import io.github.salazar.ecommerce.repository.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class AuditRepository implements Repository<Integer, Audit> {


    @Override
    public List<Audit> findAll() {
        List<Audit> audits = new ArrayList<>();
        String sql = "SELECT * FROM audit.audit ORDER BY date DESC";

        try (
                Connection connection = DbConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                audits.add(mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audits;
    }

    private Audit mapRow(ResultSet rs) throws SQLException {
        return new Audit(
                rs.getInt("id"),
                rs.getString("name_table"),
                rs.getString("operation"),
                rs.getString("previous_value"),
                rs.getString("new_value"),
                rs.getTimestamp("date") != null ? rs.getTimestamp("date").toLocalDateTime() : null,
                rs.getString("user"),
                rs.getString("scheme"),
                rs.getObject("activate", Boolean.class),
                rs.getObject("trigger", Boolean.class)
        );
    }

    @Override public Optional<Audit> findById(Integer id) { return Optional.empty(); }
    @Override public Audit save(Audit entity) { throw new UnsupportedOperationException(); }
    @Override public Audit update(Audit entity) { throw new UnsupportedOperationException(); }
    @Override public void delete(Integer id) { throw new UnsupportedOperationException(); }
}
