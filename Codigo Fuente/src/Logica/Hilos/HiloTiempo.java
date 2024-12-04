package Logica.Hilos;

import Grafica.Juego;

public class HiloTiempo extends Thread {
    private int tiempo; // Tiempo en segundos
    private boolean running;

    public HiloTiempo() {
        this.tiempo = 60; // Comienza en 60 segundos
        this.running = true;
    }

    @Override
    public void run() {
        while (running && tiempo > 0) {
            try {
                Thread.sleep(1000); // Espera 1 segundo
                tiempo--; // Decrementa el tiempo
                if (tiempo <= 0) {
                    // Aquí puedes manejar el evento cuando el tiempo se agota
                    Juego.reiniciarNivel(); // Reinicia el nivel
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void detener() {
        running = false;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void reiniciar() {
        this.tiempo = 60; // Reinicia el tiempo a 60 segundos
    }
}

