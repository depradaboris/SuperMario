package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Mario;
import Logica.Plataformas.Plataforma;

public class Piranha extends Enemigo{
	
	private int AlturaMax;
	private int AlturaMin;
	
	public Piranha(Coordenada coord,Sprite spr) {
		super(coord,spr,16,16);
		AlturaMax = coord.getY() + 8;
		AlturaMin = coord.getY()+ 48;
		VelocidadY = 1;
		this.getHitbox().setAncho(8);
	}
	
	@Override
	public void mover() {
    	this.setY(this.getY() + VelocidadY);
    	//this.getHitbox().cambiarCoord(this.getX() + 4, this.getY());
    	int Y = this.getY();
    	if (Y <= AlturaMax) {
    		int numero2 = (int) (Math.random() * 40);
    		if (numero2 == 2)
    			VelocidadY = 1;
			else VelocidadY = 0; 
    	}
    	if (Y > AlturaMin) {
    		int numero = (int) (Math.random() * 127);
			if (numero == 42)
				VelocidadY = -1;
			else VelocidadY= 0;
    		}
		this.getHitbox().setCoordenada(new Coordenada(this.getX() + 4, this.getY() ) );
    	this.observer.actualizar();
	}

	@Override
	public void colisionar(Plataforma plataforma) {} // ES NECESARIO QUE ESTE PARA QUE FUNCIONE
	
	@Override
	public void chocadoDesdeArriba() {
		Mario.getInstancia().recibirDaño(-30);
	}

	@Override
	public void chocadoDesdeOtroLado() {
		Mario.getInstancia().recibirDaño(-30);
	}
	
	@Override
	public void morir() {
		super.morir();
		Mario.getInstancia().agregarPuntos(30);
	}

}
