package projet2;

public class ErreurTerritoireDe extends Exception{
	
	public ErreurTerritoireDe() {
		super("Le territoire souhaitant attaqué ne possède qu'un seul dé");
	}

}
