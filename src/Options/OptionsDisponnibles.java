package Options;

import java.util.ArrayList;

public abstract class OptionsDisponnibles {
	private final static ArrayList<Option> OPTIONSDISPONNIBLES;
	
	static {
		ArrayList<Option> tmp = new ArrayList<Option> ();
		
		tmp.add(new OptionMusique());
		tmp.add(new OptionVitesseAugmentee());
		
		OPTIONSDISPONNIBLES = tmp;
	}

	public static ArrayList<Option> getOptionsDisponnibles() {
		return OPTIONSDISPONNIBLES;
	}
}
