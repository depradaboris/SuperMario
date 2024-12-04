package Grafica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;    
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Datos.Constantes;
import Datos.PuntajeRegister;
import Datos.Ranking;
import Grafica.Observers.Observer;
import Grafica.Observers.ObserverEntidades;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Inputs.Jugador;
import Logica.BolaDeFuego;
import Logica.Mario;
import Logica.Nivel;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

import Visitantes.*;


public class ControladorVista extends JPanel implements ActionListener, Runnable {
    private Jugador jugador;             // Instancia del jugador
    private String rutaImagen;
    protected JFrame ventana;

    protected ControladorCamara ctrl_camara;
    protected Observer obs_mario1;
    protected ObserverEntidades obs_entidades;
    protected Nivel nivel_actual;
    
    protected JLabel labelPuntaje;  // Etiqueta para mostrar el puntaje
    protected JLabel labelVidas;
    protected JLabel labelTiempo;
    
    protected Ranking ranking;
    
    //protected 
    public ControladorVista(Jugador jugador,JFrame ventana) {
        this.jugador = jugador; // Inicializa el jugador
        this.setLayout(null); //si o si tiene que ser null.
        setFocusable(true);
        this.ventana = ventana;
        this.ranking = new Ranking();
	     //Inicializa y configura el JLabel para el puntaje

        añadirInterfazJugador();
    }
    
    public void añadirInterfazJugador() {   
	     // Crear el panel con GridLayout de 1 fila y 2 columnas
	    JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 10, 0)); // 10 píxeles de espacio horizontal
	    // Crear y configurar labelPuntaje
	    labelPuntaje = new JLabel("Puntaje: " + jugador.getPuntaje().getPuntajeActual());
	    labelPuntaje.setForeground(Color.BLUE);
	    panelSuperior.add(labelPuntaje); // Agrega labelPuntaje al panel
	
	    // Crear y configurar labelTiempo
	    labelTiempo = new JLabel("Tiempo: " + Juego.obtenerTiempoActual());
	    labelTiempo .setForeground(Color.BLACK);
	    panelSuperior.add(labelTiempo);
	    
	    // Crear y configurar labelVidas
	    labelVidas = new JLabel("Vidas: " + jugador.getVidas());
	    labelVidas.setForeground(Color.RED);
	    panelSuperior.add(labelVidas); // Agrega labelVidas al panel
	
	    // Agregar el panel al borde norte del JFrame
	    ventana.add(panelSuperior, BorderLayout.NORTH);       
    }
    
    public void iniciar() {
        ctrl_camara = new ControladorCamara(this);
    	requestFocusInWindow();
    	establecerScroll();
    	Thread hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    public void establecerScroll() {
        setPreferredSize(new Dimension(Constantes.ANCHO_NIVEL, Constantes.ALTO_NIVEL));
    	JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scrollPane.setBounds(0, 0, Constantes.ANCHO_NIVEL, Constantes.ALTO_NIVEL);
        ventana.add(scrollPane);
    }
    
    // Método para actualizar el puntaje en el JLabel
    public void actualizarPuntaje() {
        labelPuntaje.setText("Puntaje: " + Jugador.getInstancia().getPuntaje().getPuntajeActual());
    }
    
    public void actualizarVidas() {
    	labelVidas.setText("Vidas: " + Jugador.getInstancia().getVidas());
    }
    
    public void actualizarTiempo() {
    	labelTiempo.setText("Tiempo: " + Juego.obtenerTiempoActual());
    }

    public void resetCamara() {
    	ctrl_camara = new ControladorCamara(this);
    }
    
    //@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Dibujar fondo
        rutaImagen = Juego.factory_spr.getFondo().getRutaImagen();
        ImageIcon imagen = new ImageIcon("./src/" + rutaImagen);
        g.drawImage(imagen.getImage(), 0, 0, Constantes.ANCHO_FONDO, Constantes.ALTO_FONDO, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Actualizar el dibujo
    }
    
    public void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

    @Override
    public void run() {
        while (true) {
            MarioColisionVisitor colision_visitor_mario = new MarioColisionVisitor(Mario.getInstancia());
            if (nivel_actual != null) {
                
                Mario.getInstancia().mover();

                // Lista temporal para enemigos a eliminar
                List<Enemigo> enemigosAEliminar = new LinkedList<>();

                // Iterar sobre una copia de la lista de enemigos para evitar ConcurrentModificationException
                List<Enemigo> enemigos = new LinkedList<>(nivel_actual.getListaEnemigos());
                for (Enemigo enemigo : enemigos) {
                    ColisionVisitorEnemigo colision_visitor_enemigo = new ColisionVisitorEnemigo(enemigo);
                    Mario.getInstancia().accept(colision_visitor_mario);
                    colision_visitor_mario.visit(enemigo, nivel_actual);

                    if (!enemigo.esColisionable()) {
                        enemigosAEliminar.add(enemigo); // Agregar a la lista temporal
                    } else {
                        enemigo.mover();
                    }

                    for (Plataforma plataforma : nivel_actual.getListaPlataformas()) {
                        colision_visitor_enemigo.visit(plataforma);
                    }
                }

                for (Plataforma plataforma : nivel_actual.getListaPlataformas()) {
                    colision_visitor_mario.visit(plataforma);
                }

                for (PowerUp power_up : nivel_actual.getListaPowerUps()) {
                    colision_visitor_mario.visit(power_up);
                    power_up.mover();
                }

                // Duplico la lista original de bola de fuego para evitar modificarla y usarla a la vez
                // Primero, mover todas las bolas de fuego
                List<BolaDeFuego> lista_bola = new CopyOnWriteArrayList<>(nivel_actual.getListaBolaDeFuego());
                for (BolaDeFuego bola : lista_bola) {
                    bola.mover();
                }

                // Luego, verificar colisiones de las bolas de fuego con los enemigos
                for (BolaDeFuego bola : lista_bola) {
                    ColisionBolaFuego colision_visitor_bola = new ColisionBolaFuego(bola);
                    bola.accept(colision_visitor_bola);

                    for (Enemigo enemigo : enemigos) {
                        colision_visitor_bola.visit(enemigo, nivel_actual);
                    }

                    for (Plataforma plataforma : nivel_actual.getListaPlataformas()) {
                        colision_visitor_bola.visit(plataforma);
                    }
                }

                ctrl_camara.moverCamara();

                // Eliminar enemigos después de la iteración
                for (Enemigo enemigo : enemigosAEliminar) {
                    nivel_actual.eliminarEnemigo(enemigo);
                }
            } else {
                System.out.print("No hay nivel_actual");
            }

            this.actualizarVidas();
            this.actualizarPuntaje();
            this.actualizarTiempo();

            //Verificar si las vidas son cero
            if (Jugador.getInstancia().getVidas() == 0) {
                mostrarCuadroDialogoGameOver();
                break; // Salir del bucle cuando el juego termina
            }

            
            repaint();

            try {
                Thread.sleep(16); // Mantiene aproximadamente 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostrarCuadroDialogoGameOver() {
        // Mostrar cuadro de diálogo para ingresar el nombre
        String nombreJugador = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Game Over", JOptionPane.QUESTION_MESSAGE);
        
        if (nombreJugador != null && !nombreJugador.trim().isEmpty()) {
        	PuntajeRegister puntaje_register = new PuntajeRegister(nombreJugador,Jugador.getInstancia().getPuntaje().getPuntajeActual());
        	ranking.guardarEnArchivoSiEsTop5(puntaje_register);
        	System.exit(0);
            // Realiza alguna acción, como reiniciar el juego o finalizarlo
        }
    }

	public void setNivel(Nivel nivel_actual) {
		this.nivel_actual = nivel_actual;
	}

}
