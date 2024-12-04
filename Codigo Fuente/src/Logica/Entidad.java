package Logica;

import Datos.Coordenada;
import Grafica.Juego;
import Grafica.Sprite;
import Grafica.Observers.Observer;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;
import Visitantes.ColisionVisitor;

public abstract class Entidad extends EntidadBase{
	protected Coordenada coord;
	protected Observer observer;
	protected Sprite spr;
    protected Hitbox hitbox;
    protected boolean es_colisionable;
    protected boolean vivo;
    protected int VelocidadX = 0;
    protected int VelocidadY = 0;
    protected int Gravedad = 1;
    protected int facing_orientation;

    // Constructor
    public Entidad(Coordenada coord,Sprite spr, int ancho, int alto) {
    	this.spr = spr;
        this.coord = coord;
        this.es_colisionable = true;
        hitbox = new Hitbox (coord,alto,ancho);
        setVivo(true);
    }
	
    public void setCoordenada(Coordenada coord) {
    	this.coord = coord;
    	hitbox.setCoordenada(coord);
    }
    
    public void setVivo(boolean estaVivo) {
    	this.vivo = estaVivo;
    }
    public boolean isLookingLeft() {
    	return facing_orientation == -1;
    }
    
    public boolean isLookingRight() {
    	return facing_orientation == 1;
    }
    
    
    public void setLeftOrientation() {
    	facing_orientation = -1;
    }
    
    public void setRightOrientation() {
    	facing_orientation = 1;
    }
    	
	public Sprite getSprite() {
		return spr;
	}
	
	public void notifyObserver() {
	    if (observer != null) {
	        observer.actualizar();
	    }
	}
	
	public void setSprite(Sprite spr) {
		this.spr = spr;
	}
	
	public Hitbox getHitbox() {
		return hitbox;
	}
	public int getY() {
		return coord.getY();
	}
	public void setY(int y) {
		coord.setY(y);
	}
	public int getX() {
		return coord.getX();
	}
	public void setX(int y) {
		coord.setX(y);
	}
	
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	public abstract void colisionar(Enemigo enemigo);
	public abstract void colisionar(PowerUp power_up);
	public abstract void colisionar(Plataforma plataforma);
	public abstract void colisionar(Mario mario);
	
	public abstract void accept(ColisionVisitor visitor);
	
	public boolean checkCollision(Entidad ent_entrante){
		Hitbox hitbox_entrante = ent_entrante.getHitbox();
		
		return (this.es_colisionable && ent_entrante.es_colisionable) && (hitbox.getRight() > hitbox_entrante.getLeft() &&
	               hitbox.getLeft() < hitbox_entrante.getRight() &&
	               hitbox.getBottom() > hitbox_entrante.getTop() &&
	               hitbox.getTop() < hitbox_entrante.getBottom());
	}
	
	//A partir del alto, ancho y coordenadas de entidad entrante y local, se hacen los calculos.

	
	public void setColisionable(boolean valor) {
		this.es_colisionable = valor;
	}
	
	public boolean esColisionable() {
		return this.es_colisionable;
	}
	
	public abstract void mover();
	
	public int getVelocidadY() {
		return VelocidadY;
	}	
	
	public int getVelocidadX() {
		return VelocidadX;
	} 
	
	public void setVelocidadY(int i) {
		VelocidadY = i;	
	}
	
	public void setVelocidadX(int i) {
		VelocidadX = i;
	}
	
	public void morir() {
		setVivo(false);
		spr = Juego.factory_spr.getTransparencia();
		es_colisionable = false;
	}

	public void agregarPuntos(int puntos) {
	}

	public boolean isVivo() {
		return vivo;
	}
	
}
