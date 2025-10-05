package Model;

public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;
    // Constructor vacío
    public Producto() {}
    // Constructor completo
    public Producto(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    // Constructor sin ID (para inserts)
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    // toString para depuración
    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', cantidad=" + cantidad + ", precio=" + precio + "}";
    }
}

