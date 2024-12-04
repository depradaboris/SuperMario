package Visitantes;

import Logica.BolaDeFuego;
import Logica.Mario;
import Logica.Nivel;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public class ColisionBolaFuego extends ColisionVisitor{
	
	protected BolaDeFuego bola_fuego;
	
	public ColisionBolaFuego(BolaDeFuego bola_fuego) {
		this.bola_fuego = bola_fuego;
	}
	@Override
	public void visit(Mario mario) {
		//nada
	}

	@Override
	public void visit(PowerUp power_up) {
		// nada
	}

	@Override
	public void visit(Plataforma plataforma) {
		
		if(bola_fuego.checkCollision(plataforma)) {
			bola_fuego.colisionar(plataforma);
		}
	}

	@Override
	public void visit(BolaDeFuego bola) {
		//nada
	}
	
	public void visit(Enemigo enemigo, Nivel nivel_actual) {
		if(bola_fuego.checkCollision(enemigo)) {
	        bola_fuego.colisionar(enemigo);
	    }
	}

}
