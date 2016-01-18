package IHM;

import java.awt.Color;

import javax.swing.JLabel;

import Client.Ascenseur;
import Client.Batiment;

/**Classe statique possedant des fonctions utiles pour l'IHM
 * @author Thomas
 */
public abstract class FonctionsUtiles {

	/**Permet d'obtenir le {@link String} d'etage par son numero
	 * @param etage le numero de l'etage dont on veut la {@link String}.
	 * @return le {@link String} de l'etage. 
	 */
	public static String nommerEtage(int etage){
		if (etage == 0)
			return "Ground floor";
		else if (etage == 1)
			return etage + "st floor";
		else if ((etage - 1)%10 == 1)
			return etage + "nd floor";
		else if ((etage - 2)%10 == 1)
			return etage + "rd floor";
		else 
			return etage + "th floor";
	}
	
	/**Permet de changer la couleur de fond du {@link JLabel} label selon l'etat de l'{@link Ascenseur} asc
	 * @param asc l'{@link Ascenseur} sur lequel on verifie l'etat.
	 * @param label le {@link JLabel} sur lequel doit s'appliquer la couleur.
	 */
	public static void afficherEtatAscenseur(Ascenseur asc, JLabel label){
		if (asc.estBloquer())
			label.setBackground(Color.red);
		else if (asc.isEstEnMouvement())
			label.setBackground(Color.blue);
		else if (!asc.isPortesOuvertes())
			label.setBackground(Color.orange);
		else 
			label.setBackground(Color.green);
	}
	
	/**Permet de connaitre le nombre d'{@link Ascenseur} pour l'etage etage du {@link Batiment} bat.
	 * @param bat le {@link Batiment} dont on verifie la presence d'{@link Ascenseur}.
	 * @param etage l'etage pour lequel on verifie la presence d'{@link Ascenseur}.
	 * @return le nombre d'{@link Ascenseur} a l'etage etage du {@link Batiment} bat.
	 */
	public static int NbAscenseursParEtage(Batiment bat, int etage){
		int CptAscenseurs = 0;
		for (int i = 0; i < bat.getNbAscenseurs(); ++i){
			if (bat.getAscenseur(i).getEtage() == etage){
				++CptAscenseurs;
			}
		}
		return CptAscenseurs;
		
	}
}
