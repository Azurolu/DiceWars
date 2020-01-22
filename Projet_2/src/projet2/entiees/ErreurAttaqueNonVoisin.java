package projet2.entiees;

public class ErreurAttaqueNonVoisin extends Exception{
	
	public ErreurAttaqueNonVoisin() {
		super("L'attaquant veut attaquer un territoire non voisin");
	}

}
