package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;

public class Spiny extends Enemigo{
	
	public Spiny(Coordenada coord,Sprite spr) {
		super(coord,spr,16,16);
		VelocidadY = -8;
	}

	@Override
	public void chocadoDesdeArriba() {
		Mario.getInstancia().recibirDaño(-30);
	}

	@Override
	public void chocadoDesdeOtroLado() {
		Mario.getInstancia().recibirDaño(-30);
	}
	
	@Override
	public void morir() {
		super.morir();
		Mario.getInstancia().agregarPuntos(60);
	}


}
