package Datos;

import java.util.ArrayList;
import java.util.List;

import Grafica.Observers.Observer;

public class Puntaje {
	
    private int puntajeActual;
    private List<Puntaje> ranking;
    private Observer observer;
    
    
    public Puntaje() {
        puntajeActual = 0;
        setRanking(new ArrayList<>());
    }

    
    public void sumarPuntos(int puntos) {
        puntajeActual += puntos;
        System.out.println("Puntos actuales: " + this.puntajeActual);
        this.observer.actualizar();
    }
    
    public int getPuntajeActual() {
        return puntajeActual;
    }
    
    public void setObserver(Observer observer) {
		this.observer = observer;
	}
	
	public Observer getObserver() {
		return observer;
	}


	public List<Puntaje> getRanking() {
		return ranking;
	}


	public void setRanking(List<Puntaje> ranking) {
		this.ranking = ranking;
	}
    
}