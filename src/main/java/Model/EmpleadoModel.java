package Model;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadoModel {

    public void agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, apellido, edad, telefono) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setInt(3, empleado.getEdad());
            ps.setString(4, empleado.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al agregar empleado: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar empleado: " + e.getMessage());
        }
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getString("telefono")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar empleados: " + e.getMessage());
        }
        return lista;
    }
}
