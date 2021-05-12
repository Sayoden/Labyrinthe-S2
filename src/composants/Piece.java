package composants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Cette classe permet de reprÃ©senter les diffÃ©rentes piÃ¨ces du jeu.
 */
abstract public class Piece {

    private int modelePiece;        // Le modÃ¨le de la piÃ¨ce
    private int orientationPiece;    // L'orientation de la piÃ¨ce
    private boolean[] pointsEntree; // Les points d'entrÃ©e indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.
    private static final int NB_ENTREE = 4;

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Constructeur permettant de crÃ©er une piÃ¨ce d'un modÃ¨le avec l'orientation 0.
     *
     * @param modelePiece       Le modÃ¨le de la piÃ¨ce.
     * @param pointEntreeHaut   Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e en haut.
     * @param pointEntreeDroite Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e Ã  droite.
     * @param pointEntreeBas    Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e en bas.
     * @param pointEntreeGauche Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e Ã  gauche.
     */
    public Piece(int modelePiece, boolean pointEntreeHaut, boolean pointEntreeDroite, boolean pointEntreeBas, boolean pointEntreeGauche) {
        this.modelePiece = modelePiece;
        this.orientationPiece = 0;
        this.pointsEntree = new boolean[NB_ENTREE];
        this.pointsEntree[0] = pointEntreeHaut;
        this.pointsEntree[1] = pointEntreeDroite;
        this.pointsEntree[2] = pointEntreeBas;
        this.pointsEntree[3] = pointEntreeGauche;
    }

    /**
     * MÃ©thoide retournant un String reprÃ©sentant la piÃ¨ce.
     */
    @Override
    public String toString() {
        return "[m:" + modelePiece + "|o:" + orientationPiece + "|pe:" + pointsEntree[0] + " " + pointsEntree[1] + " " + pointsEntree[2] + " " + pointsEntree[3] + "]";
    }

    /**
     * A Faire (23/04/21 CD Finalisée)
     * <p>
     * MÃ©thode permettant de rotationner une piÃ¨ce dans le sens d'une horloge.
     */
    public void rotation() {
        this.orientationPiece++;
        boolean[] pointsEntreeTemp = this.pointsEntree;
        boolean[] tempFalse = {false, false, false, false};

        for (int i = 0; i < pointsEntreeTemp.length; i++) {
            if (pointsEntreeTemp[i]) {
                tempFalse[(i + 1) % 4] = true;
            }
        }

        this.pointsEntree = tempFalse;

        if (this.orientationPiece == 4) {
            this.orientationPiece = 0;
        }

    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant d'orienter une piÃ¨ce vers une orientation spÃ©cifique.
     *
     * @param orientationPiece Un entier correspondant Ã  la nouvelle orientation de la piÃ¨ce.
     */
    public void setOrientation(int orientationPiece) {
        while (orientationPiece != this.orientationPiece) {
            rotation();
        }
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant le modÃ¨le de la piÃ¨ce.
     *
     * @return Un entier corrspondant au modÃ¨le de la piÃ¨ce.
     */
    public int getModelePiece() {
        return this.modelePiece;
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant l'orientation de la piÃ¨ce.
     *
     * @return un entier retournant l'orientation de la piÃ¨ce.
     */
    public int getOrientationPiece() {
        return this.orientationPiece;
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode indiquant si il existe un point d'entrÃ©e Ã  une certaine position (0: en haut, 1: Ã  droite, 2: en bas, 3: Ã  gauche).
     *
     * @param pointEntree L'indice/la position du point d'entrÃ©e.
     * @return true si il y a un point d'entrÃ©e, sinon false.
     */
    public boolean getPointEntree(int pointEntree) {
        return this.pointsEntree[pointEntree];
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de crÃ©er un tableau contenant toutes les piÃ¨ces du jeu (les 50 piÃ¨ces).
     * Le tableau contiendra 20 piÃ¨ces du modÃ¨le 0, 12 piÃ¨ces du modÃ¨le 1 et 18 piÃ¨ces du modÃ¨le 2.
     * L'orientation de chaque piÃ¨ce sera alÃ©atoire.
     *
     * @return Un tableau contenant toutes les piÃ¨ces du jeu.
     */
    public static Piece[] nouvellesPieces() {
        Piece[] pieces = new Piece[50];

        int[] random = Utils.genereTabIntAleatoirement(50);
        int compteur = 0;
        for (int i : random) {
            if (compteur < 20) {
                pieces[i] = new PieceM0();
            } else if (compteur < 32) {
                pieces[i] = new PieceM1();
            } else if (compteur < 50) {
                pieces[i] = new PieceM2();
            }
            pieces[i].setOrientation(Utils.genererEntier(4));
            compteur++;
        }

        return pieces;
    }

    /**
     * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
     *
     * @return Une copie de la piÃ¨ce.
     */
    public abstract Piece copy() throws CloneNotSupportedException;
}