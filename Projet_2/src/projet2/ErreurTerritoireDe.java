package projet2;

public class ErreurTerritoireDe extends Exception{
	
	public ErreurTerritoireDe() {
		super("Le territoire souhaitant attaquer ne possède qu'un seul dé");
	}

}
