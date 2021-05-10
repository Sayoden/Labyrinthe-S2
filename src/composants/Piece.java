package composants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de représenter les différentes pièces du jeu.
 */

public abstract class Piece {
    private int modelePiece;        // Le modèle de la pièce
    private int orientationPiece;    // L'orientation de la pièce
    private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Constructeur permettant de crÃ©er une pièce d'un modèle avec l'orientation 0.
     *
     * @param modelePiece       Le modèle de la pièce.
     * @param pointEntreeHaut   Un booléen indiquant si la pièce a un point d'entrée en haut.
     * @param pointEntreeDroite Un booléen indiquant si la pièce a un point d'entrée à droite.
     * @param pointEntreeBas    Un booléen indiquant si la pièce a un point d'entrée en bas.
     * @param pointEntreeGauche Un booléen indiquant si la pièce a un point d'entrée à  gauche.
     */
    public Piece(int modelePiece, boolean pointEntreeHaut, boolean pointEntreeDroite, boolean pointEntreeBas, boolean pointEntreeGauche) {
        this.modelePiece = modelePiece;
        this.orientationPiece = 0;
        this.pointsEntree = new boolean[4];
        this.pointsEntree[0] = pointEntreeHaut;
        this.pointsEntree[1] = pointEntreeDroite;
        this.pointsEntree[2] = pointEntreeBas;
        this.pointsEntree[3] = pointEntreeGauche;
    }

    /**
     * Méthode retournant un String représentant la pièce.
     */
    @Override
    public String toString() {
        return "[m:" + modelePiece + "|o:" + orientationPiece + "|pe:" + pointsEntree[0] + " " + pointsEntree[1] + " " + pointsEntree[2] + " " + pointsEntree[3] + "]";
    }

    /**
     * A Faire (23/04/21 CD Finalisée)
     * <p>
     * Méthode permettant de rotationner une pièce dans le sens d'une horloge.
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
     * Méthode permettant d'orienter une pièce vers une orientation spécifique.
     *
     * @param orientationPiece Un entier correspondant à la nouvelle orientation de la pièce.
     */
    public void setOrientation(int orientationPiece) {
        for (int i = 0; i < orientationPiece; i++) {
            rotation();
        }
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Méthode retournant le modèle de la pièce.
     *
     * @return Un entier corrspondant au modèle de la pièce.
     */
    public int getModelePiece() {
        return this.modelePiece;
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Méthode retournant l'orientation de la pièce.
     *
     * @return un entier retournant l'orientation de la pièce.
     */
    public int getOrientationPiece() {
        return this.orientationPiece;
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Méthode indiquant si il existe un point d'entrée à  une certaine position (0: en haut, 1:à droite, 2: en bas, 3:à gauche).
     *
     * @param pointEntree L'indice/la position du point d'entrée.
     * @return true si il y a un point d'entrée, sinon false.
     */
    public boolean getPointEntree(int pointEntree) {
        return this.pointsEntree[pointEntree];
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Méthode permettant de créer un tableau contenant toutes les piéces du jeu (les 50 pièces).
     * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
     * L'orientation de chaque pièce sera aléatoire.
     *
     * @return Un tableau contenant toutes les pièces du jeu.
     */
    public static Piece[] nouvellesPieces() {
        Piece[] pieces = new Piece[50];

        for (int i = 0; i < 20; i++) {
            PieceM0 pieceTemp = new PieceM0();
            pieceTemp.setOrientation(Utils.genererEntier(3));
            pieces[i] = pieceTemp;
        }
        for (int i = 20; i < 32; i++) {
            PieceM1 pieceTemp = new PieceM1();
            pieceTemp.setOrientation(Utils.genererEntier(3));
            pieces[i] = pieceTemp;
        }
        for (int i = 32; i < 50; i++) {
            PieceM2 pieceTemp = new PieceM2();
            pieceTemp.setOrientation(Utils.genererEntier(3));
            pieces[i] = pieceTemp;
        }

        return pieces;
    }

    /**
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     *
     * @return Une copie de la pièce.
     */
    public abstract Piece copy() throws CloneNotSupportedException;
}
