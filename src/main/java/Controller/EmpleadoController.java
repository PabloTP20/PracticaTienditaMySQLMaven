package Controller;

import Model.Empleado;
import Model.EmpleadoModel;
import View.EmpleadoView;
import View.InventarioView;

import javax.swing.*;
import java.util.ArrayList;

public class EmpleadoController {
    private EmpleadoModel model;
    private EmpleadoView view;

    public EmpleadoController(EmpleadoModel model, EmpleadoView view) {
        this.model = model;
        this.view = view;

        view.getBtnAgregar().addActionListener(e -> agregarEmpleado());
        view.getBtnDespedir().addActionListener(e -> despedirEmpleado());

        cargarLista();
    }

    private void agregarEmpleado() {
        String nombre = JOptionPane.showInputDialog(view, "Ingrese el nombre:");
        if (nombre == null || nombre.isEmpty()) return;

        String apellido = JOptionPane.showInputDialog(view, "Ingrese el apellido:");
        if (apellido == null || apellido.isEmpty()) return;

        String edadStr = JOptionPane.showInputDialog(view, "Ingrese la edad:");
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Edad inválida.");
            return;
        }

        String telefono = JOptionPane.showInputDialog(view, "Ingrese el teléfono:");
        if (telefono == null || telefono.isEmpty()) return;

        Empleado emp = new Empleado(nombre, apellido, edad, telefono);
        model.agregarEmpleado(emp);
        cargarLista();
    }

    private void despedirEmpleado() {
        int index = view.getListaEmpleados().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione un empleado para despedir.");
            return;
        }

        ArrayList<Empleado> empleados = model.obtenerEmpleados();
        int id = empleados.get(index).getId();
        model.eliminarEmpleado(id);
        cargarLista();
    }

    private void cargarLista() {
        view.getModeloLista().clear();
        for (Empleado e : model.obtenerEmpleados()) {
            view.getModeloLista().addElement(e.toString());
        }
    }

    public EmpleadoView getView() {
        return view;
    }
}
