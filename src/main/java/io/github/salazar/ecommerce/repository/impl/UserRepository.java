package io.github.salazar.ecommerce.repository.impl;

import io.github.salazar.ecommerce.db.*;
import io.github.salazar.ecommerce.model.*;
import io.github.salazar.ecommerce.repository.*;

import java.sql.*;
import java.util.*;

public class UserRepository implements Repository<Integer, User> {

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM public.\"user\"";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM public.\"user\" WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO public.\"user\" (id_profile, id_marital_status, name, dni, email, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, user.getIdProfile());
            stmt.setString(2, user.getMaritalStatus());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getDni());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE public.\"user\" SET id_profile=?, id_marital_status=?, name=?, dni=?, email=?, password=? WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getIdProfile());
            stmt.setString(2, user.getMaritalStatus());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getDni());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setInt(7, user.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM public.\"user\" WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setIdProfile(rs.getInt("id_profile"));
        user.setMaritalStatus(rs.getString("marital_status"));
        user.setName(rs.getString("name"));
        user.setDni(rs.getString("dni"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
