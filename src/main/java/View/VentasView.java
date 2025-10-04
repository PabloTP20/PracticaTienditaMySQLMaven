package View;

import Controller.VentasController;
import Model.Producto;
import Model.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentasView {
    private VentasController controller;
    private JComboBox<String> comboProductos;
    private JTextField txtCantidad;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;
    private JPanel mainPanel;

    public VentasView(VentasController controller, List<Producto> productos) {
        mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());

        // 🔹 Panel superior
        JPanel panelTop = new JPanel(new GridLayout(3, 2, 10, 10));
        panelTop.setBorder(BorderFactory.createTitledBorder("Registrar Venta"));

        comboProductos = new JComboBox<>();
        for (Producto p : productos) {
            comboProductos.addItem(p.getNombre());
        }

        txtCantidad = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Venta");
        JButton btnEliminar = new JButton("Eliminar Venta");

        panelTop.add(new JLabel("Producto:"));
        panelTop.add(comboProductos);
        panelTop.add(new JLabel("Cantidad:"));
        panelTop.add(txtCantidad);
        panelTop.add(btnRegistrar);
        panelTop.add(btnEliminar);

        // 🔹 Tabla de ventas
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Producto", "Cantidad", "Total ($)"}, 0);
        tablaVentas = new JTable(modeloTabla);

        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        mainPanel.add(panel);

        // 🔹 Eventos
        btnRegistrar.addActionListener(e -> {
            String producto = (String) comboProductos.getSelectedItem();
            int cantidad;
            try {
                cantidad = Integer.parseInt(txtCantidad.getText());
            } catch (NumberFormatException ex) {
                mostrarMensaje("Error", "Ingrese una cantidad válida", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.registrarVenta(producto, cantidad);
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaVentas.getSelectedRow();
            if (fila >= 0) {
                int id = (int) modeloTabla.getValueAt(fila, 0);
                controller.eliminarVenta(id);
            } else {
                mostrarMensaje("Error", "Seleccione una venta para eliminar", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void actualizarVentas(List<Venta> ventas) {
        modeloTabla.setRowCount(0);
        for (Venta v : ventas) {
            modeloTabla.addRow(new Object[]{v.getId(), v.getNombreProducto(), v.getCantidadVendida(), v.getTotal()});
        }
    }

    public void mostrarMensaje(String titulo, String mensaje, int tipo) {
        JOptionPane.showMessageDialog(mainPanel, mensaje, titulo, tipo);
    }
    public JPanel getMainPanel() { return mainPanel; }
}













