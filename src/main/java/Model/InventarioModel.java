package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.Db;

public class InventarioModel {
    // Obtener todos los productos de la BD
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            stmt = conn.prepareStatement("SELECT id, nombre, cantidad, precio FROM producto ORDER BY id");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                );
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productos;
    }
    // Agregar un nuevo producto (adaptado para recibir parámetros individuales)
    public boolean agregarProducto(String nombre, int cantidad, double precio) {
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO producto (nombre, cantidad, precio) VALUES (?, ?, ?)")) {
            stmt.setString(1, nombre);
            stmt.setInt(2, cantidad);
            stmt.setDouble(3, precio);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un producto por ID
    public boolean eliminarProducto(int id) {
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM producto WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar la cantidad de un producto por su nombre
    public boolean actualizarCantidadPorNombre(String nombre, int nuevaCantidad) {
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE producto SET cantidad = ? WHERE nombre = ?")) {
            stmt.setInt(1, nuevaCantidad);
            stmt.setString(2, nombre);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar productos por un término en el nombre (para búsqueda en tiempo real)
    public List<Producto> buscarProductosPorTermino(String termino) {
        List<Producto> productos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            stmt = conn.prepareStatement("SELECT id, nombre, cantidad, precio FROM producto WHERE LOWER(nombre) LIKE ? ORDER BY id");
            stmt.setString(1, "%" + termino + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                );
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productos;
    }
    // Buscar un producto específico por nombre (ya existía, pero lo incluyo para completitud)
    public Producto buscarProductoPorNombre(String nombre) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            stmt = conn.prepareStatement("SELECT id, nombre, cantidad, precio FROM producto WHERE nombre = ?");
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}



