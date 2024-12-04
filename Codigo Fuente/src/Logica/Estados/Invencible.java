package Logica.Estados;

import java.util.Timer;
import java.util.TimerTask;

import Grafica.Juego;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;

public class Invencible extends EstadoBase implements MarioStates {	
	
	protected  Mario mario = Mario.getInstancia();
	
	protected MarioStates estado_anterior;
    private Timer timer;

    public Invencible(MarioStates estado_anterior) {
        super.caminando_der = Juego.factory_spr.getInvencibleMarioCaminandoDer();
        super.caminando_izq = Juego.factory_spr.getInvencibleMarioCaminandoIzq();
        super.saltando_der = Juego.factory_spr.getInvencibleMarioSaltandoDer();
        super.saltando_izq = Juego.factory_spr.getInvencibleMarioSaltandoIzq();
        super.parado_der = Juego.factory_spr.getInvencibleMarioParadoDer();
        super.parado_izq = Juego.factory_spr.getInvencibleMarioParadoIzq();
        
        this.estado_anterior = estado_anterior;
        
        System.out.println("Estado anterior: " + estado_anterior.getClass().getName());

        // Iniciar temporizador para regresar al estado anterior después de 10 segundos
        SoundPlayer.pausa();
        SoundPlayer.playFXInvencible();
        
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Mario mario = Mario.getInstancia();
                mario.setState(estado_anterior); // Restaurar el estado anterior
                mario.getHitbox().setAlto(32); // Ajustar la altura de la hitbox si es necesario
                timer.cancel(); // Detener el temporizador
                SoundPlayer.pausa();
            }
        }, 10000); // 10 segundos (10000 milisegundos)
    }
    
    public void update( ) {
		super.update();	
	}
	
	@Override
	public void recibirDaño(int puntaje) {
		// nada porque soy inmortal :P
	}

	public MarioStates getEstadoAnterior() {
		return estado_anterior;
	}

	@Override
	public void lanzarBolaDeFuego() {
		//nada :p
	}

	@Override
	public void colisionarDesdeArriba(Enemigo enemigo) {
		mario.minisalto();
        Juego.esteNivel().eliminarEnemigo(enemigo);
        enemigo.morir();
        enemigo.notifyObserver();
        SoundPlayer.playFXDaño();
	}

	@Override
	public void colisionarDesdeOtroLado(Enemigo enemigo) {
        Juego.esteNivel().eliminarEnemigo(enemigo);
        enemigo.morir();
        enemigo.notifyObserver();
        SoundPlayer.playFXDaño();
	}

	@Override
	public void chocarDesdeAbajo(Plataforma plataforma) {
		if(plataforma.esUsable())
			plataforma.usar();
	}

	//Para manejo de Puntaje
	
	@Override
	public void colisionarConFlor( ) {
	//	mario.getHitbox().setAlto(32);
		mario.agregarPuntos(5);
	}
	
	@Override
	public void colisionarConSuperChamp( ) {
		mario.agregarPuntos(10);
	//	mario.getHitbox().setAlto(32);
	}
	
	@Override
	public void colisionarConEstrella( ) {
		mario.agregarPuntos(20);
		mario.setState(new Invencible(mario.getState()));
	//	mario.getHitbox().setAlto(32);
	}


}
