package Model;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;

    // 🔹 Constructor completo (para obtener desde la base de datos)
    public Empleado(int id, String nombre, String apellido, int edad, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
    }

    // 🔹 Constructor sin ID (para nuevos empleados)
    public Empleado(String nombre, String apellido, int edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
    }

    // 🔹 Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // 🔹 Representación en texto (útil para depuración)
    @Override
    public String toString() {
        return id + " - " + nombre + " " + apellido + " (" + edad + " años, Tel: " + telefono + ")";
    }
}

