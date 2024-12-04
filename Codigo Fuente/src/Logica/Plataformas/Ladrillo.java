package Logica.Plataformas;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.SoundPlayer;

public class Ladrillo extends Plataforma{
	
	public Ladrillo(Coordenada coord, Sprite spr) {
		super(coord,spr,16,16);
    	rompible = true;
	}
	
	@Override
    public void romper() {
		SoundPlayer.playFXRomperBloque();
    	this.setRompible(false);
    	this.morir();
    	notifyObserver();
    }

}
