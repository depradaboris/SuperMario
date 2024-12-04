package Logica.Estados;

import Grafica.Juego;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Enemigos.Enemigo;
import Logica.Hilos.HiloInvulnerabilidad;
import Logica.Plataformas.Plataforma;

public class Super extends EstadoBase implements MarioStates{
		
	protected  Mario mario = Mario.getInstancia();
	
	public Super() {
		SoundPlayer.playFXPowerUp();
		super.caminando_der = Juego.factory_spr.getSuperMarioCaminandoDer();
		super.caminando_izq = Juego.factory_spr.getSuperMarioCaminandoIzq();
		super.saltando_der = Juego.factory_spr.getSuperMarioSaltandoDer();
		super.saltando_izq = Juego.factory_spr.getSuperMarioSaltandoIzq();
		super.parado_der = Juego.factory_spr.getSuperMarioParadoDer();
		super.parado_izq = Juego.factory_spr.getSuperMarioParadoIzq();
	}
	
	public void update() {
		super.update();
	}

	@Override
	public void lanzarBolaDeFuego() {
		// nada ;p
	}

	@Override
	public  void chocarDesdeAbajo(Plataforma plataforma) {
		if( plataforma.esRompible())
			plataforma.romper();
		if(plataforma.esUsable())
			plataforma.usar();
	}
	
	@Override
	public void colisionarDesdeArriba(Enemigo enemigo) {
		if(mario.getVelocidadY() > 0) {
		Mario.getInstancia().minisalto();
		enemigo.chocadoDesdeArriba();
		}
	}

	@Override
	public void colisionarDesdeOtroLado(Enemigo enemigo) {
		if (! mario.esInvulnerable() )
		enemigo.chocadoDesdeOtroLado();
	}

	@Override
	public void recibirDaño(int puntaje) {
		System.out.println("chocaste desde super");
	    HiloInvulnerabilidad hilo = new HiloInvulnerabilidad(2);
		mario.getHitbox().setAlto(16);
	    mario.setState(new Normal());
	 //   HiloInvulnerabilidad hilo = new HiloInvulnerabilidad(2);
	    hilo.start();
	}
	
	//Para manejo de Puntaje
	
	@Override
	public void colisionarConFlor() {
		mario.setState(new Fuego());
		mario.agregarPuntos(30);
	}
	
	@Override
	public void colisionarConSuperChamp() {
		mario.agregarPuntos(50);
	}
	
	@Override
	public void colisionarConEstrella() {
		mario.agregarPuntos(30);
		mario.setState(new Invencible(mario.getState()));
		mario.getHitbox().setAlto(32);
	}



}
