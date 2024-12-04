package Grafica.Observers;

import Logica.Entidad;

public class ObserverBolaDeFuego extends ObserverGrafica {
	
	public ObserverBolaDeFuego(Entidad entidad_observada) {
		super(entidad_observada);
		actualizar();
	}

}
