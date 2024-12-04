package Logica.Estados;
import Grafica.Sprite;
import Logica.Mario;
import Logica.SoundPlayer;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;

public abstract class EstadoBase{
	
	public SoundPlayer reproductor;
	protected Sprite caminando_izq;
	protected Sprite caminando_der;
	protected Sprite parado_izq;
	protected Sprite parado_der;
	protected Sprite saltando_izq;
	protected Sprite saltando_der;
	protected Sprite muerto;

	public void update( ) {
		if(Mario.getInstancia().isSaltando()) {
			actualizarSpriteSaltando();
		}else{
			if (Mario.getInstancia().isIdle())
				actualizarSpriteQuieto();
				
			else
				actualizarSpriteCaminando();
		}
	}
	
	public void actualizarSpriteCaminando() {
			if(Mario.getInstancia().isLookingRight())
				Mario.getInstancia().setSprite(caminando_der);
			if(Mario.getInstancia().isLookingLeft())
				Mario.getInstancia().setSprite(caminando_izq);
	}

	public void actualizarSpriteSaltando() {
			if(Mario.getInstancia().isLookingRight())
				Mario.getInstancia().setSprite(saltando_der);
			if(Mario.getInstancia().isLookingLeft())
				Mario.getInstancia().setSprite(saltando_izq);
	}

	public void actualizarSpriteQuieto() {
			if (Mario.getInstancia().isLookingRight())
				Mario.getInstancia().setSprite(parado_der);	
			if(Mario.getInstancia().isLookingLeft())
				Mario.getInstancia().setSprite(parado_izq);
	}

	
	public void colisionarDesdeArriba(Enemigo enemigo) {};
	public void colisionarDesdeOtroLado(Enemigo enemigo) {};
	public void chocarDesdeAbajo(Plataforma plataforma){};
	public void recibirDaño(){};
	public void lanzarBolaDeFuego(){};
	
	public void colisionarConEstrella(){};
	public void colisionarConFlor(){};
	public void colisionarConSuperChamp(){};
}
