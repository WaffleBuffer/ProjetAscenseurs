package Requetes;

public class RequeteInterne extends Requete {
	
	//constructeur pour changer d'�tage
	public RequeteInterne (int nouvelEtage){
		this.setLibelle("Allez � l'�tage");
		this.setEtage(nouvelEtage);
	}
	
	//constructeur pour l'arr�t d'urgence
	public RequeteInterne (){
		this.setLibelle("Arr�ter l'ascenseur");
	}

}
