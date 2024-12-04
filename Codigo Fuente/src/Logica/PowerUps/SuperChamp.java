package Logica.PowerUps;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Plataformas.Plataforma;

public class SuperChamp extends PowerUp {

	public SuperChamp(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}


	@Override
	
	public void colisionar(Mario mario) {
		mario.colisionarConSuperChamp();
		spr = Juego.factory_spr.getTransparencia();
		
		SoundPlayer.playFXPowerUp();
		
		notifyObserver();
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void colisionar(Plataforma plataforma) {
		// TODO Auto-generated method stub
		
	}

}
