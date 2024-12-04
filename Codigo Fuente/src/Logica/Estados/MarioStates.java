package Logica.Estados;

import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;

public interface MarioStates {
	
    public abstract void update();
	public abstract void colisionarDesdeArriba(Enemigo enemigo);
	public abstract void colisionarDesdeOtroLado(Enemigo enemigo);
	public abstract void chocarDesdeAbajo(Plataforma plataforma);
	//public abstract void recibirDaño();
	public abstract void recibirDaño(int puntaje);
	public abstract void lanzarBolaDeFuego();
	
	public abstract void colisionarConEstrella();
	public abstract void colisionarConFlor();
	public abstract void colisionarConSuperChamp();
}
