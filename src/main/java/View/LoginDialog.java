package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {

    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtPassword = new JPasswordField(15);
    private final JButton btnLogin = new JButton("Iniciar sesión");
    private final JButton btnSalir = new JButton("Salir"); //

    private boolean loginExitoso = false;

    public LoginDialog(JFrame parent) {
        super(parent, "Inicio de Sesión", true);
        setLayout(new BorderLayout());
        setSize(350, 200);
        setLocationRelativeTo(parent);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnSalir); // 🔹 Agregamos el nuevo botón aquí

        add(panel, BorderLayout.CENTER);

        // Acción del botón salir
        btnSalir.addActionListener(e -> {
            loginExitoso = false;
            dispose();
            System.exit(0); // 🔹 Cierra toda la aplicación
        });

        getRootPane().setDefaultButton(btnLogin); // Enter activa el login
    }

    public String getUsuario() {
        return txtUsuario.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public void setLoginExitoso(boolean exitoso) {
        this.loginExitoso = exitoso;
    }

    public boolean isLoginExitoso() {
        return loginExitoso;
    }

    public void mostrar() {
        setVisible(true);
    }
}

