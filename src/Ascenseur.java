

//Salut, je suis un comm
public class Ascenseur {
	//� quel �tage se trouve  l'ascenseur
	private int etage;
	//l'ascenseur est-il en mouvement ou bien � l'arr�t
	private boolean estEnMouvement;
	//quel est le poids maximum en kg que l'ascenseur est cens� pouvoir supporter
	private int poidsMax;
	//l'ascenseur est-il vide
	private boolean estVide;
	
	//constructeur
	public Ascenseur (){
		etage = 0;					//un nouvel ascenseur est assembl� au rez-de-chauss�e (niveau 0)
		estEnMouvement = false;		//un nouvel ascenseur est immobile car n'a pas encore re�u de requ�te
		estVide = true;				//un nouvel ascenseur ne contient aucun usager
		poidsMax = 300;				//param�tre par d�faut - � changer ou rendre param�trable par l'utilisateur
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
