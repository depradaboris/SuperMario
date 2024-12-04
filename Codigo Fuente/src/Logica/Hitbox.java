package Logica;

import Datos.Coordenada;

public class Hitbox{
	protected Coordenada coord;
	protected int alto;
	protected int ancho;
	
	public Hitbox(Coordenada coord, int alto,int ancho) {
		coord.setY(coord.getY()+15);
		this.coord = coord;
		this.alto = alto;
		this.ancho = alto;
	}
	
	public void setCoordenada(Coordenada coord) {
		this.coord = coord;
	}
	
	public int getTop() {
		return coord.getY() - alto + 1;
	}
	
	public int getBottom() {
		return coord.getY();
	}
	
	public int getLeft() {
		return coord.getX();
	}
	
	public int getRight() {
		return coord.getX() + ancho - 1;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public void setAlto(int alto) {
		this.alto = alto;
	}
	
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
}
