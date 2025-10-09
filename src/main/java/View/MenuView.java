package View;
import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    private JMenuItem menuItemInventario;
    private JMenuItem menuItemVentas;
    private JMenuItem menuItemEmpleados;
    private JMenuItem menuItemSalir;
    private JPanel panelContenido;
    public MenuView() {
        setTitle("Tienda Equipo 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        crearMenu();
        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);
    }
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menú");
        // Inventario
        menuItemInventario = new JMenuItem("Inventario");
        menuPrincipal.add(menuItemInventario);
        // Ventas
        menuItemVentas = new JMenuItem("Ventas");
        menuPrincipal.add(menuItemVentas);
        // Empleados
        menuItemEmpleados = new JMenuItem("Empleados");
        menuPrincipal.add(menuItemEmpleados);
        menuPrincipal.addSeparator();
        // Salir
        menuItemSalir = new JMenuItem("Salir");
        menuPrincipal.add(menuItemSalir);
        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);
        setMenusEnabled(false);
    }

    //Habilita o deshabilita todo el menu
    public void setMenusEnabled(boolean enabled) {
        menuItemInventario.setEnabled(enabled);
        menuItemVentas.setEnabled(enabled);
        menuItemEmpleados.setEnabled(enabled);
        menuItemSalir.setEnabled(enabled);
    }

    //Aplica permisos basados en roles
    public void aplicarPermisosPorRol(boolean isAdmin) {
        //Sólo el administrador puede acceder a Empleados
        menuItemEmpleados.setEnabled(isAdmin);
        // los demas son accesibles después de iniciar sesión
        menuItemInventario.setEnabled(true);
        menuItemVentas.setEnabled(true);
        menuItemSalir.setEnabled(true);
    }
    // Getters for UI components
    public JMenuItem getMenuItemInventario() {
        return menuItemInventario;
    }
    public JMenuItem getMenuItemVentas() {
        return menuItemVentas;
    }
    public JMenuItem getMenuItemEmpleados() {
        return menuItemEmpleados;
    }
    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }
    public JPanel getPanelContenido() {
        return panelContenido;
    }
}
