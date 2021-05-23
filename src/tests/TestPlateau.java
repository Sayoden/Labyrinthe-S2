package tests;

import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;

import java.util.Arrays;

public class TestPlateau {

    public static void main(String[] args) {

        //Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres();

        int nbJoueurs = (Integer) parametres[0];
        IG.creerFenetreJeu("Etape 1 - Projet S2", nbJoueurs);
        IG.rendreVisibleFenetreJeu();

        IG.jouerUnSon(2);
        IG.pause(300);
        IG.jouerUnSon(2);

        String[] message = {
                "",
                "Cliquez pour continuer ...",
                ""
        };

        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau = plateau.placerPiecesAleatoierment();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.miseAJourAffichage();
        int[][] resultat = plateau.calculeChemin(3, 3, 3, 6);
        if (resultat == null) {
            System.out.println("Aucun chemin possible.");
        } else {
            System.out.println("La liste des chemins trouvés à partir de la case (3,3) : \n");
            for (int[] ints : resultat) {
                System.out.println("Chemins entre les cases (3,3) et (" + ints[0] + "," + ints[1] + ") : " + Arrays.deepToString(resultat));
                IG.placerBilleSurPlateau(ints[0], ints[1], 1, 1, 1);
            }
        }

        IG.miseAJourAffichage();
    }
}
