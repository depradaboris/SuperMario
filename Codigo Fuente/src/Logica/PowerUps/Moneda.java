package Logica.PowerUps;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;

public class Moneda extends PowerUp{

	public Moneda(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}

	@Override
	public void colisionar(Mario mario) {
		mario.agregarPuntos(5);	
		SoundPlayer.playFXMoneda();
		morir();
		notifyObserver();
	}

}
