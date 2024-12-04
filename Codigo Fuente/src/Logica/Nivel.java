package Logica;

import java.util.LinkedList;
import java.util.List;

import Datos.Coordenada;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public class Nivel {
    protected List<Enemigo> listaEnemigos;
    protected List<Plataforma> listaPlataformas;
    protected List<PowerUp> listaPowerUps;
    protected List<BolaDeFuego> listaBolaDeFuego;
    
    protected Coordenada spawn_mario;

    public Nivel() {
        listaEnemigos = new LinkedList<Enemigo>();
        listaPlataformas = new LinkedList<Plataforma>();
        listaPowerUps = new LinkedList<PowerUp>();
        listaBolaDeFuego = new LinkedList<BolaDeFuego>();
       
        spawn_mario = new Coordenada(0,0);
    }

    public Coordenada getMarioOrigin() {
    	return spawn_mario;
    }
    
    public void setMarioOrigin(Coordenada coord) {
    	spawn_mario = new Coordenada(coord.getX(),coord.getY());
    }
    
    // Getters y Setters para Enemigos
    public List<Enemigo> getListaEnemigos() {
        return listaEnemigos;
    }

    public void setEnemigo(Enemigo enemigo) {
        listaEnemigos.add(enemigo);
    }

    public void setListaEnemigos(List<Enemigo> listaEnemigos) {
        this.listaEnemigos = listaEnemigos;
    }

    // Getters y Setters para Plataformas
    public List<Plataforma> getListaPlataformas() {
        return listaPlataformas;
    }

    public void setPlataforma(Plataforma plataforma) {
        listaPlataformas.add(plataforma);
    }

    public void setListaPlataformas(List<Plataforma> listaPlataformas) {
        this.listaPlataformas = listaPlataformas;
    }

    // Getters y Setters para PowerUps
    public List<PowerUp> getListaPowerUps() {
        return listaPowerUps;
    }

    public void setPowerUp(PowerUp powerUp) {
        listaPowerUps.add(powerUp);
       // System.out.println("cree un power upp");
    }

    public void setListaPowerUps(List<PowerUp> listaPowerUps) {
        this.listaPowerUps = listaPowerUps;
    }

 
    public void setBolaDeFuego(BolaDeFuego bola) {
    	listaBolaDeFuego.add(bola);
    }
    
    public List<BolaDeFuego> getListaBolaDeFuego(){
    	return listaBolaDeFuego;
    }
    
    // Getters y Setters para Mario
    
	public List<Entidad> getListaEntidades() {
		List<Entidad> ent_list = new LinkedList<Entidad>();
		
		for (Enemigo enemigo : listaEnemigos) { ent_list.add(enemigo);}
		
		for (Plataforma plat : listaPlataformas) { ent_list.add(plat); }
		
		for (PowerUp power : listaPowerUps) { ent_list.add(power); }
			
		return ent_list;
	}

	public void eliminarEnemigo(Enemigo enemigo) {
	    if (listaEnemigos.contains(enemigo)) {
	        listaEnemigos.remove(enemigo);
	        enemigo.notifyObserver(); // Notificar al observador
	    }
	}
}
