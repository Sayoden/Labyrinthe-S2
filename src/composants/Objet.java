package composants;

import grafix.interfaceGraphique.IG;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe permet de reprÃ©senter chacun des objets du jeu.
 */
public class Objet {

    private int numObjet; // Le numÃ©ro de l'objet (un entier entre 0 et 17).
    private int posLignePlateau; // La ligne du plateau sur laquelle est Ã©ventuellement posÃ© l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private int posColonnePlateau; // La colonne du plateau sur laquelle est Ã©ventuellement posÃ© l'objet (un entier entre -1 et 6, -1:lorsqu'il n'est pas sur le plateau).
    private boolean surPlateau; // Indique si l'objet est sur le plateau ou non (true : sur le plateau, false : hors du plateau).

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * Constructeur permettant de construire un objet qui est initialement hors du plateau.
     *
     * @param numObjet Le numÃ©ro de l'objet.
     */
    public Objet(int numObjet) {
        assert numObjet >= 0 && numObjet <= 17;

        this.numObjet = numObjet;
        this.surPlateau = false;
        this.posLignePlateau = -1;
        this.posColonnePlateau = -1;
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de gÃ©nÃ©rer un tableau contenant les 18 objets du jeu.
     * Les objets seront postionnÃ©es alÃ©atoirement sur le plateau.  Deux objets ne pourront pas Ãªtre sur une mÃªme case (mÃªme ligne et mÃªme colonne).
     *
     * @return Un tableau de 18 objets initialisÃ©s pour une partie du jeu. Chaque objet a une position gÃ©nÃ©rÃ©e alÃ©atoirement. Les positions sont diffÃ©rentes pour deux objets distincts.
     */
    public static Objet[] nouveauxObjets() {
        Objet objets[] = new Objet[18];
        int randomTab[] = Utils.genereTabIntAleatoirement(49);

        for (int i = 0; i < objets.length; i++) {
            objets[i] = new Objet(i);
            objets[i].positionneObjet(randomTab[i] / 7, randomTab[i] % 7);
        }
        IG.miseAJourAffichage();

        List<Objet> objetList = Arrays.asList(objets);
        Collections.shuffle(objetList);
        return objetList.toArray(objets);
    }


    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant le numÃ©ro de l'objet.
     *
     * @return Le numÃ©ro de l'objet.
     */
    public int getNumeroObjet() {
        return this.numObjet;
    }


    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant le numÃ©ro de la ligne sur laquelle se trouve l'objet.
     *
     * @return Le numÃ©ro de la ligne sur laquelle se trouve l'objet.
     */
    public int getPosLignePlateau() {
        return this.posLignePlateau;
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant le numÃ©ro de la colonne sur laquelle se trouve l'objet.
     *
     * @return Le numÃ©ro de la colonne sur laquelle se trouve l'objet.
     */
    public int getPosColonnePlateau() {
        return this.posColonnePlateau;
    }


    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de positionner l'objet sur une ligne et une colonne donnÃ©es en paramÃ¨tre.
     *
     * @param lignePlateau   Un entier compris entre 0 et 6.
     * @param colonnePlateau Un entier compris entre 0 et 6.
     */
    public void positionneObjet(int lignePlateau, int colonnePlateau) {
        this.surPlateau = true;
        this.posColonnePlateau = colonnePlateau;
        this.posLignePlateau = lignePlateau;
        IG.placerObjetPlateau(this.numObjet, lignePlateau, colonnePlateau);
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant d'enlever l'objet du plateau.
     */
    public void enleveDuPlateau() {
        this.surPlateau = false;
        IG.enleverObjetPlateau(getPosLignePlateau(), getPosColonnePlateau());
        IG.miseAJourAffichage();
        this.posLignePlateau = -1;
        this.posColonnePlateau = -1;
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode indiquant si l'objet est sur le plateau au non.
     *
     * @return true si l'objet est sur le plateau, false sinon.
     */
    public boolean surPlateau() {
        return this.surPlateau;
    }

    /**
     * MÃ©thode permettant d'obtenir une reprÃ©sentation d'un objet sous forme de chaÃ®ne de caractÃ¨res.
     */
    @Override
    public String toString() {
        return "Objet [numObjet=" + numObjet + ", posLignePlateau=" + posLignePlateau + ", posColonnePlateau="
                + posColonnePlateau + ", surPlateau=" + surPlateau + "]";
    }

    /**
     * MÃ©thode permettant de copier l'objet.
     *
     * @return Une copie de l'objet.
     */
    public Objet copy() {
        Objet objet = new Objet(numObjet);
        objet.posLignePlateau = posLignePlateau;
        objet.posColonnePlateau = posColonnePlateau;
        objet.surPlateau = surPlateau;
        return objet;
    }

    /**
     * Programme testant quelques mÃ©thodes de la classe Objet.
     *
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        System.out.println("*** GÃ©nÃ©ration et affichage des 18 objets ... ***");
        Objet objetsJeu[] = nouveauxObjets();
        for (int i = 0; i < objetsJeu.length; i++)
            System.out.println(objetsJeu[i]);
        System.out.println("*** On enlÃ¨ve les 18 objets du plateau ... ***");
        for (int i = 0; i < objetsJeu.length; i++)
            objetsJeu[i].enleveDuPlateau();
        System.out.println("*** On affiche de nouveau les 18 objets ... ***");
        for (int i = 0; i < objetsJeu.length; i++)
            System.out.println(objetsJeu[i]);
    }

}