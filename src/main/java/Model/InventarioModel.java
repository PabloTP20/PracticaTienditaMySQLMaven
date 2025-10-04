package Model;

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private ArrayList<Producto> productos;  // Lista de productos
    private int nextId;

    public InventarioModel() {
        this.productos = new ArrayList<>();
        this.nextId = 1;

        // 🚀 Productos precargados al iniciar
        agregarProducto("Café", 100, 45.50);
        agregarProducto("Maruchan", 200, 12.00);
        agregarProducto("Marias", 150, 30.00);
        agregarProducto("Sabritas", 80, 18.50);
        agregarProducto("Savile", 50, 25.00);
    }

    public boolean agregarProducto(String nombre, int cantidad, double precio) {
        Producto producto = new Producto(nextId, nombre, cantidad, precio);
        productos.add(producto);
        nextId++;
        return true;
    }

    public boolean eliminarProductoPorId(int idProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == idProducto) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarCantidadPorNombre(String nombre, int nuevaCantidad) {
        for (Producto prod : productos) {
            if (prod.getNombre().equalsIgnoreCase(nombre)) {
                prod.setCantidad(nuevaCantidad);
                return true;
            }
        }
        return false;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto prod : productos) {
            if (prod.getNombre().equalsIgnoreCase(nombre)) {
                return prod;
            }
        }
        return null;
    }

    public List<Producto> obtenerTodosProductos() {
        return new ArrayList<>(productos);  // Retorna copia
    }

    public List<Producto> buscarProductosPorTermino(String termino) {
        if (termino == null || termino.trim().isEmpty()) {
            return obtenerTodosProductos();
        }
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(termino.toLowerCase())) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}


