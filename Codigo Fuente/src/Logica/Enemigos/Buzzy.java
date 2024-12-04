package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;

public class Buzzy extends Enemigo{
	public Buzzy(Coordenada coord,Sprite spr) {
		super(coord,spr,16,16);
	}

	@Override
	public void chocadoDesdeArriba() {
		this.morir();
	}

	@Override
	public void chocadoDesdeOtroLado() {
		Mario.getInstancia().recibirDaño(-30);
	}
	
	@Override
	public void morir() {
		super.morir();
		Mario.getInstancia().agregarPuntos(30);
	}
}
