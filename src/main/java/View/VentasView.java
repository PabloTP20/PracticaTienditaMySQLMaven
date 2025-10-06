package View;

import Model.Producto;
import Model.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentasView extends JPanel {

    private JComboBox<String> comboProductos;
    private JTextField txtCantidad;
    private JButton btnRegistrarVenta;
    private JButton btnEliminarVenta;
    private JButton btnRefrescar;
    private JTable tablaVentas;
    private DefaultTableModel modeloTablaVentas;

    public VentasView(List<Producto> productos) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Registrar nueva venta"));
        panelSuperior.setBackground(Color.WHITE);

        comboProductos = new JComboBox<>();
        for (Producto p : productos) {
            comboProductos.addItem(p.getNombre());
        }

        txtCantidad = new JTextField();

        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBackground(new Color(0, 153, 76));
        btnRegistrarVenta.setForeground(Color.WHITE);
        btnRegistrarVenta.setFocusPainted(false);

        btnEliminarVenta = new JButton("Eliminar Venta");
        btnEliminarVenta.setBackground(new Color(204, 0, 0));
        btnEliminarVenta.setForeground(Color.WHITE);
        btnEliminarVenta.setFocusPainted(false);

        btnRefrescar = new JButton("Refrescar Productos");
        btnRefrescar.setBackground(new Color(0, 102, 204));
        btnRefrescar.setForeground(Color.WHITE);
        btnRefrescar.setFocusPainted(false);

        panelSuperior.add(new JLabel("Producto:"));
        panelSuperior.add(comboProductos);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(txtCantidad);
        panelSuperior.add(btnRegistrarVenta);
        panelSuperior.add(btnRefrescar);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla de ventas
        modeloTablaVentas = new DefaultTableModel(new String[]{"ID", "Producto", "Cantidad", "Total ($)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // La tabla no es editable
            }
        };
        tablaVentas = new JTable(modeloTablaVentas);
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVentas.setRowHeight(25);
        tablaVentas.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ventas registradas"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(Color.WHITE);
        panelInferior.add(btnEliminarVenta);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Metodo actualizacion
    public void actualizarTabla(List<Venta> ventas) {
        modeloTablaVentas.setRowCount(0);
        for (Venta v : ventas) {
            modeloTablaVentas.addRow(new Object[]{
                    v.getId(),
                    v.getNombreProducto(),
                    v.getCantidadVendida(),
                    String.format("%.2f", v.getTotal())
            });
        }
    }

    // Metodo getters

    public JComboBox<String> getComboProductos() {
        return comboProductos;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JButton getBtnRegistrarVenta() {
        return btnRegistrarVenta;
    }

    public JButton getBtnEliminarVenta() {
        return btnEliminarVenta;
    }

    public JButton getBtnRefrescar() {
        return btnRefrescar;
    }

    public JTable getTablaVentas() {
        return tablaVentas;
    }

    public DefaultTableModel getModeloTablaVentas() {
        return modeloTablaVentas;
    }
}
















