package Controller;

import Model.InventarioModel;
import Model.VentasModel;
import View.InventarioView;
//import View.VentasView;
import Model.Venta;

import javax.swing.*;
import java.util.List;
/*
public class VentasController {
    private VentasModel model;
    private VentasView view;

    public VentasController(InventarioModel inventario) {
        this.model = new VentasModel(inventario);
        this.view = new VentasView(this, inventario.obtenerTodosProductos());
    }

    public void registrarVenta(String producto, int cantidad) {
        if (model.registrarVenta(producto, cantidad)) {
            view.mostrarMensaje("Éxito", "Venta registrada correctamente.", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } else {
            view.mostrarMensaje("Error", "No hay suficiente stock o el producto no existe.", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarVenta(int idVenta) {
        if (model.eliminarVenta(idVenta)) {
            view.mostrarMensaje("Éxito", "Venta eliminada correctamente.", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
        } else {
            view.mostrarMensaje("Error", "No se pudo eliminar la venta.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        List<Venta> ventas = model.obtenerVentas();
        view.actualizarVentas(ventas);
    }
    public VentasView getView() {
        return view;
    }
}



*/







