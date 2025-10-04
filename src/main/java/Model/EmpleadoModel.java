package Model;

import java.util.ArrayList;

public class EmpleadoModel {
    private ArrayList<Empleado> empleados = new ArrayList<>();

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void eliminarEmpleado(int index) {
        if (index >= 0 && index < empleados.size()) {
            empleados.remove(index);
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}