package Client;
import java.util.ArrayList;

import Boutons.BoutonExterne;
import Boutons.BoutonInterne;
import IHM.FenetreConfiguration;
import IHM.FenetreRequetes;
import Options.OptionMusique;

public class Main {

	public static void main(String[] args) {
		//Batiment batim = new Batiment("Hotel", 5, 2);
		
		FenetreConfiguration fenetre = new FenetreConfiguration();
//		System.out.println(batim.getAscenseur(1).toString());
//		
//		batim.appuyerBoutonAscenseur(1, 3);
//		batim.appuyerBoutonAscenseur(2, 5);
//		batim.getAscenseur(1).ajouterOption(new OptionMusique("Guns'n Roses FTW"));
//		batim.getAscenseur(1).activerOption(0);
//		batim.traiterControleurs();
//		System.out.println(batim.getAscenseur(1).toString());
//		System.out.println(batim.getAscenseur(2).toString());
//		
//		batim.appuyerBoutonEtage(0); //etage 0 haut
//		batim.appuyerBoutonEtage(2); //etage 1 bas
//		batim.appuyerBoutonEtage(8); //etage 4 haut
//		batim.appuyerBoutonEtage(0); //etage 0 haut
//		
//		batim.getControleurExt().traiterRequetes();
//		System.out.println(batim.getControleursInterne().toString());		
//		batim.traiterControleurs();
//		System.out.println(batim.getControleursInterne().toString());		
//		
//		System.out.println(batim.getAscenseur(1).toString());
//		System.out.println(batim.getAscenseur(2).toString());
//		System.out.println(batim.toString());
//		ArrayList<BoutonInterne> listeBoutonsAsc1 = new ArrayList<BoutonInterne>();
//		ArrayList<BoutonExterne> listeBoutonsBatim = new ArrayList<BoutonExterne>();
//		
//		System.out.println("Ascenceur :");
//		
//		listeBoutonsAsc1 = batim.getAscenseur(1).getListeBoutons();
//		for (BoutonInterne i : listeBoutonsAsc1){
//			System.out.println(i.getLibelle());
//		}
//		
//		System.out.println("Batiment :");
//		
//		listeBoutonsBatim = batim.getListeBoutons();
//		for (BoutonExterne i : listeBoutonsBatim){
//			System.out.println(i.toString());
//		}
//		for (int i = 0;0 < batim.getControleursInterne().get(0).getNumberOfRequete(); ++i) {
//			try {
//				Thread.sleep(1000); //temps d'iteration
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("-------------------Iteration-------------------");
//			System.out.println(batim.traiterControleurs());
//			System.out.println("----------------fin d'Iteration----------------\n");
//		}
		FenetreRequetes vueRequetes = new FenetreRequetes();
		
	}

}

