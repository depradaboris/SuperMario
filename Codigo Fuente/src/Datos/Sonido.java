package Datos;

public class Sonido {
	
	protected String ruta_sonido;
	
	public Sonido (String ruta_sonido) {
		this.ruta_sonido = ruta_sonido;
	}
	
	public String getRutaSonido() {
		return ruta_sonido;
	}
	
	public void setRutaSonido(String ruta_imagen) {
		this.ruta_sonido = ruta_imagen;
	}	
}
