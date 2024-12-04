package Logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Datos.Coordenada;
import Datos.Mapa;
import Logica.Enemigos.Enemigo;
import Logica.Fabricas.FabricaElementos;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public class NivelBuilder {
    // Atributos de instancia
    protected Mapa mapaActual; // El mapa que contendrá las coordenadas
    protected Nivel nivelActual;
    protected FabricaElementos factory_elem;
    
    public NivelBuilder(FabricaElementos factory_elem,String ruta) {
        mapaActual = new Mapa();  // Instanciar el mapa
        mapaActual.cargarMapa(ruta);       // Cargar las coordenadas del archivo
        // Instanciar las fábricas que vas a usar
        
        //Crear nivel
        nivelActual = new Nivel();
        this.factory_elem = factory_elem;  // Asegúrate de tener una instancia de la fábrica
    }

    // Método para construir las entidades
    public void buildEntidades() {
        // Obtener el mapeo completo
        HashMap<Integer, List<Coordenada>> mapaCoordenadas = mapaActual.getMapaCoordenadas();

        // Recorrer el mapeo completo
        for (Map.Entry<Integer, List<Coordenada>> entry : mapaCoordenadas.entrySet()) {
            int id = entry.getKey();  // Obtener el ID (clave)
            List<Coordenada> coordenadas = entry.getValue();  // Obtener la lista de coordenadas (valor)

            // Recorrer la lista de coordenadas
            for (Coordenada coord : coordenadas) {
            	
            	//Plataformas lleno
            	if(id>=10 && id<=19) {
            		Plataforma plataforma= buildPlataformas(id, coord);
                	nivelActual.setPlataforma(plataforma);
            	}
            	//Enemigos lleno
            	if(id>=20 && id<=25) {
            		Enemigo enemigo= buildEnemigos(id, coord);
                	nivelActual.setEnemigo(enemigo);	
                }
            	
            	if (id >= 0 && id <= 9) {
            		PowerUp powerup = buildPowerUp(id, coord);
            		nivelActual.setPowerUp(powerup);
            	}

            	if (id == 26) {
            		Mario.getInstancia().setCoordenada(coord);
            	}
            }
        }
    }
    
    public Plataforma buildPlataformas(int id, Coordenada coord) {
    	Plataforma plataforma;
    	switch (id) {
			case 10: // ID para Vacio
				plataforma = factory_elem.getVacio(coord); // Asumiendo que es un método de fábrica
		        break;
			case 11: // ID para Ladrillo
				plataforma = factory_elem.getLadrillo(coord); // Asumiendo que es un método de fábrica
		        break;
			case 12: // ID para TuboInferior
				plataforma = factory_elem.getTuboInferior(coord); // Asumiendo que es un método de fábrica
		        break; 
		    case 13: // ID para Bloque Pregunta
		    	plataforma = factory_elem.getBloquePregunta(coord); // Asumiendo que es un método de fábrica
		        break;
		    case 14: // ID para Bloque Solido
		    	plataforma = factory_elem.getBloqueSolido(coord); // Asumiendo que es un método de fábrica
		        break;
		    case 15: // ID para banderin
		    	plataforma = factory_elem.getBanderin(coord);
		    	break;
		    case 16: // ID para TuboSuperior
		    	plataforma = factory_elem.getTuboSuperior(coord);
		    	break;
		    default:
		        throw new IllegalArgumentException("Tipo de entidad no reconocido: " + id);
    }
    	return plataforma;
    }
    
    public PowerUp buildPowerUp(int id,Coordenada coord) {
    	PowerUp power_up;
    	switch(id){
    		case 00:
    			power_up = factory_elem.getSuperChamp(coord);
    			break;
    		case 1:
    			power_up = factory_elem.getChampVerde(coord);
    			break;
    		case 2:
    			power_up = factory_elem.getFlorFuego(coord);
    			break;
    		case 3:
    			power_up = factory_elem.getMoneda(coord);
    			break;
    		case 04:
    			power_up = factory_elem.getEstrella(coord);
    			break;
    		default :
    			throw new IllegalArgumentException("Tipo de entidad no reconocido: " + id);
    	}
    	
    	return power_up;
    }
    
    public Enemigo buildEnemigos(int id,Coordenada coord) {
    	Enemigo enemigo;
    	switch (id) {
        	case 20: // ID para Enemigo
        		enemigo = factory_elem.getGoomba(coord); // Asumiendo que es un método de fábrica
	            break;
        	case 21: // ID para Enemigo
        		enemigo = factory_elem.getKoopa(coord); // Asumiendo que es un método de fábrica
	            break;
        	case 22: // ID para Enemigo
	        	enemigo = factory_elem.getPiranha(coord); // Asumiendo que es un método de fábrica
	            break; 
	        case 23: // ID para Enemigo
	        	enemigo = factory_elem.getLakitu(coord); // Asumiendo que es un método de fábrica
	            break;
	        case 24: // ID para Enemigo
	        	enemigo = factory_elem.getSpiny(coord); // Asumiendo que es un método de fábrica
	            break;
	        case 25: // ID para Enemigo
	        	enemigo = factory_elem.getBuzzy(coord); // Asumiendo que es un método de fábrica
	            break;
	        default:
	            throw new IllegalArgumentException("Tipo de entidad no reconocido: " + id);
	    }
	    return enemigo;
    }

    // Método para obtener el nivel actual
    public Nivel getNivel() {
        return nivelActual;
    }
}
