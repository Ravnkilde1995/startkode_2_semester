package dat.backend.model.persistence;

import dat.backend.model.entities.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ItemMapper {

    static List<Item> getItems(ConnectionPool connectionPool) {

        List<Item> itemList = new ArrayList<>();

        String sql = "select * from item";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    Boolean done = rs.getBoolean("done");
                    Timestamp created = rs.getTimestamp("created");
                    String username = rs.getString("username");

                    Item newItem = new Item(id, name, done, created, username);
                    itemList.add(newItem);
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;

    }

    public static void toggleDone(int item_id, ConnectionPool connectionPool) {

        String sql = "UPDATE item SET done = 1 - done WHERE item_id = ?";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, item_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Item getItemById(int item_id, ConnectionPool connectionPool) {

        String sql = "select * from item where item_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1, item_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int id = rs.getInt("item_id");
                    String name = rs.getString("name");
                    Boolean done = rs.getBoolean("done");
                    Timestamp created = rs.getTimestamp("created");
                    String username = rs.getString("username");

                    Item newItem = new Item(id, name, done, created, username);
                    return newItem;
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void updateItemName(int item_id, String name, ConnectionPool connectionPool) {

        String sql = "UPDATE item SET name = ? WHERE item_id = ? ";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, item_id);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void toggleItem(int item_id, ConnectionPool connectionPool) {

        String sql = "UPDATE item SET done = (1- done) WHERE item_id = ? ";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, item_id);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
