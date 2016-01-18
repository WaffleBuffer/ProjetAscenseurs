package Client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BatimentTest {

	private Batiment batiment;
	
	@BeforeClass
	public static void setUpClasse() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		this.batiment = new Batiment("Hotel", 5, 2);
		System.out.println("==============================================");
		System.out.print("| Debut du test : ");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRequetesInterne() {
		System.out.println("Test des Requetes Interne |");
		System.out.println("==============================================");
		System.out.println("");
		
		this.batiment.appuyerBoutonAscenseur(this.batiment.getAscenseur(0), 3);
		this.batiment.appuyerBoutonAscenseur(this.batiment.getAscenseur(1), 5);
		for (int i = 1; i <= 9; ++i) {
			System.out.println("Iteration " + i);
			System.out.println(this.batiment.traiterControleurs());
		}
		assertTrue(this.batiment.getAscenseur(0).getEtage() == 3);
		assertTrue(this.batiment.getAscenseur(1).getEtage() == 5);
		
		System.out.println("");
	}
	
	@Test
	public void testRequeteExterne() {
		System.out.println("Test des Requetes Externes |");
		System.out.println("==============================================");
		System.out.println("");
		
		this.batiment.appuyerBoutonEtage(0, Constantes.HAUT); //etage 0 haut
		this.batiment.appuyerBoutonEtage(0, Constantes.BAS); /*etage 1 bas : on mes 0 car c'est le premier 
		bouton bas de la liste des boutons bas*/
		this.batiment.appuyerBoutonEtage(4, Constantes.HAUT); //etage 4 haut
		this.batiment.appuyerBoutonEtage(0, Constantes.HAUT); //etage 0 haut
		
		this.batiment.getControleurExt().traiterRequetes();
		System.out.println(this.batiment.getControleursInternes().toString());
		
		for (int i = 1; i <= 10; ++i) {
			System.out.println("Iteration " + i);
			System.out.println(this.batiment.traiterControleurs());
		}
		
		assertTrue(this.batiment.getAscenseur(0).getEtage() == 4);
		assertTrue(this.batiment.getAscenseur(1).getEtage() == 1);
	}

}
