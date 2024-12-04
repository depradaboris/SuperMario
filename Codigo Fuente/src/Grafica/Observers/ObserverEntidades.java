package Grafica.Observers;

import Logica.Entidad;

public class ObserverEntidades extends ObserverGrafica{

	public ObserverEntidades(Entidad entidad_observada) {
		super(entidad_observada);
		actualizar();
	}
}
