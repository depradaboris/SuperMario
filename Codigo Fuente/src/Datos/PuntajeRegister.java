package Datos;

public class PuntajeRegister {
	
	private String nombre;
	private int puntaje;
	
	public PuntajeRegister(String nombre,int puntaje) {
		this.setPuntaje(puntaje);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
}
