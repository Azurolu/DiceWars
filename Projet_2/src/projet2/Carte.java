package projet2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Carte {
	
	private Territoire[][] carte;
	private int base;
	
	public Carte(Path path){
		ArrayList<String> lignes = new ArrayList<String>();
		try {
			Scanner lecture = new Scanner(path);
			while(lecture.hasNextLine()) {
				lignes.add(lecture.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.carte = new Territoire[lignes.size()][lignes.get(0).length()];
		this.base = lignes.get(0).length();
		int i = 0;
		for(String s : lignes) {
			int j = 0;
			for(String sbis : s.split(";")) {
				if(sbis != null) {
					carte[i][j] = new Territoire(i*base + j);
				}
				j++;
			}
			i++;
		}
		lignes.clear();
	}
	
	public Territoire[][] getCarte(){
		return this.carte;
	}
	
	public int getBase() {
		return this.base;
	}
	
	public Territoire getTerritoire(int id) {
		return this.carte[id/base][id%base];
	}
	
	public Territoire[] getVoisins(Territoire t) {
		ArrayList<Territoire> voisins = new ArrayList<Territoire>();
		if(this.getTerritoire(t.getId()-this.base) != null)
			voisins.add(this.getTerritoire(t.getId()-this.base));
		if(this.getTerritoire(t.getId()-1) != null)
			voisins.add(this.getTerritoire(t.getId()-1));
		if(this.getTerritoire(t.getId()+1) != null)
			voisins.add(this.getTerritoire(t.getId()+1));
		if(this.getTerritoire(t.getId()+this.base) != null)
			voisins.add(this.getTerritoire(t.getId()+this.base));
		Territoire[] retour = (Territoire[])voisins.toArray();
		voisins.clear();
		return retour;
	}
	
	public boolean victoire() {
		Joueur joueur = this.carte[0][0].getProprio();
		for(Territoire[] tcrochets : this.carte) {
			for(Territoire t : tcrochets) {
				if(joueur != t.getProprio())
					return false;
			}
		}
		return true;
	}

}
