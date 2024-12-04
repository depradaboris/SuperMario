package Logica.Estados;

import javax.swing.Timer;
import Grafica.Juego;
import Logica.Mario;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;

public class Normal extends EstadoBase implements MarioStates{
	
	// si pongo de atributo a mario se crashea todo
	
	
	Timer timer;
	
	public Normal() {
		super.caminando_der = Juego.factory_spr.getMarioCaminandoDer();
		super.caminando_izq = Juego.factory_spr.getMarioCaminandoIzq();
		super.saltando_der = Juego.factory_spr.getMarioSaltandoDer();
		super.saltando_izq = Juego.factory_spr.getMarioSaltandoIzq();
		super.parado_der = Juego.factory_spr.getMarioParadoDer();
		super.parado_izq = Juego.factory_spr.getMarioParadoIzq();
		super.muerto = Juego.factory_spr.getMarioMuerto();
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void lanzarBolaDeFuego() {
		//NADa
	}
	@Override
	public  void chocarDesdeAbajo(Plataforma plataforma) {
		if(plataforma.esUsable())
			plataforma.usar();
	}
	
	@Override
	public void colisionarDesdeArriba(Enemigo enemigo) {
		if(Mario.getInstancia().getVelocidadY() > 0) {  // velociad Y >0 significa que esta cayendo
		Mario.getInstancia().minisalto();
		enemigo.chocadoDesdeArriba();
		}
	}

	@Override
	public void colisionarDesdeOtroLado(Enemigo enemigo) {
		if (! Mario.getInstancia().esInvulnerable() )
		enemigo.chocadoDesdeOtroLado();	
	}
	
	@Override
	public void recibirDaño(int puntaje) {
		Mario.getInstancia().agregarPuntos(puntaje);
		Juego.reiniciarNivel();
	}
	
	//Para manejo de Puntaje
	
	@Override
	public void colisionarConFlor( ) {
		Mario.getInstancia().agregarPuntos(5);
		Mario.getInstancia().setState(new Fuego());
		Mario.getInstancia().getHitbox().setAlto(32);
	}
	
	@Override
	public void colisionarConSuperChamp( ) {
		Mario.getInstancia().agregarPuntos(10);
		Mario.getInstancia().setState(new Super());
		Mario.getInstancia().getHitbox().setAlto(32);
	}
	
	@Override
	public void colisionarConEstrella( ) {
		Mario mario = Mario.getInstancia();
		mario.agregarPuntos(20);
		mario.setState(new Invencible(mario.getState()));
		mario.getHitbox().setAlto(32);
	}
	
}
