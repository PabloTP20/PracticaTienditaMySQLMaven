package Model;

import java.util.ArrayList;
import java.util.List;

public class VentasModel {
    private ArrayList<Venta> ventas;
    private InventarioModel inventario;
    private int nextId;

    public VentasModel(InventarioModel inventario) {
        this.inventario = inventario;
        this.ventas = new ArrayList<>();
        this.nextId = 1;
    }

    public boolean registrarVenta(String nombreProducto, int cantidad) {
        Producto producto = inventario.buscarProductoPorNombre(nombreProducto);

        if (producto == null || producto.getCantidad() < cantidad) {
            return false;
        }

        producto.setCantidad(producto.getCantidad() - cantidad);

        double total = producto.getPrecio() * cantidad;
        Venta venta = new Venta(nextId++, nombreProducto, cantidad, total);

        ventas.add(venta);
        return true;
    }

    public boolean eliminarVenta(int idVenta) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getId() == idVenta) {
                ventas.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Venta> obtenerVentas() {
        return new ArrayList<>(ventas);
    }
}

