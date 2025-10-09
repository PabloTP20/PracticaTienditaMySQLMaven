package Controller;

import Model.Empleado;
import Model.EmpleadoModel;
import View.EmpleadoView;

import javax.swing.*;
import java.util.List;

public class EmpleadoController {
    private final EmpleadoModel model;
    private final EmpleadoView view;

    // Constructor
    public EmpleadoController(EmpleadoModel model, EmpleadoView view) {
        this.model = model;
        this.view = view;

        // Primero pedimos inicio de sesión
        if (!iniciarSesion()) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión cancelado. Cerrando programa.");
            System.exit(0);
        }

        // Cargamos lista de empleados
        cargarEmpleados();

        // Configuramos eventos
        view.getBtnContratar().addActionListener(e -> contratarEmpleado());
        view.getBtnDespedir().addActionListener(e -> despedirEmpleado());
    }

    // Metodo de inicio de sesión
    private boolean iniciarSesion() {
        String usuario = JOptionPane.showInputDialog(null, "Ingrese su usuario:");
        if (usuario == null) return false; // usuario canceló

        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(
                null,
                passwordField,
                "Ingrese su contraseña:",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String contrasena = new String(passwordField.getPassword());

            // Aquí podrías validar contra base de datos si lo deseas
            if (usuario.equals("admin") && contrasena.equals("1234")) {
                JOptionPane.showMessageDialog(null, "✅ Bienvenido " + usuario);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "❌ Usuario o contraseña incorrectos.");
                return iniciarSesion(); // vuelve a pedir
            }
        } else {
            return false; // cancelado
        }
    }

    // Cargar empleados desde el modelo a la lista visual
    private void cargarEmpleados() {
        List<Empleado> empleados = model.obtenerEmpleados();
        DefaultListModel<String> lista = view.getModeloLista();
        lista.clear();
        for (Empleado emp : empleados) {
            lista.addElement(emp.getId() + " - " + emp.getNombre() + " " + emp.getApellido()+ "{Edad:"+emp.getEdad()+", Telefono:"+emp.getTelefono()+"}");
        }
    }

    // Contratar nuevo empleado
    private void contratarEmpleado() {
        String nombre = view.getTxtNuevoEmpleado().getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese al menos el nombre del empleado.");
            return;
        }

        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del empleado:");
        if (apellido == null || apellido.trim().isEmpty()) return;

        String edadStr = JOptionPane.showInputDialog("Ingrese la edad del empleado:");
        if (edadStr == null) return;
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad inválida.");
            return;
        }

        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del empleado:");
        if (telefono == null || telefono.trim().isEmpty()) return;

        Empleado nuevo = new Empleado(0, nombre, apellido, edad, telefono);
        model.agregarEmpleado(nuevo);
        cargarEmpleados();
        view.getTxtNuevoEmpleado().setText("");
        JOptionPane.showMessageDialog(null, "✅ Empleado contratado con éxito.");
    }

    // Despedir empleado seleccionado
    private void despedirEmpleado() {
        String seleccionado = view.getListaEmpleados().getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado para despedir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Seguro que desea despedir a " + seleccionado + "?",
                "Confirmar despido",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Extrae el ID (parte antes del primer espacio o guion)
                int id = Integer.parseInt(seleccionado.split(" - ")[0]);
                model.eliminarEmpleado(id);
                cargarEmpleados();
                JOptionPane.showMessageDialog(null, "Empleado despedido correctamente.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "No se pudo identificar el ID del empleado.");
            }
        }
    }

    public EmpleadoView getView() {
        return view;
    }
}



