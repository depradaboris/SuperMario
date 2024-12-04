package Grafica.Observers;

import Datos.Puntaje;
import Grafica.ControladorVista;

public class ObserverPuntaje implements Observer {
    private ControladorVista controladorVista; // Referencia al controlador de la vista
    private Puntaje puntaje; // Referencia al puntaje

    public ObserverPuntaje(ControladorVista controladorVista, Puntaje puntaje) {
        this.controladorVista = controladorVista;
        this.puntaje = puntaje;
    }

    @Override
    public void actualizar() {
        // Actualiza el puntaje en la vista
        controladorVista.actualizarPuntaje(); // Llama al método para actualizar el puntaje en la vista
    }
}
