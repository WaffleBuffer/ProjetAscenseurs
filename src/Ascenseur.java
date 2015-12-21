
public class Ascenseur {
	//à quel étage se trouve  l'ascenseur
	private int etage;
	//l'ascenseur est-il en mouvement ou bien à l'arrêt
	private boolean estEnMouvement;
	//quel est le poids maximum en kg que l'ascenseur est censé pouvoir supporter
	private int poidsMax;
	//l'ascenseur est-il vide
	private boolean estVide;
	
	//constructeur
	public Ascenseur (){
		etage = 0;					//un nouvel ascenseur est assemblé au rez-de-chaussée (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore reçu de requête
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		poidsMax = 300;				//paramètre par défaut - à changer ou rendre paramétrable par l'utilisateur
	}

	@Override
	public String toString() {
		return "Ascenseur [etage=" + etage + ", estArrete=" + estEnMouvement + ", poidsMax=" + poidsMax + ", estVide="
				+ estVide + "]";
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}
}
