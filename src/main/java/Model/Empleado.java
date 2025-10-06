package Model;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;

    public Empleado(String nombre, String apellido, int edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
    }

    public Empleado(int id, String nombre, String apellido, int edad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " (" + edad + " años, Tel: " + telefono + ")";
    }
}
