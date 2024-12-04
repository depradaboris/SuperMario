package Logica.Fabricas;

import Grafica.Sprite;

public abstract class FabricaSpritesBase {
	
	protected String ruta_al_sprite;
	
	protected FabricaSpritesBase(String ruta_al_sprite) {
		this.ruta_al_sprite = ruta_al_sprite;
	}
	
	public Sprite getMenu() {
		return new Sprite(ruta_al_sprite + "Menu/SuperMarioMenu.png");
	}
	
	public Sprite getBanderin() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_banderin.png");
	}
	
	public Sprite getTransparencia() {
		//No es necesario que este sprite cambie, por eso tiene una ruta fija
		return new Sprite("Assets/transparencia.png");
	}
	
	public Sprite getBolaDeFuego() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/bolaDeFuego.gif");
	}
	
	public Sprite getFondo() {
		return new Sprite(ruta_al_sprite + "Escenario/bkgd_main.png");
	}
	
	//Enemigos
	
	public Sprite getGoomba() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_goomba_caminando.gif");
	}
	
	public Sprite getKoopa() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_koopa_caminando.gif");
	}
	
	public Sprite getKoopa2() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_koopa_muerto.png");
	}
	
	public Sprite getLakitu() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_lakitu.png");
	}
	
	public Sprite getPiranha() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_piranha.gif");
	}
	
	public Sprite getSpiny() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_spiny_caminando.gif");
	}
	
	public Sprite getBuzzy() {
		return new Sprite(ruta_al_sprite + "Entidades/Enemigos/spr_buzzy_caminando.gif");
	}
	
	//PowerUps
	
	public Sprite getSuperChamp() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/spr_champiñon_rojo.png");
	}
	
	public Sprite getChampVerde() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/spr_champiñon_verde.png");
	}
	
	public Sprite getFlor() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/spr_flor.gif");
	}
	
	public Sprite getEstrella() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/spr_estrella.gif");
	}
	
	public Sprite getMoneda() {
		return new Sprite(ruta_al_sprite + "Entidades/PowerUps/spr_moneda.gif");
	}
	
	//Bloques
	
	public Sprite getBloquePregunta() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_bloque_pregunta.gif");
	}
	
	public Sprite getLadrillo() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_labrillo.png");
	}
	
	public Sprite getTuboInferior() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_tubo_inferior.png");
	}
	
	public Sprite getTuboSuperior() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_tubo_superior.png");
	}
	
	public Sprite getVacio() {
		return new Sprite(ruta_al_sprite + "Escenario/spr_vacio.png");
	}
	
	public Sprite getBloqueSolido(){
		return new Sprite(ruta_al_sprite + "Escenario/spr_bloque_solido.png");
	}
	
	//Normal
	
	public Sprite getMarioCaminandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_caminando_izq.gif");
	}
	public Sprite getMarioCaminandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_caminando_der.gif");
	}
	public Sprite getMarioParadoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_parado_der.png");
	}
	public Sprite getMarioParadoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_parado_izq.png");
	}
	public Sprite getMarioSaltandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_saltando_der.png");
	}
	public Sprite getMarioSaltandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_saltando_izq.png");
	}	
	public Sprite getMarioMuerto() {
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Normal/spr_mario_muerto.png");
	}
	
	//Super
	
	public Sprite getSuperMarioCaminandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_caminando_izq.gif");
	}
	public Sprite getSuperMarioCaminandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_caminando_der.gif");
	}
	public Sprite getSuperMarioParadoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_parado_der.png");
	}
	public Sprite getSuperMarioParadoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_parado_izq.png");
	}
	public Sprite getSuperMarioSaltandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_saltando_der.png");
	}
	public Sprite getSuperMarioSaltandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Super/spr_mario_grande_saltando_izq.png");
	}
	
	//Fuego
	public Sprite getFuegoMarioCaminandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_caminando_izq.gif");
	}
	public Sprite getFuegoMarioCaminandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_caminando_der.gif");
	}
	public Sprite getFuegoMarioParadoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_parado_der.png");
	}
	public Sprite getFuegoMarioParadoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_parado_izq.png");
	}
	public Sprite getFuegoMarioSaltandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_saltando_der.png");
	}
	public Sprite getFuegoMarioSaltandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Fuego/spr_mario_fuego_saltando_izq.png");
	}
	
	//Invencible
	public Sprite getInvencibleMarioCaminandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_caminando_izq.gif");
	}
	public Sprite getInvencibleMarioCaminandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_caminando_der.gif");
	}
	public Sprite getInvencibleMarioParadoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_parado_der.gif");
	}
	public Sprite getInvencibleMarioParadoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_parado_izq.gif");
	}
	public Sprite getInvencibleMarioSaltandoDer(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_saltando_der.gif");
	}
	public Sprite getInvencibleMarioSaltandoIzq(){
		return new Sprite(ruta_al_sprite + "Entidades/Mario/Invencible/spr_mario_invencible_saltando_izq.gif");
	}
}
