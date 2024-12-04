package Logica;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import Datos.Sonido;
import Logica.Fabricas.FabricaSonido;

public class SoundPlayer{
	private static Clip clip;
	private static long pausePosition = 0;
	
	private SoundPlayer() {
		throw new UnsupportedOperationException("No debo de ser instanciada.");
	}
	
	public static void playFXSalto() {
		cargar(FabricaSonido.getSalto());
	}
	
	public static void playMUSFondo() {
		cargarLoop(FabricaSonido.getMusicaFondo());
	}
	
	public static void playFXMoneda() {
		cargar(FabricaSonido.getMoneda());
	}
	
	public static void playFXVida() {
		cargar(FabricaSonido.getVida());
	}
	
	public static void playFXPowerUp() {
		cargar(FabricaSonido.getPowerUp());
	}
	
	public static void playFXInvencible() {
		cargar(FabricaSonido.getInvencible());
	}
	
	public static void playFXSuperado() {
		cargar(FabricaSonido.getSuperado());
	}
	
	public static void playFXGolpe() {
		cargar(FabricaSonido.getGolpe());
	}
	
	public static void playFXMuerto() {
		cargar(FabricaSonido.getMuerto());
	}
	
	public static void playFXDaño() {
		cargar(FabricaSonido.getDaño());
	}
	
	public static void playFXRomperBloque() {
		cargar(FabricaSonido.getRomperBloque());
	}
	
	public static void playFXGolpeBloque() {
		cargar(FabricaSonido.getGolpeBloque());
	}
	
	public static void playFXBolaDeFuego() {
		cargar(FabricaSonido.getBolaDeFuego());
	}
	
	public static void cargar(Sonido sonido) {
		try {
			String rutaSonido = sonido.getRutaSonido();
            File archivo = new File(rutaSonido);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setMicrosecondPosition(pausePosition); 
            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	
	public static void cargarLoop(Sonido sonido) {
		try {
            // Cargar el archivo de sonido
			String rutaSonido = sonido.getRutaSonido();
            File archivo = new File(rutaSonido);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(clip.LOOP_CONTINUOUSLY);
            clip.setMicrosecondPosition(pausePosition); 
            // Reproducir el sonido
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	
	public static void reanudar() {
		if (clip != null) {
			clip.setMicrosecondPosition(pausePosition);  // Reanuda desde la posición de pausa
            clip.start();
		}
	}
	
	public static void quitar() {
		if (clip != null && clip.isRunning()) {
			clip.close();		
		}
	}
	
    public static void pausa() {
        if (clip != null && clip.isRunning()) {
            pausePosition = clip.getMicrosecondPosition();  // Guarda la posición actual
            clip.stop();
        }
    }

}
