package IHM;

import java.awt.Color;

import javax.swing.JLabel;

import Client.Ascenseur;
import Client.Batiment;

public class FonctionsUtiles {

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
	
	public static int NbAscenseursParEtage(Batiment bat, int etage){
		int CptAscenseurs = 0;
		for (int i = 1; i <= bat.getNbAscenseur(); ++i){
			if (bat.getAscenseur(i).getEtage() == etage){
				for (int j = 1; j <= bat.getNbEtages(); ++j){
					if (bat.getAscenseur(j).getEtage() == etage)
						++CptAscenseurs;
				}
				return CptAscenseurs;
			}
		}
		return CptAscenseurs;
		
	}
}
