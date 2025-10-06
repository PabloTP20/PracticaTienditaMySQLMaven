package View;

import Model.Empleado;
import Model.EmpleadoModel;
import javax.swing.*;
import java.awt.*;

public class EmpleadoView extends JPanel {
    private DefaultListModel<String> modeloLista;
    private JList<String> listaEmpleados;
    private JButton btnAgregar;
    private JButton btnDespedir;
    private JPanel mainPanel;

    public EmpleadoView() {
        setLayout(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());

        modeloLista = new DefaultListModel<>();
        listaEmpleados = new JList<>(modeloLista);
        btnAgregar = new JButton("Agregar empleado");
        btnDespedir = new JButton("Despedir empleado");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnDespedir);

        mainPanel.add(new JScrollPane(listaEmpleados), BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnDespedir() { return btnDespedir; }
    public JList<String> getListaEmpleados() { return listaEmpleados; }
    public DefaultListModel<String> getModeloLista() { return modeloLista; }
    public JPanel getMainPanel() { return mainPanel; }
}
