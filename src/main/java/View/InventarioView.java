package View;
import Controller.InventarioController;
import Model.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class InventarioView{

    private InventarioController controller;
    private JTextField txtBusqueda;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar;
    private JButton btnLlenar;
    private JButton btnLimpiar;
    private JPopupMenu menuContextual;
    private JMenuItem itemEliminar;
    private JPanel mainPanel;
    public InventarioView(InventarioController controller) {
        this.controller = controller;
        crearInterfazPrincipal();
    }
    private void crearInterfazPrincipal() {
        mainPanel = new JPanel(new BorderLayout());
        // Panel para botones (visibles, sin listeners)
        JPanel panelOpciones = new JPanel();
        btnAgregar = new JButton("Agregar/Eliminar Producto Nuevo");
        btnAgregar.setBackground(Color.BLUE);
        btnAgregar.setForeground(Color.WHITE);
        btnLlenar = new JButton("Llenar Inventario");
        btnLlenar.setBackground(Color.BLUE);
        btnLlenar.setForeground(Color.WHITE);
        panelOpciones.add(btnAgregar);
        panelOpciones.add(btnLlenar);
        mainPanel.add(panelOpciones, BorderLayout.NORTH);
        // Panel para buscador (visibles, sin listeners)
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        txtBusqueda = new JTextField(20);
        panelBusqueda.add(txtBusqueda);
        btnLimpiar = new JButton("Limpiar");
        panelBusqueda.add(btnLimpiar);
        mainPanel.add(panelBusqueda, BorderLayout.CENTER);
        // Tabla (visible con scroll)
        String[] columnas = {"ID", "Nombre", "Cantidad", "Precio ($)"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Configurar anchos de columnas
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        // Menú contextual para eliminar (click derecho - visible, sin listeners)
        menuContextual = new JPopupMenu();
        itemEliminar = new JMenuItem("Eliminar");
        menuContextual.add(itemEliminar);
    }
    // Getters para componentes (para que Controller agregue eventos)
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnLlenar() { return btnLlenar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTextField getTxtBusqueda() { return txtBusqueda; }
    public JTable getTablaProductos() { return tablaProductos; }
    public JPopupMenu getMenuContextual() { return menuContextual; }
    public JMenuItem getItemEliminar() { return itemEliminar; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JPanel getMainPanel() { return mainPanel; }

    // Metodo para mostrar diálogo de agregar/eliminar
    public void mostrarDialogoAgregarEliminar() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainPanel), "Agregar/Eliminar Producto", true);
        dialog.setSize(450, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblPrecio = new JLabel("Precio:");
        JTextField txtPrecio = new JTextField();
        JLabel lblCantidad = new JLabel("# Productos Iniciales:");
        JTextField txtCantidad = new JTextField();
        JLabel lblIdEliminar = new JLabel("ID (ID para eliminar):");
        JTextField txtIdEliminar = new JTextField();
        dialog.add(lblNombre);
        dialog.add(txtNombre);
        dialog.add(lblPrecio);
        dialog.add(txtPrecio);
        dialog.add(lblCantidad);
        dialog.add(txtCantidad);
        dialog.add(lblIdEliminar);
        dialog.add(txtIdEliminar);
        JButton btnDialogAgregar = new JButton("Agregar");
        btnDialogAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    mostrarMensaje("Error", "El nombre es requerido.", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double precio = Double.parseDouble(txtPrecio.getText().isEmpty() ? "0" : txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText().isEmpty() ? "0" : txtCantidad.getText());
                controller.procesarAgregar(nombre, cantidad, precio);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error", "Precio y cantidad deben ser números.", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton btnDialogEliminar = new JButton("Eliminar por ID");
        btnDialogEliminar.setBackground(Color.RED);
        btnDialogEliminar.setForeground(Color.WHITE);
        btnDialogEliminar.addActionListener(e -> {
            try {
                int idEliminar = Integer.parseInt(txtIdEliminar.getText());
                controller.procesarEliminar(idEliminar);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error", "ID debe ser un número.", JOptionPane.ERROR_MESSAGE);
            }
        });
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnDialogAgregar);
        panelBotones.add(btnDialogEliminar);
        dialog.add(panelBotones);
        dialog.setVisible(true);
    }
    // Metodo para pedir input de nombre (diálogo visible)
    public String pedirNombreProducto() {
        return JOptionPane.showInputDialog(this, "Ingrese el nombre del producto:");
    }
    // Metodo para mostrar diálogo de actualizar cantidad (UI visible, listener interno llama a Controller)
    public void mostrarDialogoActualizarCantidad(Producto producto) {
        if (producto == null) return;
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainPanel), "Actualizar Inventario - " + producto.getNombre(), true);
        dialog.setSize(250, 150);
        dialog.setLayout(new GridLayout(4, 1, 5, 5));
        JLabel lblActual = new JLabel("Cantidad actual: " + producto.getCantidad());
        dialog.add(lblActual);
        JLabel lblNueva = new JLabel("Nueva cantidad:");
        dialog.add(lblNueva);
        JTextField txtNuevaCantidad = new JTextField();
        dialog.add(txtNuevaCantidad);
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> {
            try {
                int nuevaCantidad = Integer.parseInt(txtNuevaCantidad.getText().trim());
                controller.procesarActualizar(producto.getNombre(), nuevaCantidad);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error", "Cantidad debe ser un número.", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btnActualizar);
        dialog.setVisible(true);
    }
    public void actualizarTabla(List<Producto> productos) {
        modeloTabla.setRowCount(0);  // Limpiar tabla
        for (Producto prod : productos) {
            Object[] fila = {prod.getId(), prod.getNombre(), prod.getCantidad(), String.format("%.2f", prod.getPrecio())};
            modeloTabla.addRow(fila);
        }
    }
    public void mostrarMensaje(String titulo, String mensaje, int tipo) {
        JOptionPane.showMessageDialog(mainPanel, mensaje, titulo, tipo);
    }
    public void limpiarCampoBusqueda() {
        txtBusqueda.setText("");
    }
    public String obtenerTerminoBusqueda() {
        return txtBusqueda.getText();
    }
    // Metodo para mostrar confirmación (diálogo visible, retorna boolean)
    public boolean mostrarConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(mainPanel, mensaje, titulo, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
