package Logica.PowerUps;

import Datos.Coordenada;
import Grafica.Sprite;
import Inputs.Jugador;
import Logica.Mario;
import Logica.SoundPlayer;

public class ChampVerde extends PowerUp{

	public ChampVerde(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}

	@Override
	public void colisionar(Mario mario) {
		Mario.getInstancia().agregarPuntos(100);
		morir();
		Jugador.getInstancia().incrementarVidas();
		SoundPlayer.playFXVida();
		
		notifyObserver();
	}
}
