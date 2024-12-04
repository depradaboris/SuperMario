package Logica.PowerUps;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;

public class FlorFuego extends PowerUp {

	public FlorFuego(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}

	@Override
	public void colisionar(Mario mario) {
		
		mario.colisionarConFlor();
		morir();
		 
		SoundPlayer.playFXPowerUp();
		
		notifyObserver();
	}

}
