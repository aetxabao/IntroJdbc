package edu.masanz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    public Dao() {
        DbCon.connect(
                "utx_db",
                "proy",
                "password");
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico " +
                "FROM utx_db.items " +
                "ORDER BY id DESC";
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql)) {
                try(ResultSet rs = pst.executeQuery()) {
                    //int columnCount = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        long id = rs.getLong(1);
                        String nombre = rs.getString(2);
                        String desc = rs.getString(3);
                        int precioInicio = rs.getInt(4);
                        String urlImagen = rs.getString(5);
                        String nombreUsuario = rs.getString(6);
                        int estado = rs.getInt(7);
                        boolean historico = rs.getBoolean(8);
                        Item item = new Item(id, nombre, desc, precioInicio, urlImagen, nombreUsuario, estado, historico);
                        items.add(item);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    public Item getItemById(long id) {
        String sql = "SELECT id, nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico " +
                "FROM utx_db.items " +
                "WHERE id = ?";
        Item item = null;
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql)) {
                pst.setLong(1, id);
                try(ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
//                        long id = rs.getLong(1);
                        String nombre = rs.getString(2);
                        String desc = rs.getString(3);
                        int precioInicio = rs.getInt(4);
                        String urlImagen = rs.getString(5);
                        String nombreUsuario = rs.getString(6);
                        int estado = rs.getInt(7);
                        boolean historico = rs.getBoolean(8);
                        item = new Item(id, nombre, desc, precioInicio, urlImagen, nombreUsuario, estado, historico);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return item;
    }

    public boolean existsItem(long idItem) {
        String sql = "SELECT id FROM utx_db.items WHERE id = ?";
        boolean exists = false;
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql)) {
                pst.setLong(1, idItem);
                try(ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }

    public long addItem(Item item) {
        String sql = "INSERT INTO utx_db.items (nombre, `desc`, precioInicio, urlImagen, nombreUsuario, estado, historico) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        long id = 0;
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, item.getNombre());
                pst.setString(2, item.getDesc());
                pst.setInt(3, item.getPrecioInicio());
                pst.setString(4, item.getUrlImagen());
                pst.setString(5, item.getNombreUsuario());
                pst.setInt(6, item.getEstado());
                pst.setBoolean(7, item.isHistorico());
                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    try(ResultSet rs = pst.getGeneratedKeys()) {
                        if (rs.next()) {
                            id = rs.getLong(1);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public boolean updateItem(Item item) {
        String sql = "UPDATE utx_db.items " +
                "SET nombre = ?, `desc` = ?, precioInicio = ?, urlImagen = ?, nombreUsuario = ?, estado = ?, historico = ? " +
                "WHERE id = ?";
        boolean updated = false;
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql)) {
                pst.setString(1, item.getNombre());
                pst.setString(2, item.getDesc());
                pst.setInt(3, item.getPrecioInicio());
                pst.setString(4, item.getUrlImagen());
                pst.setString(5, item.getNombreUsuario());
                pst.setInt(6, item.getEstado());
                pst.setBoolean(7, item.isHistorico());
                pst.setLong(8, item.getId());
                int affectedRows = pst.executeUpdate();
                updated = affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    public boolean deleteItem(long id) {
        String sql = "DELETE FROM utx_db.items WHERE id = ?";
        boolean deleted = false;
        if(DbCon.isConnected()) {
            try (PreparedStatement pst = DbCon.getConnection().prepareStatement(sql)) {
                pst.setLong(1, id);
                int affectedRows = pst.executeUpdate();
                deleted = affectedRows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deleted;
    }

    public void cerrarConexion() {
        DbCon.disconnect();
    }

}
