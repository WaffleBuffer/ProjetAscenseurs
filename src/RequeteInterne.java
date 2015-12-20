
public class RequeteInterne extends Requete {
	
	//constructeur pour changer d'étage
	public RequeteInterne (int nouvelEtage){
		this.setLibelle("Allez à l'étage");
		this.setEtage(nouvelEtage);
	}
	
	//constructeur pour l'arrêt d'urgence
	public RequeteInterne (){
		this.setLibelle("Arrêter l'ascenseur");
	}

}
