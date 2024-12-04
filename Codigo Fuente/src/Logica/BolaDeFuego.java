package Logica;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;
import Visitantes.ColisionVisitor;

public class BolaDeFuego extends Entidad{

	private int Direccion;
	private static final int VelocidadX = 2;
	
	public BolaDeFuego(Coordenada coord, Sprite spr,int facing_orientation) {
		super(coord, spr, 8, 8);
		this.Direccion = facing_orientation;
	}
	
	@Override
	public void accept(ColisionVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void mover() {
		this.setX(this.getX() + VelocidadX * Direccion);
		notifyObserver();
	}

	@Override
	public void colisionar(Enemigo enemigo) {
		enemigo.morir();
		this.morir(); 
		notifyObserver();
	}

	@Override
	public void colisionar(PowerUp power_up) {
		//Nada
	}

	@Override
	public void colisionar(Plataforma plataforma) {
		this.morir();
		notifyObserver();
	}

	@Override
	public void colisionar(Mario mario) {
		//Nada
	}

}
