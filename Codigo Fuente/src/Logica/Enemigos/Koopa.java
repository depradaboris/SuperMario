package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.Mario;

public class Koopa extends Enemigo{
	private int  vida;
	
	public Koopa(Coordenada coord,Sprite spr) {
		super(coord,spr,16,16);
		vida = 2;
	}
	
	@Override
	public void mover() {
		if (vida == 2) { 
			super.mover();
		}
		if (vida == 1) {
			this.observer.actualizar();
		}
	}
	
	@Override
	public void chocadoDesdeArriba() {
		if (vida == 2) {
			vida = 1;
			this.setSprite(Juego.factory_spr.getKoopa2());
			System.out.println("llego aca?");
			}
		else
			this.morir();
	}

	@Override
	public void chocadoDesdeOtroLado() {
		Mario.getInstancia().recibirDaño(-45);
	}
	
	@Override
	public void morir() {
		super.morir();
		Mario.getInstancia().agregarPuntos(90);
	}
}
