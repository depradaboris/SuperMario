package Logica.Enemigos;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Entidad;
import Logica.Mario;
import Logica.Nivel;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;
import Visitantes.ColisionVisitor;

public abstract class Enemigo extends Entidad{
	private Nivel nivel_actual;
	
	public Enemigo(Coordenada coord, Sprite spr, int alto, int ancho) {
        super(coord,spr, alto, ancho);  // Inicializa el enemigo con una coordenada y tamaño
        VelocidadX = Math.random() < 0.5 ? -1 : 1; // puede empezar moviendose a la izquierda o a la derecha
        
    }
	
	// Método para mover el enemigo
    public void mover() {
    	setX(this.getX() + VelocidadX);
    	setY(this.getY() + VelocidadY);
        VelocidadY = VelocidadY + Gravedad;
       if(VelocidadY > 4)
        	VelocidadY = 4;
        this.observer.actualizar();
    }


	public void colisionar(Plataforma plataforma) {
		int chocadodesdearriba;
		int chocadodesdeizquierda;
		int chocadodesdederecha;
		chocadodesdearriba = plataforma.getHitbox().getBottom() -  this.getHitbox().getTop();//en valor absoluto
		chocadodesdeizquierda = plataforma.getHitbox().getRight() - this.getHitbox().getLeft();//en valor absoluto
		chocadodesdederecha = this.getHitbox().getRight() - plataforma.getHitbox().getLeft();//en valor absoluto
		
		if((chocadodesdearriba > 0.85 * chocadodesdeizquierda && chocadodesdearriba >  0.85 *chocadodesdederecha)) {
			this.setY(plataforma.getY()-15); //deberia ser 16 pero estan adentro del bloque por 1 pixel que no lo puedo solucionar
		}
		else if(  0.85 * chocadodesdeizquierda > chocadodesdearriba) {
			this.setX(plataforma.getX()-16);
			this.setVelocidadX( this.getVelocidadX() * -1);
			this.setVelocidadY(0);
		}
		else if( 0.85 * chocadodesdederecha > chocadodesdearriba) {
			this.setX(plataforma.getX()+16);
			this.setVelocidadX( this.getVelocidadX() * -1);
		}
	     this.observer.actualizar();
	}

	@Override
	public void accept(ColisionVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this,nivel_actual);
	}

	public abstract void chocadoDesdeArriba();

	public abstract void chocadoDesdeOtroLado();
	
	
	//NO BORRAR LOS DE ABAJO
	@Override
	public void colisionar(Mario mario) {
		// NO HACE NADA
	}

	@Override
	public void colisionar(PowerUp power_up) {
		// NO HACE NADA
	}
	
	@Override
	public void colisionar(Enemigo enemigo) {
		//  NO HACE NADA
	}
	
}
