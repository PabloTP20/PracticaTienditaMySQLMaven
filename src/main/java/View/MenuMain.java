package View;

import Controller.InventarioController;
import Controller.EmpleadoController;
import Controller.VentasController;
import Model.InventarioModel;
import Model.EmpleadoModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuMain extends JFrame {
    private InventarioController inventarioController;
    private VentasController ventasController;
    private EmpleadoController empleadoController;
    private JPanel panelContenido;
    private InventarioModel inventarioModel;

    public MenuMain() {
        setTitle("Tienda Equipo 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inventarioModel = new InventarioModel(); // Compartido entre controladores

        crearMenu();

        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menú");

        // Inventario
        JMenuItem menuItemInventario = new JMenuItem("Inventario");
        menuItemInventario.addActionListener(e -> mostrarInventario());
        menuPrincipal.add(menuItemInventario);

        // Ventas
        JMenuItem menuItemVentas = new JMenuItem("Ventas");
        menuItemVentas.addActionListener(e -> mostrarVentas());
        menuPrincipal.add(menuItemVentas);

        // Empleados
        JMenuItem menuItemEmpleados = new JMenuItem("Empleados");
        menuItemEmpleados.addActionListener(e -> mostrarEmpleados());
        menuPrincipal.add(menuItemEmpleados);

        menuPrincipal.addSeparator();

        // Salir
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuPrincipal.add(menuItemSalir);

        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);
    }

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

