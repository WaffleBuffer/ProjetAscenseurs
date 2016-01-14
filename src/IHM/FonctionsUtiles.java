package IHM;

import java.awt.Color;

import javax.swing.JLabel;

import Client.Ascenseur;

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
}
