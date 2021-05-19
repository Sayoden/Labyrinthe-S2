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


        int nbObjetsJoueur = 9;
        if (nbJoueurs == 3) {
            nbObjetsJoueur = 6;
        }
        /**
         * Setup player name, type with parameters
         */
        int counter = 1;
        int countObjet = 0;
        for (int i = 0; i < nbJoueurs; i++) {
            String nomJoueur = (String) parametres[counter];
            counter++;
            String categorieJoueur = (String) parametres[counter];
            counter++;
            int numImageJoueur = ((Integer) parametres[counter]).intValue();
            counter++;

            //Setup player objects
            for (int j = 0; j < nbObjetsJoueur; j++) {
                IG.changerObjetJoueur(i, countObjet, j);
                countObjet++;
            }

            IG.changerNomJoueur(i, nomJoueur + " (" + categorieJoueur + ")");
            IG.changerImageJoueur(i, numImageJoueur);
        }
        /**
         * Setup player name, type with parameters
         */

        Plateau plateau=new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }
        IG.miseAJourAffichage();
        int[][] resultat = plateau.calculeChemin(0,0, 1,0);
        if (resultat == null) {
            System.out.println("Aucun chemin possible.");
        } else {
            System.out.println(Arrays.deepToString(resultat));
            for (int[] ints : resultat) {
                IG.placerBilleSurPlateau(ints[0], ints[1], 1, 1, 1);
            }
        }
        IG.miseAJourAffichage();
    }
}
