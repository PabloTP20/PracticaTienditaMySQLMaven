package Main;

import javax.swing.*;
import Controller.MenuController;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MenuController menuController = new MenuController();
        });

    }
}
