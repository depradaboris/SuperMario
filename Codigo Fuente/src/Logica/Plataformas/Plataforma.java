package Logica.Plataformas;

import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Entidad;
import Logica.Mario;
import Logica.Enemigos.Enemigo;
import Logica.PowerUps.PowerUp;
import Visitantes.ColisionVisitor;

public abstract class Plataforma extends Entidad{
	
	protected boolean rompible = false;
	protected boolean usable = false;
	
    public Plataforma(Coordenada coord,Sprite spr, int ancho, int alto) {
    	super(coord,spr,ancho,alto);
    }

    public boolean esRompible() {
    	return rompible;
    }
    
    public void romper() {
    	
    }
    
    public void setRompible(boolean b){
    	rompible = b;
    }
    
    public boolean esUsable() {
    	return usable;
    }
    
    public void usar() {
    	
    }
    
    public void setUsable(boolean b) {
    	usable = b;
    }
    
	@Override
	public void accept(ColisionVisitor visitor) {
		visitor.visit(this);
	}

    @Override
	public void mover() {
		//nada
	}

	@Override
	public void colisionar(Enemigo enemigo) {
		//nada
	}

	@Override
	public void colisionar(PowerUp power_up) {
		//nada
	}

	@Override
	public void colisionar(Plataforma plataforma) {
		//nada
	}

	@Override
	public void colisionar(Mario mario) {
		//nada
	}
}
