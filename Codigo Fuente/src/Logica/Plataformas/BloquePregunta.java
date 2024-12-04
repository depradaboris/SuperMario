package Logica.Plataformas;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.PowerUps.PowerUp;
public class BloquePregunta extends Plataforma {

	public BloquePregunta(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
    	usable = true;
	}

	@Override
    public void usar() {
	   	System.out.println("doy powerup");
	    this.setUsable(false);
	   	this.GenerarPowerUp();
	   	notifyObserver();
    }
	
	public void GenerarPowerUp() {
		Coordenada coord = new Coordenada (this.getX() , this.getY()-32);
		PowerUp powerup = null;
		int numero = (int) (Math.random() * 100);
		if (numero <= 15){
			powerup = Juego.factory_elem.getMoneda(coord);
		}
		else if (numero <= 30) {
			powerup = Juego.factory_elem.getEstrella(coord);
		}
		else if (numero <= 60) {
			powerup = Juego.factory_elem.getFlorFuego(coord);
		}
		else if (numero <= 90) {
			powerup = Juego.factory_elem.getSuperChamp(coord);
		}
		else {
			powerup = Juego.factory_elem.getChampVerde(coord);
		}

		Juego.nivel_actual.setPowerUp(powerup);
		Juego.registrarObserverEntidad(powerup);
	    System.out.println("Se creo el powerup");
	    powerup.notifyObserver();
	}
	
}
