package Requetes;

public class RequeteExterne extends Requete {
	//constructeur si le bool est vrai le bouton haut est presse sinon c'est le bouton bas
	public RequeteExterne (int etage, boolean Haut){
		this.setEtage(etage);
		if (Haut)
			this.setLibelle("Haut");
		else
			this.setLibelle("Bas");
	}
}
