package Logica.Estados;

import Datos.Coordenada;
import Grafica.Juego;
import Logica.BolaDeFuego;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Enemigos.Enemigo;
import Logica.Hilos.HiloInvulnerabilidad;
import Logica.Plataformas.Plataforma;

public class Fuego extends EstadoBase implements MarioStates{
	
	protected Mario mario = Mario.getInstancia();
	
	public Fuego() {
		SoundPlayer.playFXPowerUp();
		super.caminando_der = Juego.factory_spr.getFuegoMarioCaminandoDer();
		super.caminando_izq = Juego.factory_spr.getFuegoMarioCaminandoIzq();
		super.saltando_der = Juego.factory_spr.getFuegoMarioSaltandoDer();
		super.saltando_izq = Juego.factory_spr.getFuegoMarioSaltandoIzq();
		super.parado_der = Juego.factory_spr.getFuegoMarioParadoDer();
		super.parado_izq = Juego.factory_spr.getFuegoMarioParadoIzq();
	}

	@Override
	public void update( ) {
		super.update();
	}

	@Override
	public void lanzarBolaDeFuego() {
		int x = mario.getX() + 8;
		int y = mario.getY() - 24;
		Coordenada coord = new Coordenada(x,y);
		int orientation = 1;
		
		if(mario.isLookingLeft())
			orientation = -1;
		if(mario.isLookingRight())
			orientation = 1;
		
		SoundPlayer.playFXBolaDeFuego();
		BolaDeFuego bola_de_fuego = Juego.factory_elem.getBolaDeFuego(coord,orientation);	
		
		// Crear la bola de fuego
	    // Crear y asignar el observador específico de BolaDeFuego
		Juego.nivel_actual.setBolaDeFuego(bola_de_fuego);
		Juego.registrarObserverEntidad(bola_de_fuego);
	    System.out.println("Se creo bola fuego");
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
		mario.setState(new Normal());
		mario.getHitbox().setAlto(16);	
	    HiloInvulnerabilidad hilo = new HiloInvulnerabilidad(2);
	    hilo.start();
	}
	
	public  void chocarDesdeAbajo(Plataforma plataforma) {
		if( plataforma.esRompible())
			plataforma.romper();
		if(plataforma.esUsable())
			plataforma.usar();	
	}

	//Para manejo de Puntaje
	@Override
	public void colisionarConFlor() {
	//	mario.setState(new Fuego());
	//	mario.getHitbox().setAlto(32);
		mario.agregarPuntos(50);
	}
	
	@Override
	public void colisionarConSuperChamp() {
		mario.agregarPuntos(50);
	//	mario.setState(new Super());
	//	mario.getHitbox().setAlto(32);
	}
	
	@Override
	public void colisionarConEstrella() {
		mario.agregarPuntos(30);
		mario.setState(new Invencible(mario.getState()));
		mario.getHitbox().setAlto(32);
	}

}
