package projet2.entiees;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelBrain extends Brain {

	Joueur player;
	
	 public LevelBrain(Joueur AI) {
		 super(AI);
		 this.player = AI;
	 }
	 
	 //Quelqu'un a deja fait les calculs sur internet (https://web.stanford.edu/~guertin/risk.notes.html)
	 public double formula(Territoire a, Territoire b) {
		 double [][] odds = new double [6][7];
		 
		 //attaquant = 2 des
		 odds[0][0] = 0.838;
		 odds[0][1] = 0.444;
		 odds[0][2] = 0.152;
		 odds[0][3] = 0.036;
		 odds[0][4] = 0.006;
		 odds[0][5] = 0.0007;
		 odds[0][6] = 0.00007;
		 odds[0][7] = 0.000004;
		 
		 //a = 3
		 odds[1][0] = 0.973;
		 odds[1][1] = 0.779;
		 odds[1][2] = 0.453;
		 odds[1][3] = 0.192;
		 odds[1][4] = 0.061;
		 odds[1][5] = 0.015;
		 odds[1][6] = 0.003;
		 odds[1][7] = 0.0005;
		 
		 //a = 4
		 odds[2][0] = 0.997;
		 odds[2][1] = 0.939;
		 odds[2][2] = 0.743;
		 odds[2][3] = 0.46;
		 odds[2][4] = 0.220;
		 odds[2][5] = 0.083;
		 odds[2][6] = 0.025;
		 odds[2][7] = 0.006;
		 
		 //a = 5
		 odds[3][0] = 0.9998;
		 odds[3][1] = 0.988;
		 odds[3][2] = 0.909;
		 odds[3][3] = 0.718;
		 odds[3][4] = 0.464;
		 odds[3][5] = 0.242;
		 odds[3][6] = 0.104;
		 odds[3][7] = 0.037;
		 
		 //a = 6
		 odds[4][0] = 0.999997;
		 odds[4][1] = 0.998;
		 odds[4][2] = 0.975;
		 odds[4][3] = 0.884;
		 odds[4][4] = 0.700;
		 odds[4][5] = 0.467;
		 odds[4][6] = 0.260;
		 odds[4][7] = 0.121;
		 
		 //a = 7
		 odds[5][0] = 1;
		 odds[5][1] = 0.9998;
		 odds[5][2] = 0.995;
		 odds[5][3] = 0.962;
		 odds[5][4] = 0.862;
		 odds[5][5] = 0.685;
		 odds[5][6] = 0.469;
		 odds[5][7] = 0.274;
		 
		 //a = 8
		 odds[6][0] = 1;
		 odds[6][1] = 0.99998;
		 odds[6][2] = 0.999;
		 odds[6][3] = 0.990;
		 odds[6][4] = 0.948;
		 odds[6][5] = 0.844;
		 odds[6][6] = 0.673;
		 odds[6][7] = 0.471;
		 
		 return odds[a.getNbDes()-2][b.getNbDes()-1];
	 }
	 
	 //Retourne true apres une attaque, false quand il ne peut plus attaquer
	 public boolean action(Carte map, Jeu j, int x) {
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
					 //Si l'IA trouve un territoire contre lequel il a plus de x% de chance de gagner
					 for (Territoire n : neighbors2) {
						 if (n.getProprio().equals(this.player) && formula(n, t1)>= (x/100)) {
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
