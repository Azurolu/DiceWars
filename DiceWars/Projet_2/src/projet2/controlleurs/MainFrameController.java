package projet2.controlleurs;

import projet2.entiees.Carte;
import projet2.entiees.Jeu;
import projet2.entiees.Joueur;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class MainFrameController {

    private Jeu jeu;
    private HashMap<Joueur, Color> mapJoueurCouleur;

    /**
     * Constructeur de la classe.
     *
     * @param jeu Le jeu en cours.
     */
    public MainFrameController(Jeu jeu) {
        this.jeu = jeu;

        this.loadPlayerColors();
    }

    /**
     * Permet d'obtenir l'association entre les joueurs et leurs couleurs.
     *
     * @return La HashMap.
     */
    public HashMap<Joueur, Color> getMapJoueurCouleur() {
        return mapJoueurCouleur;
    }

    /**
     * Chargement de manière aléatoire des couleurs par joueurs.
     */
    private void loadPlayerColors() {
        this.mapJoueurCouleur = new HashMap<>();
        Random r = new Random();
        for (int i = 0; i < this.jeu.getJoueurs().length; i++) {
            Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
            this.mapJoueurCouleur.put(this.jeu.getJoueurs()[i], color);
        }
    }

    /**
     * Permet d'obtenir la carte du jeu.
     *
     * @return La carte.
     */
    public Carte getCarteDuJeu() {
        return this.jeu.getCarte();
    }

    /**
     * Permet d'obtenir les joueurs.
     *
     * @return Le tableau des joueurs.
     */
    public Joueur[] getJoueurs() {
        return this.jeu.getJoueurs();
    }

    /**
     * Action déclenché en cas d'appuis sur le bouton "fin du tour"
     */
    public void finDuTour() {
        this.jeu.finTour();
    }

    /**
     * Action déclenché en cas d'appuis sur un bouton de territoire.
     *
     * @param proprio Le propriétaire du territoire.
     */
    public void action(Joueur proprio) {
        // TODO action en cas de clique
    }
}
