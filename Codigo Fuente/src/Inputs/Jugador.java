package Inputs;
import Datos.Puntaje;
import Logica.Mario;

public class Jugador {
	
	private int vidas;
	private Puntaje puntaje;
	private static Jugador instanciaJugador ;
	
	public Jugador() {
		vidas = 3;
		this.puntaje = new Puntaje();
	}
	
    public static Jugador getInstancia() {
        if (instanciaJugador == null) {

            instanciaJugador = new Jugador();
        }
        return instanciaJugador;
    }
	
	
    public void decrementarVidas() {
    	vidas -= 1;
    	System.out.println(vidas);
    }
    
    public void incrementarVidas() {
    	vidas += 1;
    }
    
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public int getVidas() {
		return vidas;
	}
		
	public Puntaje getPuntaje() {
		return puntaje;
	}
	
	public void moverIzquierda(boolean b) {
		Mario.getInstancia().setIzquierda(b);
	}
	public void moverDerecha(boolean b) {
		Mario.getInstancia().setDerecha(b);
	}
	
	
    public void saltar() {
    	if (!Mario.getInstancia().isSaltando()) {
    		Mario.getInstancia().saltar();
    	}
    }
    
    public void actualizarPosicion() {
        // Imprimir el hilo actual
        System.out.println("Hilo actual" + Thread.currentThread().getName());
        // Lógica de actualización de la posición de Mario
        // Por ejemplo: x += velocidadX;
    }

	public void lanzarBolaDeFuego() {
		Mario.getInstancia().lanzarBolaDeFuego();
	}
}
