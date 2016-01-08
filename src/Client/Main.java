package Client;
import java.util.ArrayList;

import Boutons.BoutonExterne;
import Boutons.BoutonInterne;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import IHM.ConfigWindow;
import IHM.MainWindow;

public class Main {

	public static void main(String[] args) {
		Batiment batim = new Batiment("Hotel", 5);
		ArrayList<ControleurInterne> controleursInt = new ArrayList<ControleurInterne>();
		
		ControleurExterne controleurExterne = ControleurExterne.getControleurExterne();
		
		Ascenseur asc1 = new Ascenseur (batim);
		ConfigWindow fen = new ConfigWindow();
		System.out.println(asc1.toString());
		
		ControleurInterne controleur1 = new ControleurInterne(asc1);
		controleursInt.add(controleur1);
		
		asc1.getListeBoutons().get(3).appuyer(controleur1);
		controleur1.traiterRequetes();
		System.out.println(asc1.toString());
		
		batim.getListeBoutons().get(0).appuyer(controleurExterne);
		controleurExterne.traiterRequetes(controleursInt);
		
		for (ControleurInterne i : controleursInt) {
			i.traiterRequetes();
		}
		
		System.out.println(asc1.toString());
		System.out.println(batim.toString());
		ArrayList<BoutonInterne> listeBoutonsAsc1 = new ArrayList<BoutonInterne>();
		ArrayList<BoutonExterne> listeBoutonsBatim = new ArrayList<BoutonExterne>();
		
		System.out.println("Ascenceur :");
		
		listeBoutonsAsc1 = asc1.getListeBoutons();
		for (BoutonInterne i : listeBoutonsAsc1){
			System.out.println(i.getLibelle());
		}
		
		System.out.println("Batiment :");
		
		listeBoutonsBatim = batim.getListeBoutons();
		for (BoutonExterne i : listeBoutonsBatim){
			System.out.println(i.toString());
		}
		
	}

}

