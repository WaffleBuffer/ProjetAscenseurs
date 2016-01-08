package Requetes;

public class RequeteInterne extends Requete {
	
	//constructeur pour changer d'�tage
	public RequeteInterne (int nouvelEtage){
		this.setLibelle("Allez a l'etage");
		this.setEtage(nouvelEtage);
	}
	
	//constructeur pour l'arr�t d'urgence
	public RequeteInterne (){
		this.setLibelle("Arr�ter l'ascenseur");
	}

}
