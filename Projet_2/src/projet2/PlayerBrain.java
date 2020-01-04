package dicewar;

public class PlayerBrain implements Brain{

	 public Brain.Move bestMove(Path path, Territoire t) {
	        Brain.Move move = new Brain.Move();
	        map = new Carte(path);

	        double bestScore = 1e20;
	        int bestX = 0;
	        int bestY = 0;
	        Territoire bestTerritory = null;
	        Territoire current = new Territoire(t);

	        // analyze map
	        //TODO

	        // loop through all the possible territories
	        while (true) {
	        	// define variables
	        	//TODO
	            // for current territory, try all possible invasions
	            //TODO
	        	
	        	
	            current = current.computeNextRotation();
	            if (current.equals(t)) {
	                break; // break if back to original territory
	            }
	        }

	        if (bestTerritory == null) {
	            return null; // could not find a play at all
	        } else {
	        	// format move
	        	//TODO
	            return move;
	        }
	    }

	    /*
	     * a simple brain function. Given a map, produce a number that rates that
	     * territory's position -- larger numbers for worse territories.
	     */
	    public double rateTerritoire(Territoire t) {
	    	// separate in criteria and count separate scores for each criteria
	        //TODO

	        // add up the counts to make an overall score
	        return ;
	    }
}
