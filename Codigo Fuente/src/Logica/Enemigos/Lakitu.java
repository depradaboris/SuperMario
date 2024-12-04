package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.Mario;

public class Lakitu extends Enemigo{
	
	private int DistanciaMin;
	private int DistanciaMax;
	
	public Lakitu(Coordenada coord,Sprite spr) {
		super(coord,spr,16,16);
		VelocidadX = 1;
		DistanciaMin = coord.getX();
		DistanciaMax = coord.getX() + 160;
	}
	
	
	@Override
	public void mover() {
		this.setX(VelocidadX + this.getX());
		
		int X =this.getX();
		if ( X < DistanciaMin || X> DistanciaMax) {
			this.setVelocidadX(VelocidadX * -1);
		}
		int numero = (int) (Math.random() * 324);
		if (numero == 69) {
			System.out.println("e tirado un enemio");
			this.tirarSpiny();
		}
    	this.observer.actualizar();
	}

	@Override
	public void chocadoDesdeArriba() {
		this.morir();
	}

	@Override
	public void chocadoDesdeOtroLado() {
		Mario.getInstancia().recibirDaño(0);
	}
	
	@Override
	public void morir() {
		super.morir();
		Mario.getInstancia().agregarPuntos(60);
	}
	private void tirarSpiny() {
		Coordenada coord = new Coordenada (this.getX() , this.getY());
		
		Enemigo spiny = Juego.factory_elem.getSpiny(coord);

		Juego.nivel_actual.setEnemigo(spiny);
		Juego.registrarObserverEntidad(spiny);
	    spiny.notifyObserver();
	}
}
