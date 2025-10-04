package Controller;

import Model.Empleado;
import Model.EmpleadoModel;
import View.EmpleadoView;
import View.InventarioView;

import javax.swing.*;

public class EmpleadoController {
    private EmpleadoModel model;
    private EmpleadoView view;

    public EmpleadoController(EmpleadoModel model, EmpleadoView view) {
        this.model = model;
        this.view = view;

        // Eventos
        view.getBtnAgregar().addActionListener(e -> agregarEmpleado());
        view.getBtnDespedir().addActionListener(e -> despedirEmpleado());
    }

    private void agregarEmpleado() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
        if (nombre == null || nombre.isEmpty()) return;

        String apellido = JOptionPane.showInputDialog( "Ingrese el apellido del empleado:");
        if (apellido == null || apellido.isEmpty()) return;

        String edadStr = JOptionPane.showInputDialog("Ingrese la edad:");
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Ingrese una cantidad para Edad");
            return;
        }

        String telefono = JOptionPane.showInputDialog("Ingrese el número de teléfono:");
        if (telefono == null || telefono.isEmpty()) return;

        Empleado emp = new Empleado(nombre, apellido, edad, telefono);
        model.agregarEmpleado(emp);
        view.getModeloLista().addElement(emp.toString());
    }

    private void despedirEmpleado() {
        int index = view.getListaEmpleados().getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Seleccione un empleado para despedir.");
            return;
        }

        model.eliminarEmpleado(index);
        view.getModeloLista().remove(index);
    }
    public EmpleadoView getView() {
        return view;
    }
}
