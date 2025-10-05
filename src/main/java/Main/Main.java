package Main;

import Controller.EmpleadoController;
import Controller.InventarioController;
import View.MenuMain;
import Model.Empleado;
import Model.EmpleadoModel;
import Model.InventarioModel;
import View.EmpleadoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {

            MenuMain menuView = new MenuMain();
            menuView.setVisible(true);
        });

    }
}
