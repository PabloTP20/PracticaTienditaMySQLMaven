package Model;

import persistence.Db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasModel {

    private InventarioModel inventarioModel;

    public VentasModel(InventarioModel inventarioModel) {
        this.inventarioModel = inventarioModel;
    }

    public InventarioModel getInventarioModel() {
        return inventarioModel;
    }

    public boolean registrarVenta(String producto, int cantidad) {
        if (producto == null || producto.trim().isEmpty() || cantidad <= 0) {
            return false;
        }

        // Buscar producto en inventario
        Producto p = inventarioModel.buscarProductoPorNombre(producto);
        if (p == null) {
            // producto no encontrado
            return false;
        }
        if (p.getCantidad() < cantidad) {
            // stock insuficiente
            return false;
        }

        // Calcular total e insertar venta
        double total = p.getPrecio() * cantidad;
        String sqlVenta = "INSERT INTO ventas (producto, cantidad, total) VALUES (?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlVenta)) {

            ps.setString(1, producto);
            ps.setInt(2, cantidad);
            ps.setDouble(3, total);

            int filas = ps.executeUpdate();
            if (filas <= 0) {
                return false;
            }

            // 3) Actualizar inventario (usa el método existente en InventarioModel)
            int nuevaCantidad = p.getCantidad() - cantidad;
            boolean actualizado = inventarioModel.actualizarCantidadPorNombre(producto, nuevaCantidad);

            return actualizado;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar una venta por id
    public boolean eliminarVenta(int id) {
        String sql = "DELETE FROM ventas WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Listar todas las ventas
    public List<Venta> obtenerVentas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Connection conn = Db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Venta v = new Venta(
                        rs.getInt("id"),
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("total")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}




