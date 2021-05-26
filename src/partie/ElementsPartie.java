package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import joueurs.Joueur;

public class ElementsPartie {
    private Joueur[] joueurs; 	// Les joueurs de la partie.
    private Objet[] objets; 	// Les 18 objets de la partie dans l'ordre de leurs numÃ©ros.
    private Plateau plateau; 	// Le plateau des piÃ¨ces.
    private Piece pieceLibre; 	// La piÃ¨ce libre.
    private int nombreJoueurs; 	// Le nombre de joueurs.

    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de gÃ©nÃ©rer et d'initialiser l'ensemble des Ã©lÃ©ments d'une partie (sauf les joueurs qui sont donnÃ©s en paramÃ¨tres).
     *
     * Un plateau est crÃ©Ã© en placant 49 oiÃ¨ces de maniÃ¨re alÃ©atoire (utilisation de la mÃ©thode placerPiecesAleatoierment de la classe Plateau).
     * La piÃ¨ce restante (celle non prÃ©sente sur le plateau) est affectÃ©e Ã  la piÃ¨ce libre.
     * Les 18 objets sont crÃ©Ã©s avec des positions alÃ©atoires sur le plateau (utilisation de la mÃ©thode Objet.nouveauxObjets)
     * et distribuÃ©es aux diffÃ©rents joueurs (utilisation de la mÃ©thode attribuerObjetsAuxJoueurs).
     *
     * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribuÃ©s (c'est au constructeur de le faire).
     */
    public ElementsPartie(Joueur[] joueurs) {

        // A ComplÃ©ter

    }

    /**
     * Un simple constructeur.
     *
     * @param joueurs Les joueurs de la partie.
     * @param objets Les 18 objets de la partie.
     * @param plateau Le plateau de jeu.
     * @param pieceLibre La piÃ¨ce libre (la piÃ¨ce hors plateau).
     */
    public ElementsPartie(Joueur[] joueurs,Objet[] objets,Plateau plateau,Piece pieceLibre) {
        this.joueurs=joueurs;
        nombreJoueurs=joueurs.length;
        this.objets=objets;
        this.plateau=plateau;
        this.pieceLibre=pieceLibre;
    }

    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant d'attribuer les objets aux diffÃ©rents joueurs de maniÃ¨re alÃ©atoire.
     */
    private void attribuerObjetsAuxJoueurs(){

        // A ComplÃ©ter

    }

    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de rÃ©cupÃ©rer les joueurs de la partie.
     * @return Les joueurs de la partie.
     */
    public Joueur[] getJoueurs() {
        return null; // A Modifier
    }


    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de rÃ©cupÃ©rer les piÃ¨ces de la partie.
     * @return Les objets de la partie.
     */
    public Objet[] getObjets() {
        return null; // A Modifier
    }


    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de rÃ©cupÃ©rer le plateau de piÃ¨ces de la partie.
     * @return Le plateau de piÃ¨ces.
     */
    public Plateau getPlateau() {
        return null; // A Modifier
    }


    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de rÃ©cupÃ©rer la piÃ¨ce libre de la partie.
     * @return La piÃ¨ce libre.
     */
    public Piece getPieceLibre() {
        return null; // A Modifier
    }


    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de rÃ©cupÃ©rer le nombre de joueurs de la partie.
     * @return Le nombre de joueurs.
     */
    public int getNombreJoueurs() {
        return -1; // A Modifier
    }


    /**
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode modifiant les diffÃ©rents Ã©lÃ©ments de la partie suite Ã  l'insertion de la piÃ¨ce libre dans le plateau.
     *
     * @param choixEntree L'entrÃ©e choisie pour rÃ©aliser l'insertion (un nombre entre 0 et 27).
     */
    public void insertionPieceLibre(int choixEntree){
        // A ComplÃ©ter
    }


    /**
     * MÃ©thode retournant une copie.
     *
     * @return Une copie des Ã©lÃ©ments.
     */
    public ElementsPartie copy() throws CloneNotSupportedException {
        Objet[] nouveauxObjets=new Objet[(this.objets).length];
        for (int i=0;i<objets.length;i++)
            nouveauxObjets[i]=(this.objets[i]).copy();
        Joueur[] nouveauxJoueurs=new Joueur[nombreJoueurs];
        for (int i=0;i<nombreJoueurs;i++)
            nouveauxJoueurs[i]=(this.joueurs[i]).copy(objets);
        Plateau nouveauPlateau=(this.plateau).copy();
        Piece nouvellePieceLibre=(this.pieceLibre).copy();
        ElementsPartie nouveauxElements=new  ElementsPartie(nouveauxJoueurs,nouveauxObjets,nouveauPlateau,nouvellePieceLibre);
        return nouveauxElements;
    }


}
