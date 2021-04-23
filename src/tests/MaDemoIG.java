package tests;

import grafix.interfaceGraphique.IG;

public class MaDemoIG {

    /**
     * Première prise en main de la librairie
     * @param args
     */
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
        IG.changerPieceHorsPlateau(1, 0);
        IG.placerJoueurSurPlateau(0, 3, 0);
        IG.placerJoueurSurPlateau(1, 3, 6);
        /**
         * Setup player name, type with parameters
         */

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i,j, 2, 0);
            }
        }

        // Place des objets sur le plateau
        int numObjet = 17;
        int ligne = 0;
        int colonne = 0;
        for (int i = 0; i <= numObjet; i++) {
            if (colonne == 7) {
                colonne = 0;
                ligne++;
            }
            IG.placerObjetPlateau(i % 18, ligne, colonne);
            colonne++;
        }

        String[] message = {
                "",
                "Bonjour !",
                "Cliquer pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
    }
}
