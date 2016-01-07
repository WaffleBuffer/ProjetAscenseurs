package Client;
import java.util.ArrayList;

import Boutons.Bouton;
import Boutons.BoutonExterne;
import Boutons.BoutonHaut;
import Boutons.BoutonInterne;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import IHM.MainWindow;
import Requetes.Requete;
import Requetes.RequeteInterne;

public class Main {

	public static void main(String[] args) {
		MainWindow fenetre = new MainWindow();
		Batiment batim = new Batiment("Hotel", 5);
		ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
		
		ControleurExterne controleurExterne = ControleurExterne.getControleurExterne();
		
		Ascenseur asc1 = new Ascenseur (batim);
		System.out.println(asc1.toString());
		
		ControleurInterne controleur1 = new ControleurInterne(asc1);
		controleursInt.add(controleur1);
		
		BoutonExterne boutonExt0 = new BoutonHaut(0);
		
		boutonExt0.appuyer(controleurExterne);
		
		controleurExterne.choisirAsc(controleursInt);
		
		Requete test = new RequeteInterne (2);
	
		controleur1.addRequete(test);

		boutonExt0.appuyer(controleurExterne);
		
		controleurExterne.choisirAsc(controleursInt);
		
		System.out.println(asc1.toString());
		System.out.println(batim.toString());
		ArrayList<BoutonInterne> listeBoutonsAsc1 = new ArrayList<BoutonInterne>();
		ArrayList<BoutonExterne> listeBoutonsBatim = new ArrayList<BoutonExterne>();
		
		System.out.println("Ascenceur :");
		
		listeBoutonsAsc1 = asc1.getListeBoutons();
		for (BoutonInterne i : listeBoutonsAsc1){
			System.out.println(i.toString());
		}
		
		System.out.println("Batiment :");
		
		listeBoutonsBatim = batim.getListeBoutons();
		for (BoutonExterne i : listeBoutonsBatim){
			System.out.println(i.toString());
		}
		
	}

}

