package IHM;

public class DenominationEtages {

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
}
