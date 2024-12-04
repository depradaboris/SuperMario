package Logica;

import Grafica.Sprite;
import Grafica.Observers.Observer;

public abstract class EntidadBase {
	public abstract Sprite getSprite();
	public abstract Hitbox getHitbox();
	public abstract void setObserver(Observer observer);
}
