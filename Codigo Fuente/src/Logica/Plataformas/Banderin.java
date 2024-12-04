package Logica.Plataformas;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;

public class Banderin extends Plataforma {

	public Banderin(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}
	
	@Override
	public void colisionar(Mario mario) {
		//Este bloque deberia llamar el cambio de nivel del juego
		SoundPlayer.playFXSuperado();
		Juego.cambiarNivel();
	}
}
