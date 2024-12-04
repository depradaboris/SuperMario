package Visitantes;

import Logica.BolaDeFuego;
import Logica.Mario;
import Logica.Nivel;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public abstract class ColisionVisitor{
	public abstract void visit(Mario mario);
	//public abstract void visit(Enemigo enemigo);
	public abstract void visit(PowerUp power_up);
	public abstract void visit(Plataforma plataforma);
	public abstract void visit(BolaDeFuego bola);
	public void visit(Enemigo enemigo, Nivel nivel_actual) {
		// TODO Auto-generated method stub
		
	}
}
