package projet2.entiees;

import projet2.entiees.Carte;
import projet2.entiees.Joueur;
import projet2.entiees.Territoire;

import java.util.Random;

public class Jeu {

    private int nbDesDepart;
    private Carte carte;
    private Joueur[] joueurs;

    public Jeu(int limiteDes, Joueur[] joueur, Carte cart) {
        Random rnd = new Random();
        this.carte = cart;
        this.joueurs = joueur;
        this.nbDesDepart = (rnd.nextInt(limiteDes * 8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Evite le 0 dés et permet au moins un dé sur chaque territoire
        while (nbDesDepart % (joueurs.length) != 0) {
            this.nbDesDepart = (rnd.nextInt(limiteDes * 8 - carte.getNbTerritoires() + 1) + carte.getNbTerritoires()); //Il faut le même nombre de dés par joueur
        }
        System.out.println(nbDesDepart);
        for (Joueur j : joueurs) {
            j.setNbDes(nbDesDepart / joueurs.length);
        }
        int i = 0;
        while (!carte.attributionJoueurs()) {
            Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
            while (t == null || t.getProprio() != null) {
                t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
            }
            t.setProprio(joueurs[i % joueurs.length]);
            i++;
        }
        for (Territoire[] tcrochets : carte.getCarte()) {
            for (Territoire t : tcrochets) {
                if (t != null) {
                    t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire() + 1);
                }
            }
        }
        while (!carte.attributionDes()) {
            Territoire t = carte.getCarte()[rnd.nextInt(carte.getCarte().length)][rnd.nextInt(carte.getCarte()[0].length)];
            if (t != null && t.getNbDes() == 0) {
                int Des = rnd.nextInt(8) + 1;
                if (t.getProprio().getNbTerritoire() == 1) {
                    Des = t.getProprio().getNbDes();
                }
                if (t.getProprio().getNbDes() >= ((t.getProprio().getNbTerritoire() - 1) * 8)) {
                    Des = 8;
                }
                if (Des > t.getProprio().getNbDes()) {
                    Des = t.getProprio().getNbDes();
                }
                while (Des > t.getProprio().getNbDes() - (t.getProprio().getNbTerritoire() - 1)) {
                    Des--;
                }
                t.getProprio().setNbDes(t.getProprio().getNbDes() - Des);
                t.setNbDes(Des);
                t.getProprio().setNbTerritoire(t.getProprio().getNbTerritoire() - 1);
            }
        }
    }

    public void miseAJour(Territoire gagnant, Territoire perdant, Territoire attaque) {
        if (gagnant == attaque) { //Si le territoire attaqué gagne, rien ne change pour lui
            perdant.setNbDes(1);
        } else if (perdant == attaque) { //Sinon tout change
            gagnant.setNbDes(1);
            attaque.setNbDes(gagnant.getNbDes() - 1);
            attaque.setProprio(gagnant.getProprio());
        }

        if (carte.victoire())
            System.out.println("Victoire du joueur : " + carte.getCarte()[0][0].getProprio());
    }

    public void finTour() {

    }

    private int aleatoire() {
        return new Random().nextInt(6) + 1;
    }

    public Carte getCarte() {
        return carte;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }
}