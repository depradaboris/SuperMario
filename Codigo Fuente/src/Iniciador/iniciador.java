
package Iniciador;
import Menu.GameMenu;

import javax.swing.SwingUtilities;

public class iniciador {
    public static void main(String[] args) {
    	// Crear y mostrar el menú
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameMenu menu = new GameMenu();
                menu.setVisible(true);
            }
        });
    }
}
