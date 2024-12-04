package Logica.Hilos;

import Logica.Mario;

public class HiloInvulnerabilidad extends Thread{
	
private int tiempo;
	
	public HiloInvulnerabilidad(int tiempo) {
		this.tiempo = tiempo;
	}
	
	public int getTiempo() {
		return tiempo;
	}
	
	public void run() {
		Mario.getInstancia().setInvulnerable(true);
		while(true) {
			tiempo--;
			 if (tiempo < 0) {
				 Mario.getInstancia().setInvulnerable(false);;
             }
			 try {
                 Thread.sleep(1000); // Pausa de 1 segundo
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
		}
	}
}
