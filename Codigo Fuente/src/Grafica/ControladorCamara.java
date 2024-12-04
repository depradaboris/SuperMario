package Grafica;

import java.awt.Rectangle;
import javax.swing.JPanel;
import Datos.Constantes;
import Logica.Mario;

public class ControladorCamara {
	//Atributos de instancia
	private JPanel vista_actual;
	private Rectangle cursor;
	
	public ControladorCamara(JPanel vista_actual) {
		this.vista_actual = vista_actual;
		int coord_x = Mario.getInstancia().getX();
		int coord_y = Mario.getInstancia().getY();
		cursor = new Rectangle(coord_x + 128,coord_y + 64,Constantes.ALTO_VENTANA,Constantes.ANCHO_VENTANA);
	}
	
	public void moverCamara(){
		cursor.x = Mario.getInstancia().getX();
        vista_actual.scrollRectToVisible(cursor);
	}
}
