package Visitantes;

import Logica.BolaDeFuego;
import Logica.Mario;
import Logica.Nivel;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public class MarioColisionVisitor extends ColisionVisitor{
	
	protected Mario mario;
	
	public MarioColisionVisitor(Mario mario) {
		this.mario = mario;
	}
	@Override
	public void visit(Mario mario) {
		// Mario no choca con el mismo.
	}
	@Override
	public void visit(Enemigo enemigo, Nivel nivel_actual) {
	    if(mario.checkCollision(enemigo)) {
	        mario.colisionar(enemigo);
	    }
	}

	@Override
	public void visit(PowerUp power_up) {
		if(mario.checkCollision(power_up)) {
			power_up.setColisionable(false);
		//	System.out.println("Mario ha chocado con un powerup");
			mario.colisionar(power_up);
		}
	}

	@Override
	public void visit(Plataforma plataforma) {
		if(mario.checkCollision(plataforma)) {
			mario.colisionar(plataforma);
		}
	}
	@Override
	public void visit(BolaDeFuego bola) {
	}	
	
}
