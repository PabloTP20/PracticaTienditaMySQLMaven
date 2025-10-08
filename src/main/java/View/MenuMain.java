package View;

import Controller.InventarioController;
import Controller.EmpleadoController;
import Controller.VentasController;
import Controller.LoginController;
import Model.InventarioModel;
import Model.EmpleadoModel;
import Model.AppModel;
import Model.AuthService;
import View.LoginDialog;
import javax.swing.*;
import java.awt.*;

public class MenuMain extends JFrame {
    private InventarioController inventarioController;
    private VentasController ventasController;
    private EmpleadoController empleadoController;
    private JPanel panelContenido;
    private InventarioModel inventarioModel;

    // Nuevos modelos para login
    private AppModel appModel;
    private AuthService authService;

    // Ítems de menú
    private JMenuItem menuItemInventario;
    private JMenuItem menuItemVentas;
    private JMenuItem menuItemEmpleados;
    private JMenuItem menuItemSalir;

    public MenuMain() {
        setTitle("Tienda Equipo 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inventarioModel = new InventarioModel();
        appModel = new AppModel();
        authService = new AuthService();

        crearMenu();

        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);

        // Mostrar el login al iniciar
        mostrarLogin();
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menú");

        // Inventario
        menuItemInventario = new JMenuItem("Inventario");
        menuItemInventario.addActionListener(e -> mostrarInventario());
        menuPrincipal.add(menuItemInventario);

        // Ventas
        menuItemVentas = new JMenuItem("Ventas");
        menuItemVentas.addActionListener(e -> mostrarVentas());
        menuPrincipal.add(menuItemVentas);

        // Empleados (solo visible para admin)
        menuItemEmpleados = new JMenuItem("Empleados");
        menuItemEmpleados.addActionListener(e -> mostrarEmpleados());
        menuPrincipal.add(menuItemEmpleados);

        menuPrincipal.addSeparator();

        // Salir
        menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuPrincipal.add(menuItemSalir);

        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);

        // Desactivar todo hasta el login
        setMenusEnabled(false);
    }

    // Habilita o deshabilita el menú completo
    private void setMenusEnabled(boolean enabled) {
        menuItemInventario.setEnabled(enabled);
        menuItemVentas.setEnabled(enabled);
        menuItemEmpleados.setEnabled(enabled);
        menuItemSalir.setEnabled(enabled);
    }

    // Aplica los permisos según el rol del usuario logueado
    private void aplicarPermisosPorRol() {
        boolean isAdmin = appModel.isAdmin();

        // Solo admin puede ver Empleados
        menuItemEmpleados.setEnabled(isAdmin);

        // Los demás siempre accesibles
        menuItemInventario.setEnabled(true);
        menuItemVentas.setEnabled(true);
        menuItemSalir.setEnabled(true);
    }

    // ---------------- LOGIN ----------------
    private void mostrarLogin() {
        LoginDialog loginDialog = new LoginDialog(this);
        LoginController loginController = new LoginController(appModel, authService, loginDialog, this);

        boolean loggedIn = loginController.showLogin();

        if (loggedIn) {
            aplicarPermisosPorRol();
            setMenusEnabled(true);
        } else {
            System.exit(0);
        }
    }

    // ---------------- VISTAS ----------------
    private void mostrarInventario() {
        if (inventarioController == null) {
            inventarioController = new InventarioController();
        }
        panelContenido.removeAll();
        panelContenido.add(inventarioController.getView().getMainPanel(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarVentas() {
        if (ventasController == null) {
            ventasController = new VentasController(inventarioModel);
        }
        panelContenido.removeAll();
        panelContenido.add(ventasController.getView(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarEmpleados() {
        if (!appModel.isAdmin()) {
            JOptionPane.showMessageDialog(this, "Acceso denegado. Solo el ADMIN puede ingresar a Empleados.", "Permiso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (empleadoController == null) {
            EmpleadoModel model = new EmpleadoModel();
            EmpleadoView view = new EmpleadoView();
            empleadoController = new EmpleadoController(model, view);
        }

        panelContenido.removeAll();
        panelContenido.add(empleadoController.getView().getMainPanel(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}


