package Datos;

import java.io.*;
import java.util.*;

public class Ranking {

    private List<PuntajeRegister> ranking;
    private static final int MAX_RANKING = 5;
    private String ruta_archivo;

    public Ranking() {
        ranking = new ArrayList<>();
        this.ruta_archivo = "src/Datos/ranking.txt";
        cargarRankingDesdeArchivo(); // Cargar ranking inicial desde el archivo
    }

    // Método para obtener la ruta del archivo
    public String getRutaArchivoRanking() {
        return ruta_archivo;
    }

    // Agregar un nuevo puntaje si es top 5 y guardar el ranking en el archivo
    public void guardarEnArchivoSiEsTop5(PuntajeRegister nuevoPuntaje) {
        cargarRankingDesdeArchivo(); // Cargar los puntajes actuales desde el archivo

        // Agregar el nuevo puntaje y ordenar
        ranking.add(nuevoPuntaje);
        ranking.sort((p1, p2) -> Integer.compare(p2.getPuntaje(), p1.getPuntaje()));

        // Limitar el tamaño del ranking a los 5 mejores
        if (ranking.size() > MAX_RANKING) {
            ranking = ranking.subList(0, MAX_RANKING);
        }

        // Guardar los 5 mejores puntajes en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta_archivo))) {
            for (PuntajeRegister puntaje : ranking) {
                writer.write(puntaje.getNombre() + " " + puntaje.getPuntaje());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar el ranking desde el archivo
    private void cargarRankingDesdeArchivo() {
        ranking.clear(); // Limpiar el ranking actual antes de cargar
        File archivo = new File(ruta_archivo);

        // Verificar si el archivo existe antes de leer
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] partes = linea.split(" ");
                    if (partes.length == 2) {
                        String nombre = partes[0];
                        int puntaje = Integer.parseInt(partes[1]);
                        ranking.add(new PuntajeRegister(nombre, puntaje));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Ordenar el ranking en orden descendente de puntaje
        ranking.sort((p1, p2) -> Integer.compare(p2.getPuntaje(), p1.getPuntaje()));

        // Limitar el tamaño del ranking a los 5 mejores
        if (ranking.size() > MAX_RANKING) {
            ranking = ranking.subList(0, MAX_RANKING);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ranking:\n");
        for (PuntajeRegister p : ranking) {
            sb.append(p.getNombre()).append(" ").append(p.getPuntaje()).append("\n");
        }
        return sb.toString();
    }
    
    public String[] getTopFive() {
    	String[] top_five = new String[5];
    	
    	//Recorre el archivo en 5 lineas para recolectar
    	int lineasAMostrar = 5;  // Número de líneas que queremos mostrar

        try (BufferedReader br = new BufferedReader(new FileReader(ruta_archivo))) {
            String linea;
            int contador = 0;

            while ((linea = br.readLine()) != null && contador < lineasAMostrar) {
            	top_five[contador] = (contador + 1) + ". " + linea;
                contador++;
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    	
    	return top_five;
    }
}

