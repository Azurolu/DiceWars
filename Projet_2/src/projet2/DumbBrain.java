package dicewar;

public class DumbBrain extends PlayerBrain {
	 public double rateTerritoire(Territoire  t) {
	        double score = super.rateTerritoire(t);
	        return (10000 - score);
	 }
}
