package View;

import javax.swing.*;
import java.awt.*;

public class EmpleadoView extends JPanel {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaEmpleados;
    private JButton btnContratar;
    private JButton btnDespedir;
    private JTextField txtNuevoEmpleado;
    private JPanel mainPanel;

    public EmpleadoView() {
        setLayout(new BorderLayout(10, 10));
        mainPanel = new JPanel(new BorderLayout(10, 10));

        // Modelo de datos para la lista
        modeloLista = new DefaultListModel<>();
        listaEmpleados = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaEmpleados);

        // Panel superior
        JLabel lblTitulo = new JLabel("Gestión de Empleados", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Panel inferior con controles
        JPanel panelInferior = new JPanel();
        txtNuevoEmpleado = new JTextField(15);
        btnContratar = new JButton("Contratar empleado");
        btnDespedir = new JButton("Despedir empleado");

        panelInferior.add(new JLabel("Nombre:"));
        panelInferior.add(txtNuevoEmpleado);
        panelInferior.add(btnContratar);
        panelInferior.add(btnDespedir);

        // Armar estructura visual
        mainPanel.add(lblTitulo, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Getters públicos para el controlador
    public JButton getBtnContratar() { return btnContratar; }
    public JButton getBtnDespedir() { return btnDespedir; }
    public JTextField getTxtNuevoEmpleado() { return txtNuevoEmpleado; }
    public JList<String> getListaEmpleados() { return listaEmpleados; }
    public DefaultListModel<String> getModeloLista() { return modeloLista; }
    public JPanel getMainPanel() { return mainPanel; }
}


