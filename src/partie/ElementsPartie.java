package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Permet d'obtenir des infos sur les coordonnées
     * avec un simple choixEntree
     *
     * @param choixEntree
     * @return
     */
    public List<Object> obtenirPosAvecChoixEntree(int choixEntree) {
        List<Object> infos = new ArrayList<>();
        int[] tableau = new int[]{6, 4, 2, 0, -2, -4, -6};
        int ligne;
        int colonne;
        boolean hautBas;
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
        infos.add(ligne);
        infos.add(colonne);
        infos.add(hautBas);

        return infos;
    }

    /**
     * A Faire (01/06/2021 LG,CD,AG Finalisée)
     * <p>
     * MÃ©thode modifiant les diffÃ©rents Ã©lÃ©ments de la partie suite Ã  l'insertion de la piÃ¨ce libre dans le plateau.
     *
     * @param choixEntree L'entrÃ©e choisie pour rÃ©aliser l'insertion (un nombre entre 0 et 27).
     */
    public void insertionPieceLibre(int choixEntree) {
        List<Object> infos = obtenirPosAvecChoixEntree(choixEntree);
        boolean hautBas = (boolean) infos.get(2);
        int ligne = (int) infos.get(0);
        int colonne = (int) infos.get(1);


        Piece oldPiece = this.pieceLibre;
        deplacerObjetsJoueurs(ligne, colonne, hautBas);
        for (int i = 0; i <= 6; i++) {
            IG.pause(100);
            IG.miseAJourAffichage();
            int newLigne = (ligne == 0 ? ligne + i : ligne - i);
            int newColonne = (colonne == 0 ? colonne + i : colonne - i);
            if (hautBas) {
                Piece oldPieceTmp = oldPiece;
                IG.changerPiecePlateau(newLigne, colonne, oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
                oldPiece = this.plateau.getPiece(newLigne, colonne);
                this.plateau.positionnePiece(oldPieceTmp, newLigne, colonne);
            } else {
                Piece oldPieceTmp = oldPiece;
                IG.changerPiecePlateau(ligne, newColonne, oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
                oldPiece = this.plateau.getPiece(ligne, newColonne);
                this.plateau.positionnePiece(oldPieceTmp, ligne, newColonne);
            }
        }
        IG.changerPieceHorsPlateau(oldPiece.getModelePiece(), oldPiece.getOrientationPiece());
        this.pieceLibre = oldPiece;
        IG.pause(400);
        IG.deselectionnerFleche();
        IG.miseAJourAffichage();
    }

    public void deplacerObjetsJoueurs(int ligne, int colonne, boolean hautBas) {
        int ligneTmp = ligne;
        int colonneTmp = colonne;

        List<Objet> listeObjets = new ArrayList<>();
        List<Joueur> listeJoueurs = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            int newLigne = (ligneTmp == 0 ? ligneTmp + i : ligneTmp - i);
            int newColonne = (colonneTmp == 0 ? colonneTmp + i : colonneTmp - i);

            Objet objet;
            Joueur joueur;
            if (hautBas) {
                objet = objetIci(newLigne, colonne);
                joueur = joueurIci(newLigne, colonne);
            } else {
                objet = objetIci(ligne, newColonne);
                joueur = joueurIci(ligne, newColonne);
            }

            if (objet != null) {
                listeObjets.add(objet);
            }

            if (joueur != null) {
                listeJoueurs.add(joueur);
            }
        }

        for (int i = 0; i < listeObjets.size(); i++) {
            Objet obj = listeObjets.get(i);
            IG.enleverObjetPlateau(obj.getPosLignePlateau(), obj.getPosColonnePlateau());
        }

        for (int i = 0; i < listeJoueurs.size(); i++) {
            Joueur joueur = listeJoueurs.get(i);
            int LigObj = joueur.getPosLigne();
            int ColObj = joueur.getPosColonne();
            int newLigne = (ligne == 0 ? LigObj + 1 : LigObj - 1);
            int newColonne = (colonne == 0 ? ColObj + 1 : ColObj - 1);

            if (hautBas) {
                IG.placerJoueurSurPlateau(joueur.getNumJoueur(), (newLigne == -1) ? 6 : newLigne % 7, colonne);
                joueur.setPosition((newLigne == -1) ? 6 : newLigne % 7, colonne);
            } else {
                IG.placerJoueurSurPlateau(joueur.getNumJoueur(), ligne,  (newColonne == -1) ? 6 : newColonne % 7);
                joueur.setPosition(ligne, (newColonne == -1) ? 6 : newColonne % 7);
            }

        }
        for (int i = 0; i < listeObjets.size(); i++) {
            Objet obj = listeObjets.get(i);
            int LigObj = obj.getPosLignePlateau();
            int ColObj = obj.getPosColonnePlateau();
            int newLigne = (ligne == 0 ? LigObj + 1 : LigObj - 1);
            int newColonne = (colonne == 0 ? ColObj + 1 : ColObj - 1);

            if (hautBas) {
                obj.positionneObjet((newLigne == -1 ? 6 : newLigne % 7), colonne);
            } else {
                obj.positionneObjet(ligne, (newColonne == -1 ? 6 : newColonne % 7));
            }
        }
    }

    /**
     * Méthode permettant de voir si un objet est présent à tel
     * endroit du plateau
     *
     * @param ligne
     * @param colonne
     * @return - l'objet en question
     */
    public Objet objetIci(int ligne, int colonne) {
        for (int i = 0; i < 18; i++) {
            if (this.objets[i].getPosLignePlateau() == ligne && this.objets[i].getPosColonnePlateau() == colonne) {
                if (this.objets[i].surPlateau()) {
                    return this.objets[i];
                }
            }
        }
        return null;
    }

    /**
     * Méthode permettant de voir si un joueur est présent
     * à tel endroit du plateau
     *
     * @param ligne
     * @param colonne
     * @return - le joueur en question
     */
    public Joueur joueurIci(int ligne, int colonne) {
        for (int i = 0; i < getJoueurs().length; i++) {
            if (getJoueurs()[i].getPosLigne() == ligne && getJoueurs()[i].getPosColonne() == colonne) {
                return getJoueurs()[i];
            }
        }

        return null;
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
