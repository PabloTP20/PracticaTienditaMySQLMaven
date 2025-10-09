package Controller;
import Model.AppModel;
import Model.AuthService;
import Model.EmpleadoModel;
import Model.InventarioModel;
import View.EmpleadoView;
import View.LoginDialog;
import View.MenuView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuController {
    private AppModel appModel;
    private InventarioModel inventarioModel;
    private AuthService authService;
    private MenuView view;

    private InventarioController inventarioController;
    private VentasController ventasController;
    private EmpleadoController empleadoController;
    public MenuController() {
        this.appModel = new AppModel();
        this.inventarioModel = new InventarioModel();
        this.authService = new AuthService();
        this.view = new MenuView();
        initialize();
    }
    private void initialize() {
        addActionListeners();
        mostrarLogin();
        view.setVisible(true);
    }
    private void addActionListeners() {
        // Inventario
        view.getMenuItemInventario().addActionListener(e -> mostrarInventario());
        // Ventas
        view.getMenuItemVentas().addActionListener(e -> mostrarVentas());
        // Empleados
        view.getMenuItemEmpleados().addActionListener(e -> mostrarEmpleados());
        // Salir
        view.getMenuItemSalir().addActionListener(e -> System.exit(0));
    }

    // ---------------- LOGIN ----------------
    private void mostrarLogin() {
        LoginDialog loginDialog = new LoginDialog(view);
        LoginController loginController = new LoginController(appModel, authService, loginDialog,view);
        boolean loggedIn = loginController.showLogin();
        if (loggedIn) {
            boolean isAdmin = appModel.isAdmin();
            view.aplicarPermisosPorRol(isAdmin);
            view.setMenusEnabled(true);
        } else {
            System.exit(0);
        }
    }

    // ---------------- VISTAS ----------------
    private void mostrarInventario() {
        if (inventarioController == null) {
            inventarioController = new InventarioController();
        }
        updateContentPanel(inventarioController.getView().getMainPanel());
    }

    private void mostrarVentas() {
        if (ventasController == null) {
            ventasController = new VentasController(inventarioModel);
        }
        updateContentPanel(ventasController.getView());
    }
    private void mostrarEmpleados() {
        if (!appModel.isAdmin()) {
            JOptionPane.showMessageDialog(view, "Acceso denegado. Solo el ADMIN puede ingresar a Empleados.", "Permiso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (empleadoController == null) {
            EmpleadoModel empleadoModel = new EmpleadoModel();
            EmpleadoView empleadoView = new EmpleadoView();
            empleadoController = new EmpleadoController(empleadoModel, empleadoView);
        }
        updateContentPanel(empleadoController.getView().getMainPanel());
    }
    // Metodo auxiliar para actualizar el panel de contenido
    private void updateContentPanel(JComponent newPanel) {
        view.getPanelContenido().removeAll();
        view.getPanelContenido().add(newPanel, BorderLayout.CENTER);
        view.getPanelContenido().revalidate();
        view.getPanelContenido().repaint();
    }

    public MenuView getView() {
        return view;
    }
}
