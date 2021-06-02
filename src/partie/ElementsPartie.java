package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette classe permet de reprÃ©senter un ensemble d'Ã©lements composant une partie de jeu.
 */
public class ElementsPartie {

    private Joueur[] joueurs;    // Les joueurs de la partie.
    private Objet[] objets;    // Les 18 objets de la partie dans l'ordre de leurs numÃ©ros.
    private Plateau plateau;    // Le plateau des piÃ¨ces.
    private Piece pieceLibre;    // La piÃ¨ce libre.
    private int nombreJoueurs;    // Le nombre de joueurs.

    /**
     * A Faire (01/06/2021 LG,AG Finalisée)
     * <p>
     * Constructeur permettant de gÃ©nÃ©rer et d'initialiser l'ensemble des Ã©lÃ©ments d'une partie (sauf les joueurs qui sont donnÃ©s en paramÃ¨tres).
     * <p>
     * Un plateau est crÃ©Ã© en placant 49 oiÃ¨ces de maniÃ¨re alÃ©atoire (utilisation de la mÃ©thode placerPiecesAleatoierment de la classe Plateau).
     * La piÃ¨ce restante (celle non prÃ©sente sur le plateau) est affectÃ©e Ã  la piÃ¨ce libre.
     * Les 18 objets sont crÃ©Ã©s avec des positions alÃ©atoires sur le plateau (utilisation de la mÃ©thode Objet.nouveauxObjets)
     * et distribuÃ©es aux diffÃ©rents joueurs (utilisation de la mÃ©thode attribuerObjetsAuxJoueurs).
     *
     * @param joueurs Les joueurs de la partie. Les objets des joueurs ne sont pas encore attribuÃ©s (c'est au constructeur de le faire).
     */
    public ElementsPartie(Joueur[] joueurs) {
        Plateau plateau = new Plateau();
        this.joueurs = joueurs;
        this.nombreJoueurs = joueurs.length;
        this.objets = Objet.nouveauxObjets();
        this.plateau = plateau;
        this.pieceLibre = plateau.placerPiecesAleatoierment();
        attribuerObjetsAuxJoueurs();
    }

    /**
     * Un simple constructeur.
     *
     * @param joueurs    Les joueurs de la partie.
     * @param objets     Les 18 objets de la partie.
     * @param plateau    Le plateau de jeu.
     * @param pieceLibre La piÃ¨ce libre (la piÃ¨ce hors plateau).
     */
    public ElementsPartie(Joueur[] joueurs, Objet[] objets, Plateau plateau, Piece pieceLibre) {
        this.joueurs = joueurs;
        nombreJoueurs = joueurs.length;
        this.objets = objets;
        this.plateau = plateau;
        this.pieceLibre = pieceLibre;
    }

    /**
     * A Faire (01/06/2021 LG,AG Finalisée)
     * <p>
     * MÃ©thode permettant d'attribuer les objets aux diffÃ©rents joueurs de maniÃ¨re alÃ©atoire.
     */
    private void attribuerObjetsAuxJoueurs() {
        int x = 0;
        for (Joueur joueur : this.joueurs) {
            int i = 0;
            Objet[] objets = new Objet[(this.objets.length / this.nombreJoueurs)];
            while (i != (this.objets.length / this.nombreJoueurs)) {
                objets[i] = this.objets[i + x];
                i++;
            }
            x += (this.objets.length / this.nombreJoueurs) - 1;
            joueur.setObjetsJoueur(objets);
        }
    }

    /**
     * A Faire (01/06/2021 AG Finalisée)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer les joueurs de la partie.
     *
     * @return Les joueurs de la partie.
     */
    public Joueur[] getJoueurs() {
        return this.joueurs;
    }


    /**
     * A Faire (01/06/2021 AG Finalisée)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer les piÃ¨ces de la partie.
     *
     * @return Les objets de la partie.
     */
    public Objet[] getObjets() {
        return this.objets;
    }


    /**
     * A Faire (01/06/2021 AG Finalisée)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer le plateau de piÃ¨ces de la partie.
     *
     * @return Le plateau de piÃ¨ces.
     */
    public Plateau getPlateau() {
        return this.plateau;
    }


    /**
     * A Faire (01/06/2021 AG Finalisée)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer la piÃ¨ce libre de la partie.
     *
     * @return La piÃ¨ce libre.
     */
    public Piece getPieceLibre() {
        return this.pieceLibre;
    }


    /**
     * A Faire (01/06/2021 AG Finalisée)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer le nombre de joueurs de la partie.
     *
     * @return Le nombre de joueurs.
     */
    public int getNombreJoueurs() {
        return this.nombreJoueurs;
    }


    /**
     * A Faire (01/06/2021 LG,CD,AG Finalisée)
     * <p>
     * MÃ©thode modifiant les diffÃ©rents Ã©lÃ©ments de la partie suite Ã  l'insertion de la piÃ¨ce libre dans le plateau.
     *
     * @param choixEntree L'entrÃ©e choisie pour rÃ©aliser l'insertion (un nombre entre 0 et 27).
     */
    public void insertionPieceLibre(int choixEntree) {
        boolean hautBas;
        int ligne;
        int colonne;
        int[] tableau = new int[]{6,4,2,0,-2,-4,-6};
        if (choixEntree >= 0 && choixEntree <= 6) {
            //Haut vers le bas
            ligne = 0;
            colonne = choixEntree;
            hautBas = true;
        } else {
            int ligne1 = (choixEntree % 7) + tableau[choixEntree % 7];
            if (choixEntree >= 14 && choixEntree <= 20) {
                //Bas vers le haut
                ligne = 6;
                colonne = ligne1;
                hautBas = true;
            } else if (choixEntree >= 7 && choixEntree <= 13) {
                //Droite vers la gauche
                colonne = 6;
                ligne = choixEntree % 7;
                hautBas = false;
            } else {
                //Gauche vers la droite
                colonne = 0;
                ligne = ligne1;
                hautBas = false;
            }
        }

        Piece oldPiece = this.pieceLibre;
        for (int i = 0; i <= 6; i++) {
            if (hautBas) {
                int newLigne = (ligne == 0 ? ligne + i : ligne - i);
                IG.changerPiecePlateau(newLigne, colonne, oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
                oldPiece = this.plateau.getPiece(newLigne, colonne);
            } else {
                int newColonne = (colonne == 0 ? colonne + i : colonne - i);
                IG.changerPiecePlateau(ligne, newColonne, oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
                oldPiece = this.plateau.getPiece(ligne, newColonne);
            }
            IG.changerPieceHorsPlateau(oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
            this.pieceLibre = oldPiece;
        }
        IG.miseAJourAffichage();
    }

    /**
     * MÃ©thode retournant une copie.
     *
     * @return Une copie des Ã©lÃ©ments.
     */
    public ElementsPartie copy() throws CloneNotSupportedException {
        Objet[] nouveauxObjets = new Objet[(this.objets).length];
        for (int i = 0; i < objets.length; i++)
            nouveauxObjets[i] = (this.objets[i]).copy();
        Joueur[] nouveauxJoueurs = new Joueur[nombreJoueurs];
        for (int i = 0; i < nombreJoueurs; i++)
            nouveauxJoueurs[i] = (this.joueurs[i]).copy(objets);
        Plateau nouveauPlateau = (this.plateau).copy();
        Piece nouvellePieceLibre = (this.pieceLibre).copy();
        ElementsPartie nouveauxElements = new ElementsPartie(nouveauxJoueurs, nouveauxObjets, nouveauPlateau, nouvellePieceLibre);
        return nouveauxElements;
    }

    @Override
    public String toString() {
        return "ElementsPartie{" +
                "joueurs=" + Arrays.toString(joueurs) +
                ", objets=" + Arrays.toString(objets) +
                ", plateau=" + plateau +
                ", pieceLibre=" + pieceLibre +
                ", nombreJoueurs=" + nombreJoueurs +
                '}';
    }
}
