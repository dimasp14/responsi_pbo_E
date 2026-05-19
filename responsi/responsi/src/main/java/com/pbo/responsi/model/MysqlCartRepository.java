package com.pbo.responsi.model;

import com.pbo.responsi.database.DatabaseConnection;
import com.pbo.responsi.dto.CartItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCartRepository implements CartRepository {

    @Override
    public List<CartItemDTO> findAll() {

        List<CartItemDTO> items = new ArrayList<>();

        String sql = "SELECT * FROM cart_items";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                items.add(new CartItemDTO(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public void save(CartItemDTO item) {

        String sql =
                "INSERT INTO cart_items(name, price, quantity) VALUES (?, ?, ?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuantity(String name, int newQty) {

        String sql =
                "UPDATE cart_items SET quantity=? WHERE name=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, newQty);
            ps.setString(2, name);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {

        String sql =
                "DELETE FROM cart_items WHERE name=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}