package Requetes;

public class RequeteExterne extends Requete {
	//constructeur si le bool est vrai le bouton haut est presse sinon c'est le bouton bas
	public RequeteExterne (int etage, String direction){
		this.setEtage(etage);
		this.setLibelle(direction);
	}
}
