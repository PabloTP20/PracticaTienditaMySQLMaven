package Controller;

import Model.VentasModel;
import Model.InventarioModel;
import View.VentasView;
import Model.Venta;
import javax.swing.*;
import java.util.List;

public class VentasController {
    private VentasModel model;
    private VentasView view;

    public VentasController(InventarioModel inventario) {
        this.model = new VentasModel(inventario);
        this.view = new VentasView(inventario.obtenerProductos());

        // Listener para registrar
        view.getBtnRegistrarVenta().addActionListener(e -> registrarVenta());
        view.getBtnEliminarVenta().addActionListener(e -> eliminarVenta());
        view.getBtnRefrescar().addActionListener(e -> refrescarProductos());
    }

    public VentasView getView() {
        return view;
    }

    private void registrarVenta() {
        try {
            String producto = (String) view.getComboProductos().getSelectedItem();
            int cantidad = Integer.parseInt(view.getTxtCantidad().getText());
            if (model.registrarVenta(producto, cantidad)) {
                JOptionPane.showMessageDialog(view, "Venta registrada correctamente.");
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(view, "Error al registrar venta. Verifica stock.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarVenta() {
        int fila = view.getTablaVentas().getSelectedRow();
        if (fila != -1) {
            int id = (int) view.getModeloTablaVentas().getValueAt(fila, 0);
            if (model.eliminarVenta(id)) {
                JOptionPane.showMessageDialog(view, "Venta eliminada correctamente.");
                actualizarTabla();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona una venta para eliminar.");
        }
    }

    private void refrescarProductos() {
        view.getComboProductos().removeAllItems();
        model.getInventarioModel().obtenerProductos().forEach(p -> view.getComboProductos().addItem(p.getNombre()));
    }

    private void actualizarTabla() {
        List<Venta> ventas = model.obtenerVentas();
        view.actualizarTabla(ventas);
    }
}











