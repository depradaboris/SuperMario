package Grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;   
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Datos.Constantes;
import Datos.Parseo;
import Datos.Puntaje;
import Grafica.Observers.ObserverBolaDeFuego;
import Grafica.Observers.ObserverEntidades;
import Grafica.Observers.ObserverPuntaje;
import Inputs.EntradaTeclado;
import Inputs.Jugador;                  
import Logica.BolaDeFuego;
import Logica.Entidad;
import Logica.Mario;
import Logica.Nivel;
import Logica.NivelBuilder;
import Logica.SoundPlayer;
import Logica.Enemigos.Enemigo;
import Logica.Estados.Normal;
import Logica.Fabricas.FabricaElementos;
import Logica.Fabricas.FabricaSpritesBase;
import Logica.Hilos.HiloTiempo;
import Logica.Fabricas.FabricaSprites;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;

public class Juego implements ActionListener{
	
	public static FabricaSpritesBase factory_spr;
   	public static FabricaElementos factory_elem;
   	
   	private static HiloTiempo hilo_tiempo;
   	
    protected static NivelBuilder nivel_builder;
    public static Nivel nivel_actual;
    public static int numero_nivel_actual;
    public static String ruta_nivel_actual;
    
    protected static JFrame ventana;
    
    protected static String ruta_carpeta_niveles;
    protected static String[] ruta_niveles;
    
    protected ObserverEntidades observer_mario;

    private String ruta_skin;
    
    private static Jugador jugador;             	   // Instancia del jugador
    public static ControladorVista ctrl_vista; // Instancia del controlador vista
	
    public Juego(String nombre_skin) {
        // Inicia las fábricas
    	setRutaSkin(nombre_skin);
    	
    	factory_spr = new FabricaSprites(ruta_skin);
        factory_elem = new FabricaElementos(factory_spr);
   
        // Inicializa el jugador
        jugador = new Jugador();
        
        // Iniciar ventana y panel.
        ventana = new JFrame("Mi Juego");
        
    	hilo_tiempo = new HiloTiempo();
        
        ctrl_vista = new ControladorVista(jugador,ventana);
        
        //Preparamos una coleccion de niveles
        ruta_niveles= new String[3];
        ruta_carpeta_niveles = "src/Datos/Mapas/";
        numero_nivel_actual = 1;
    }
    
    public static int obtenerTiempoActual() {
    	return hilo_tiempo.getTiempo();
    }
    
    public static void iniciarTiempo() {
        // Si ya hay un hilo de tiempo en ejecución, lo detenemos
        if (hilo_tiempo != null) {
            hilo_tiempo.detener();
        }

        // Crear una nueva instancia de hiloTiempo
        hilo_tiempo = new HiloTiempo();
        hilo_tiempo.start(); // Iniciar el hilo de tiempo
    }
    
    public static void iniciar() {
        SoundPlayer.playMUSFondo();
    	crearNivel();
        ctrl_vista.iniciar();
        ctrl_vista.setNivel(nivel_actual);
        EntradaTeclado.addOyenteTeclado(ctrl_vista);
        iniciarVentana();
    }
    
    
    
	private void setRutaSkin(String nombre_skin_actual) {
    	switch(nombre_skin_actual) {
		case("Default"):
			ruta_skin = "Assets/SkinOriginal/";
			break;
		case("Papercut-4"):
			ruta_skin = "Assets/SkinGris/";
			break;
    	}
	}
    
    private static void obtenerRutaMapas(String ruta) {
    	ruta_niveles[0] = ruta + "mapa_1";
    	ruta_niveles[1] = ruta + "mapa_2";
    	ruta_niveles[2] = ruta + "mapa_3";
	}
    
	public static void crearNivel() {
    	obtenerRutaMapas(ruta_carpeta_niveles);
    	ruta_nivel_actual = ruta_niveles[numero_nivel_actual - 1];
        nivel_builder = new NivelBuilder(factory_elem,ruta_nivel_actual);
    	nivel_builder.buildEntidades();
        nivel_actual = nivel_builder.getNivel();
        registrarObservers(nivel_actual);
        Mario.getInstancia().setState(new Normal());
        Mario.getInstancia().setCoordenada(Parseo.PosicionInicialMario(ruta_nivel_actual));
        iniciarTiempo();
	}

	protected static void registrarObservers(Nivel nivel_actual) {
		registrarObserverMario(Mario.getInstancia());
		registrarObserverEnemigos(nivel_actual);
		registrarObserverPlataforma(nivel_actual);
		registrarObserverPowerUp(nivel_actual);
		registrarObserverPuntaje(Jugador.getInstancia().getPuntaje());
	}
	
    protected static void registrarObserverPuntaje(Puntaje puntaje) {
        ObserverPuntaje observer = new ObserverPuntaje(ctrl_vista, puntaje); // Crea un nuevo observador
        puntaje.setObserver(observer); // Asocia el observador con el puntaje
        // No es necesario agregar el observador a la vista
    }
	
	protected static void registrarObserverMario(Mario mario) {
		ObserverEntidades observer = new ObserverEntidades(mario);
		mario.setObserver(observer);
		ctrl_vista.add(observer);
	}
	
	public static void registrarObserverBolaDeFuego(BolaDeFuego bola) {
		ObserverBolaDeFuego observer = new ObserverBolaDeFuego(bola);
		bola.setObserver(observer);
		ctrl_vista.add(observer);
	}
	
	public static Nivel esteNivel() {
		return nivel_actual;
	}
	
	protected static void registrarObserverEnemigos(Nivel nivel_actual) {
		for(Enemigo enemigo :nivel_actual.getListaEnemigos()) {
			ObserverEntidades observer = new ObserverEntidades(enemigo);
			enemigo.setObserver(observer);
			ctrl_vista.add(observer);
			ctrl_vista.setComponentZOrder(observer, 1);
		}
	}
	
	protected static void registrarObserverPlataforma(Nivel nivel_actual) {
		for(Plataforma plataforma :nivel_actual.getListaPlataformas()) {
			ObserverEntidades observer = new ObserverEntidades(plataforma);
			plataforma.setObserver(observer);
			ctrl_vista.add(observer);
			ctrl_vista.setComponentZOrder(observer, 0);
		}
	}
	
	protected static void registrarObserverPowerUp(Nivel nivel_actual) {
		for(PowerUp powerup :nivel_actual.getListaPowerUps()) {
			ObserverEntidades observer = new ObserverEntidades(powerup);
			powerup.setObserver(observer);
			ctrl_vista.add(observer);
		}
	}
	
	public static void registrarObserverEntidad(Entidad ent) {
		ObserverEntidades observer = new ObserverEntidades(ent);
		ent.setObserver(observer);
		ctrl_vista.add(observer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Que hace esto
	}
	
	protected static void iniciarVentana(){
		ventana.pack(); // Ajustar tamaño de ventana al panel
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setSize(Constantes.ANCHO_VENTANA,Constantes.ALTO_VENTANA);
        ventana.setLocationRelativeTo(null);
        
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
	}
	
	public static void cambiarNivel() {
	    numero_nivel_actual += 1;

	    if (numero_nivel_actual == 4) {
	        ctrl_vista.mostrarCuadroDialogoGameOver();
	    } else {
	        // Borra lo visible del nivel
	        ctrl_vista.removeAll();
	        
	        // Crea un nuevo nivel
	        crearNivel();
	        ctrl_vista.setNivel(nivel_actual);
	        ctrl_vista.resetCamara();
	        
	        // Reinicia el tiempo
	        iniciarTiempo();
	    }
	}

	public static void reiniciarNivel() {
	    // Cualquier otra lógica que necesites para reiniciar el nivel
	    ctrl_vista.removeAll();
	    Jugador.getInstancia().decrementarVidas();
	    
	    // Crea un nuevo nivel
	    crearNivel();
	    ctrl_vista.setNivel(nivel_actual);
	    ctrl_vista.resetCamara();
	    
	    // Reinicia el tiempo
	    iniciarTiempo();
	}

}