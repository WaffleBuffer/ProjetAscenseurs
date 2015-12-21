
public class RequeteExterne extends Requete {
	//constructeur si le bool est vrai le bouton haut est pressé sinon c'est le bouton bas
	public RequeteExterne (boolean Haut){
		if (Haut)
			this.setLibelle("Appuie bouton haut");
		else
			this.setLibelle("Appuie bouton bas");
	}
}
