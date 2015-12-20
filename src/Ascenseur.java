
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
		etage = 0;
		estEnMouvement = false;
		estVide = true;
		poidsMax = 300;
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
