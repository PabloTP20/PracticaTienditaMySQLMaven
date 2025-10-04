package Model;

public class Venta {
    private int id;
    private String nombreProducto;
    private int cantidadVendida;
    private double total;

    public Venta(int id, String nombreProducto, int cantidadVendida, double total) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
        this.total = total;
    }

    public int getId() { return id; }
    public String getNombreProducto() { return nombreProducto; }
    public int getCantidadVendida() { return cantidadVendida; }
    public double getTotal() { return total; }
}








