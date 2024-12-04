package Logica;
import Datos.Coordenada;
import Datos.Parseo;
import Grafica.Juego;
import Grafica.Observers.Observer;
import Inputs.Jugador;
import Logica.Plataformas.Plataforma;
import Logica.PowerUps.PowerUp;
import Visitantes.ColisionVisitor;
import Logica.Enemigos.Enemigo;
import Logica.Estados.*;

public class Mario extends Entidad implements MarioStates { // porque implemtena mario states
	protected SoundPlayer reproductor_efectos;
    protected MarioStates estado;
    protected boolean Saltando = false;
    private static Mario instanciaMario ;
    protected boolean isMovingLeft;
    protected boolean isMovingRight;
    private  int SaltoDistancia = -13;  // Fuerza del salto
    
    private boolean invulnerable;
    
    // Constructor privado
    private Mario(Coordenada coord,int ancho, int alto) {
        super(coord,Juego.factory_spr.getMarioParadoDer(),ancho,alto); // Inicializa la coordenada en el constructor
        this.estado = new Normal();  // Estado predeterminado
        invulnerable = false;
    }

    // Método estático que retorna la única instancia de Mario (Singleton)
    public static Mario getInstancia() {
        if (instanciaMario == null) {
        	//Le insertamos una coordenada temporal.
            instanciaMario = new Mario(Parseo.PosicionInicialMario(Juego.ruta_nivel_actual),16,16); 
        }
        return instanciaMario;
    }
    
    public boolean esInvulnerable() {
    	return invulnerable;
    }
    
    public void setInvulnerable(boolean bool) {
    	invulnerable = bool;
    }
    
    public Observer getObserver() {
    	return observer;
    }

	public MarioStates getState() {
		return estado;
	}
	
	public void setState(MarioStates estado) {
		this.estado = estado;
	}
	
	public void accept(ColisionVisitor visitor) {
		visitor.visit(this);
	}
	
	public void minisalto() {
		VelocidadY = -9;
	}
	public void saltar() {
		if(!Saltando) {
			Saltando = true;
			VelocidadY = SaltoDistancia;
			SoundPlayer.playFXSalto();
		}
	}
	
	public boolean isSaltando() {
		return Saltando;
		//return (Saltando ||( VelocidadY <= 1 && VelocidadY >= 1));
	}

	public boolean isMovingRight(){
		return isMovingRight;
	}
	
	public boolean isMovingLeft(){
		return isMovingLeft;
	}
	
	public boolean isIdle() {
		return VelocidadX == 0;
	}
	
	
	public void setSaltando(boolean b) {
		Saltando = b;
	}

	@Override
	public void colisionar(PowerUp power_up) {
		power_up.colisionar(this);
		estado.update();
	}

	public void colisionar(Plataforma plataforma) {
		int chocadodesdearriba;
		int chocadodesdeabajo;
		int chocadodesdeizquierda;
		int chocadodesdederecha;
		
		//Checkeo de colisiones a nivel general
		
		if (this.getHitbox().getAlto() < 23) {
		chocadodesdearriba = plataforma.getHitbox().getBottom() -  this.getHitbox().getTop();//en valor absoluto
		chocadodesdeabajo = this.getHitbox().getBottom() - plataforma.getHitbox().getTop(); //en valor absoluto
		chocadodesdeizquierda = plataforma.getHitbox().getRight() - this.getHitbox().getLeft();//en valor absoluto
		chocadodesdederecha = this.getHitbox().getRight() - plataforma.getHitbox().getLeft();//en valor absoluto
		}
		else {
			chocadodesdearriba = ( plataforma.getHitbox().getBottom() -  this.getHitbox().getTop()) - 16;//en valor absoluto
			chocadodesdeabajo = (this.getHitbox().getBottom() - plataforma.getHitbox().getTop()) - 16; //en valor absoluto
			chocadodesdeizquierda = plataforma.getHitbox().getRight() - this.getHitbox().getLeft();//en valor absoluto
			chocadodesdederecha = this.getHitbox().getRight() - plataforma.getHitbox().getLeft();//en valor absoluto	
		}
		
		chocadodesdeizquierda = chocadodesdeizquierda -1;
		chocadodesdederecha = chocadodesdederecha -1;
		
		
		if((chocadodesdearriba > chocadodesdeizquierda && chocadodesdearriba > chocadodesdederecha)) {
			this.chocarDesdeArriba(plataforma);
		}
	
		else if( 0.85 * chocadodesdeizquierda > chocadodesdearriba && 0.85 * chocadodesdeizquierda > chocadodesdeabajo) {
			this.chocarDesdeIzquierda(plataforma);
		}
		else if( 0.85 * chocadodesdederecha > chocadodesdearriba &&  0.85 * chocadodesdederecha > chocadodesdeabajo) {
			this.chocarDesdeDerecha(plataforma);
		}
		else if(chocadodesdeabajo > 0.8* chocadodesdeizquierda && chocadodesdeabajo > 0.8*  chocadodesdederecha && chocadodesdeabajo > chocadodesdearriba) {
			this.chocarDesdeAbajo(plataforma);
		}
		//Colisionar de la plataforma especial si toca banderin o vacio
		plataforma.colisionar(instanciaMario);
	}	
	
	private void chocarDesdeArriba(Plataforma plataforma) {
			this.setY(plataforma.getY()-15);
			this.setVelocidadY(0);
			this.setSaltando(false);	
	}
	
	public void chocarDesdeAbajo(Plataforma plataforma) {
			this.setY(plataforma.getY() + this.getHitbox().getAlto());
			this.setVelocidadY(0);
			this.getState().chocarDesdeAbajo(plataforma);
	}
		
	public void chocarDesdeIzquierda(Plataforma plataforma) {
			this.setX(plataforma.getX()-16);
			this.setVelocidadX(0);
	}
	
	public void chocarDesdeDerecha(Plataforma plataforma) {
			this.setX(plataforma.getX()+16);
			this.setVelocidadX(0);
	}

	@Override
	public void update() {
		estado.update();
	}
	
	public void setIzquierda(boolean b) {
		isMovingLeft = b;
	}
	
	public void setDerecha(boolean b) {
		isMovingRight = b;
	}	
		
    public void mover() {
            // Movimiento horizontal
            if (isMovingLeft && !isMovingRight) {
                VelocidadX = -4;
                setLeftOrientation();
            } else if (isMovingRight && !isMovingLeft) {
                VelocidadX = 4;
                setRightOrientation();
            	} 
              else {
            	VelocidadX = 0;  // Se detiene el movimiento si no hay teclas presionadas
              }
            
            VelocidadY = VelocidadY + Gravedad;
            if(VelocidadY > 8)
                VelocidadY = 8;

            this.setX(this.getX() + VelocidadX);
            this.setY(this.getY() + VelocidadY);
            
            update();
            notifyObserver();
    }

	@Override
	public void agregarPuntos(int puntos) {
		Jugador.getInstancia().getPuntaje().sumarPuntos(puntos);
	}

	@Override
	public void colisionar(Mario mario) {
		//No hace nada
	}
	
	@Override
	public void colisionar(Enemigo enemigo) {
		int chocadodesdearriba;
		int chocadodesdeabajo;
		int chocadodesdeizquierda;
		int chocadodesdederecha;
		if (this.getHitbox().getAlto() < 23) {
			chocadodesdearriba = enemigo.getHitbox().getBottom() -  this.getHitbox().getTop();//en valor absoluto
			chocadodesdeabajo = this.getHitbox().getBottom() - enemigo.getHitbox().getTop(); //en valor absoluto
			chocadodesdeizquierda = enemigo.getHitbox().getRight() - this.getHitbox().getLeft();//en valor absoluto
			chocadodesdederecha = this.getHitbox().getRight() - enemigo.getHitbox().getLeft();//en valor absoluto
		}
			else {
				chocadodesdearriba =  (enemigo.getHitbox().getBottom() -  this.getHitbox().getTop()- 16);//en valor absoluto
				chocadodesdeabajo = (this.getHitbox().getBottom() - enemigo.getHitbox().getTop() -  16); //en valor absoluto
				chocadodesdeizquierda = enemigo.getHitbox().getRight() - this.getHitbox().getLeft();//en valor absoluto
				chocadodesdederecha = this.getHitbox().getRight() - enemigo.getHitbox().getLeft();//en valor absoluto
				}
		
		chocadodesdeizquierda = chocadodesdeizquierda -4;
		chocadodesdederecha = chocadodesdederecha -4;
		
		
		if((chocadodesdearriba > chocadodesdeizquierda && chocadodesdearriba > chocadodesdederecha && chocadodesdearriba > chocadodesdeabajo)) {
			estado.colisionarDesdeArriba(enemigo);
			SoundPlayer.playFXDaño();
			}
			else 
				estado.colisionarDesdeOtroLado(enemigo);
	}
		
	public void lanzarBolaDeFuego() {
		estado.lanzarBolaDeFuego();
	}

	@Override
	public void colisionarDesdeArriba(Enemigo enemigo) {
		//
	}

	@Override
	public void colisionarDesdeOtroLado(Enemigo enemigo) {
		//
	}

	@Override
	public void colisionarConFlor() {
		estado.colisionarConFlor();
	}

	@Override
	public void colisionarConSuperChamp() {
		estado.colisionarConSuperChamp();
	}

	@Override
	public void colisionarConEstrella( ) {
		estado.colisionarConEstrella();
	}

	@Override
	public void recibirDaño(int puntaje) {
		estado.recibirDaño(puntaje);
		SoundPlayer.playFXGolpe();
	}
}
