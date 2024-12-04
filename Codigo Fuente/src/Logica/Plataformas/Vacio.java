package Logica.Plataformas;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Logica.Mario;
import Logica.Estados.Normal;
import Visitantes.ColisionVisitor;

public class Vacio extends Plataforma{

	public Vacio(Coordenada coord, Sprite spr) {
		super(coord, spr, 16, 16);
	}

	@Override
	public void accept(ColisionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void colisionar(Mario mario) {
		mario.agregarPuntos(-15);
		mario.setState(new Normal());
		Juego.reiniciarNivel();
	}
}
