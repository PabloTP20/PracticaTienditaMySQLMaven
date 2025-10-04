package View;
import Controller.InventarioController;
import Controller.VentasController;
import Controller.EmpleadoController;
import Model.InventarioModel;
import Model.EmpleadoModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuMain extends JFrame{
    private InventarioController inventarioController;
    private VentasController ventasController; // Declarar VentasController
    private EmpleadoController empleadoController; // Declarar EmpleadoController
    private JPanel panelContenido;
    private InventarioModel inventarioModel; // Instancia de InventarioModel para compartir
    public MenuMain() {
        setTitle("Tienda Equipo 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        inventarioModel = new InventarioModel(); // Inicializar InventarioModel aquí para compartir
        crearMenu();
        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);
    }
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuPrincipal = new JMenu("Menu");
        // Opción de Inventario
        JMenuItem menuItemInventario = new JMenuItem("Inventario");
        menuItemInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });
        menuPrincipal.add(menuItemInventario);
        // Opción de Ventas
        JMenuItem menuItemVentas = new JMenuItem("Ventas");
        menuItemVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentas();
            }
        });
        menuPrincipal.add(menuItemVentas);
        // Opción de Empleados
        JMenuItem menuItemEmpleados = new JMenuItem("Empleados");
        menuItemEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEmpleados();
            }
        });
        menuPrincipal.add(menuItemEmpleados);
        menuPrincipal.addSeparator(); // Separador para la opción de salir
        // Opción de Salir
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Termina la aplicación
            }
        });
        menuPrincipal.add(menuItemSalir);
        menuBar.add(menuPrincipal);
        setJMenuBar(menuBar);
    }
    // Metodo para mostrar el panel del inventario en el panelContenido
    private void mostrarInventario() {
        if (inventarioController == null) {
            inventarioController = new InventarioController();
        }
        panelContenido.removeAll();
        panelContenido.add(inventarioController.getView().getMainPanel(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
    // Metodo para mostrar la vista de Ventas
    private void mostrarVentas() {
        if (ventasController == null) {
            // Se pasa el mismo inventarioModel para que Ventas pueda interactuar con el stock
            ventasController = new VentasController(inventarioModel);
        }
        panelContenido.removeAll();
        panelContenido.add(ventasController.getView().getMainPanel(), BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
    // Metodo para mostrar la vista de Empleados
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
