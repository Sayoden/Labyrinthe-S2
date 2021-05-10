package composants;

/**
 * Cette classe permet de représenter les pièces du jeu de modèle 1.
 */
public class PieceM1 extends Piece {

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * Constructeur permettant de construire une pièce de modéle 1 et d'orientation 0.
     */
    public PieceM1() {
        super(1, true, false, true, false);
    }

    /**
     * A Faire (03/05/21 LG Finalisée)
     * <p>
     * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
     *
     * @return Une copie de la pièce.
     */
    public Piece copy() throws CloneNotSupportedException {
        Piece piece = (Piece) this.clone();
        return piece;
    }
}