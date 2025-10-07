package Main;

import View.MenuMain;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {

            MenuMain menuView = new MenuMain();
            menuView.setVisible(true);
        });

    }
}
