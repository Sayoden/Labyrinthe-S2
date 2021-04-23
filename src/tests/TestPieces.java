package tests;

import composants.Piece;
import grafix.interfaceGraphique.IG;

public class TestPieces {

    public static void main(String[] args) {

        String[] message = {
                "",
                "Cliquez pour continuer ...",
                ""
        };

        //Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres();

        int nbJoueurs = (Integer) parametres[0];
        IG.creerFenetreJeu("Etape 2 - Projet S2", nbJoueurs);
        IG.rendreVisibleFenetreJeu();

        IG.jouerUnSon(2);
        IG.pause(300);
        IG.jouerUnSon(2);

        Piece[] pieces = Piece.nouvellesPieces();

        int compteur = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, pieces[compteur].getModelePiece(), pieces[compteur].getOrientationPiece());
                compteur++;
            }
        }
        IG.changerPieceHorsPlateau(pieces[compteur].getModelePiece(), pieces[compteur].getOrientationPiece());
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.afficherMessage(message);

        for (int i = 1; i <= 4; i++) {
            compteur = 0;
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    pieces[compteur].rotation();
                    System.out.println(pieces[compteur].toString());
                    IG.changerPiecePlateau(j,k, pieces[compteur].getModelePiece(), pieces[compteur].getOrientationPiece());
                    compteur++;
                }
            }
            pieces[compteur].rotation();
            IG.changerPieceHorsPlateau(pieces[compteur].getModelePiece(), pieces[compteur].getOrientationPiece());
            IG.miseAJourAffichage();
            IG.attendreClic();
        }
    }
}
