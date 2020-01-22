package projet2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerBrain extends Brain {

	Joueur player;
	
	 public PlayerBrain(Joueur AI) {
		 super(AI);
		 this.player = AI;
	 }
	 
	 //Retourne true apres une attaque, false quand il ne peut plus attaquer
	 public boolean action(Carte map, Jeu j) {
		 //On melange les territoires
		 Territoire [][] territories0 = map.getCarte();
		 List<Territoire[]> territories1 = Arrays.asList(territories0);
		 Collections.shuffle(territories1);
		 Territoire [][] territories2 = territories0; 
		 for (int i =0; i < territories1.size(); i++) 
	            territories2[i] = territories1.get(i); 
		 
		 //On obtient les voisins
		 for (Territoire[] t0 : territories2) {
			 for (Territoire t1 : t0) {
				 if (t1.getProprio().equals(this.player) && t1.getNbDes()>1) {
					 Territoire[] neighbors0 = map.getVoisins(t1);
					 //On melange les voisins
					 List<Territoire> neighbors1 = Arrays.asList(neighbors0);
					 Collections.shuffle(neighbors1);
					 Territoire [] neighbors2 = neighbors0;
					 for (int i =0; i < neighbors1.size(); i++) 
				            neighbors2[i] = neighbors1.get(i); 
					 //Si l'IA trouve un territoire avec moins de des que son territoire, elle attaque
					 for (Territoire n : neighbors2) {
						 if (n.getProprio().equals(this.player) && n.getNbDes() <= t1.getNbDes()) {
							 j.attaquer(t1.getId(), n.getId());
							 return true;
						 }
					 }
				 }
			 }
		 }
		 return false;
	 }
}
