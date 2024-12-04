package Inputs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Grafica.ControladorVista;

public class EntradaTeclado {
	
	public static void addOyenteTeclado(ControladorVista ctrl_vista) {
		Jugador jugador = Jugador.getInstancia();
        ctrl_vista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A: // Tecla A para moverse a la izquierda
                        jugador.moverIzquierda(true);
                        break;
                    case KeyEvent.VK_D: // Tecla D para moverse a la derecha
                        jugador.moverDerecha(true);
                        break;
                    case KeyEvent.VK_W:
                    	jugador.saltar();
                    	break;
                    case KeyEvent.VK_SPACE:
                    	jugador.lanzarBolaDeFuego();
                    	break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A: 
                    	jugador.moverIzquierda(false);
                    	break;                // Detener el movimiento cuando se suelta la tecla A
                    case KeyEvent.VK_D: // Detener el movimiento cuando se suelta la tecla D
                    	jugador.moverDerecha(false);
                        break;
                }
            }
        });
    }
}
