package Controller;

import Model.AppModel;
import Model.AuthService;
import Model.User;
import View.LoginDialog;
import View.MenuView;

import javax.swing.*;
import java.util.Optional;

public class LoginController {
    private final AppModel appModel;
    private final AuthService authService;
    private final LoginDialog loginView;
    private final MenuView menuMain;

    public LoginController(AppModel appModel, AuthService authService, LoginDialog loginView, MenuView menuMain) {
        this.appModel = appModel;
        this.authService = authService;
        this.loginView = loginView;
        this.menuMain = menuMain;

        // Acción de login
        this.loginView.getBtnLogin().addActionListener(e -> login());
    }

    private void login() {
        String usuario = loginView.getUsuario();
        String password = loginView.getPassword();

        Optional<User> usuarioOpt = authService.login(usuario, password);
        if (usuarioOpt.isPresent()) {
            User user = usuarioOpt.get();
            appModel.setUsuarioActual(user.getUsername());
            appModel.setRolActual(user.getRole());
            loginView.setLoginExitoso(true);
            JOptionPane.showMessageDialog(loginView, "Bienvenido, " + user.getUsername());
            loginView.dispose();
        } else {
            JOptionPane.showMessageDialog(loginView, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean showLogin() {
        loginView.mostrar();
        return loginView.isLoginExitoso();
    }
}



