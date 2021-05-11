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
        IG.placerJoueurPrecis(0, 3, 0, 1, 0);
        IG.placerJoueurPrecis(1, 3, 6, 1, 2);
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

        /**
         * Rotation des pieces avec les clics
         */
        int rotationCount = 0;
        int sousColonne = 1;
        int colonnePlateau = 0;
        for (int i = 1; i <= 4; i++) {
            //Change messageClic with correct number of clics
            String[] messageClic = {
                    "",
                    "Après le clic " + i,
                    "Cliquer pour continuer ...",
                    ""
            };

            //Apply transparence on objet with clics and remove this from plateau
            IG.changerObjetJoueurAvecTransparence(0, rotationCount, rotationCount);
            IG.enleverObjetPlateau(0, rotationCount);

            //Increment rotationCount
            rotationCount++;

            //If rotationCount == 4 ->
            if (rotationCount == 4) {
                rotationCount = 0;
            }

            //Allow you to change piece of plateau of game with good rotation count
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    IG.changerPiecePlateau(j,k, 2, rotationCount);
                }
            }

            IG.changerPieceHorsPlateau(1,rotationCount);


            IG.placerBilleSurPlateau(3, colonnePlateau, 1, sousColonne - 1, 2);
            IG.placerBilleSurPlateau(3, 6 - colonnePlateau, 1, 2 - (sousColonne - 1), 2);
            if (sousColonne == 3) {
                sousColonne = 0;
                colonnePlateau++;
            }

            IG.placerJoueurPrecis(0, 3, colonnePlateau,1, sousColonne);
            IG.placerJoueurPrecis(1, 3, 6 - colonnePlateau,1, 2 - sousColonne);
            sousColonne++;

            IG.afficherMessage(messageClic);
            IG.miseAJourAffichage();
            IG.attendreClic();
        }

        String[] messageWaitFleche = {
                "",
                "Cliquez sur une flèche ",
                "pour quitter",
                ""
        };
        IG.afficherGagnant(0);
        IG.afficherMessage(messageWaitFleche);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.attendreChoixEntree();

        String[] messageStopProcess = {
                "",
                "Arrêt du programme ",
                "dans 2 secondes ! ",
                ""
        };
        IG.afficherMessage(messageStopProcess);
        IG.miseAJourAffichage();
        IG.pause(2000);
        IG.fermerFenetreJeu();
        System.exit(0);

    }
}
