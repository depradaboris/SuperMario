package Logica.PowerUps;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Plataformas.Plataforma;

public class Estrella extends PowerUp {

	public Estrella(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}

	@Override
	public void colisionar(Mario mario) {
		mario.colisionarConEstrella();
		morir();
		
		SoundPlayer.playFXPowerUp();
		
		notifyObserver();
	}


	@Override
	public void mover() {
		// se queria hacer que la estrella se moviese pero no dio tiempo
	}

	@Override
	public void colisionar(Plataforma plataforma) {
		// se queria hacer que la estrella se moviese pero no dio tiempo
	}


}
