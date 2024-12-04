package Logica.Fabricas;

import Datos.Sonido;

public class FabricaSonido {
	
	private static final String ruta_al_sonido = "./src/Sonidos/";
	
	public static Sonido getSalto() {
		return new Sonido(ruta_al_sonido + "jump.wav");
	}
	
	public static Sonido getMoneda() {
		return new Sonido(ruta_al_sonido + "coin.wav");
	}
	
	public static Sonido getVida() {
		return new Sonido(ruta_al_sonido + "mario-bros-vida.wav");
	}
	
	public static Sonido getPowerUp() {
		return new Sonido(ruta_al_sonido + "power-up-mario.wav");
	}
	
	public static Sonido getInvencible() {
		return new Sonido(ruta_al_sonido + "super-mario-bros-nes-music-star-theme-cut.wav");
	}
	
	public static Sonido getMusicaFondo() {
		return new Sonido(ruta_al_sonido + "musica_fondo.wav");
	}
	
	public static Sonido getSuperado() {
		return new Sonido(ruta_al_sonido + "52ac54_super_mario_bros_stage_clear_sound_effect.wav");
	}
	
	public static Sonido getGolpe() {
		return new Sonido(ruta_al_sonido + "mario-mario-touch-enemy.wav");
	}
	
	public static Sonido getMuerto() {
		return new Sonido(ruta_al_sonido + "mario-bros-die.wav");
	}
	
	public static Sonido getDaño() {
		return new Sonido(ruta_al_sonido + "smb_bump.wav");
	}
	
	public static Sonido getRomperBloque() {
		return new Sonido(ruta_al_sonido + "mario-bros-breakblock.wav");
	}
	
	public static Sonido getGolpeBloque() {
		return new Sonido(ruta_al_sonido + "block-hit.wav");
	}
	
	public static Sonido getBolaDeFuego() {
		return new Sonido(ruta_al_sonido + "smb_fireball.wav");
	}
}
