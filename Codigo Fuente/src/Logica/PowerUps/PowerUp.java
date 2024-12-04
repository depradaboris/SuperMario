package Logica.PowerUps;
import Datos.Coordenada;
import Grafica.Sprite;
import Logica.Entidad;
import Logica.Enemigos.Enemigo;
import Logica.Plataformas.Plataforma;
import Visitantes.ColisionVisitor;

public abstract class PowerUp extends Entidad{
	
    public PowerUp(Coordenada coord,Sprite spr, int ancho, int alto) {
    	super(coord,spr,ancho,alto);
    }

	@Override
	public void colisionar(Enemigo enemigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accept(ColisionVisitor visitor) {
		visitor.visit(this);
	}

	
	@Override
	public void colisionar(PowerUp power_up) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void colisionar(Plataforma plataforma) {
		// TODO Auto-generated method stub
		
	}


}
