package tests;

import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import partie.ElementsPartie;

import java.util.Arrays;

public class TestJoueur {

    public static void main(String[] args) {

        //Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres();

        int nbJoueurs = (Integer) parametres[0];
        IG.creerFenetreJeu("Etape 4 - Projet S2", nbJoueurs);
        IG.rendreVisibleFenetreJeu();

        IG.jouerUnSon(2);
        IG.pause(300);
        IG.jouerUnSon(2);

        String[] message = {
                "",
                "Cliquez pour commencer ...",
                ""
        };

        /**
         * Setup player name, type with parameters
         */
        int counter = 1;
        for (int i = 0; i < nbJoueurs; i++) {
            String nomJoueur = (String) parametres[counter];
            counter++;
            String categorieJoueur = (String) parametres[counter];
            counter++;
            int numImageJoueur = (Integer) parametres[counter];
            counter++;

            IG.changerNomJoueur(i, nomJoueur + " (" + categorieJoueur + ")");
            IG.changerImageJoueur(i, numImageJoueur);
        }

        IG.placerJoueurSurPlateau(0, 0, 0);
        IG.placerJoueurSurPlateau(1, 0, 6);
        if (nbJoueurs == 3) {
            IG.placerJoueurSurPlateau(2, 6, 6);
        }
        /**
         * Setup player name, type with parameters
         */

        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);

        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        ElementsPartie elementsPartie = new ElementsPartie(joueurs);
        Plateau plateau = elementsPartie.getPlateau();
        for (Joueur joueur : joueurs) {
            String[] message1 = {
                    "",
                    "Au tour de " + joueur.getNomJoueur(),
                    "Sélectionner une case ...",
                    ""
            };
            IG.afficherMessage(message1);
            IG.miseAJourAffichage();
            while (true) {
                int[] caseTmp = joueur.choisirCaseArrivee(elementsPartie);
                int[][] chemin = plateau.calculeChemin(joueur.getPosLigne(), joueur.getPosColonne(), caseTmp[0], caseTmp[1]);
                if (chemin != null) {
                    for (int[] ints : chemin) {
                        IG.miseAJourAffichage();
                        IG.pause(500);
                        IG.placerBilleSurPlateau(ints[0], ints[1], 1, 1, joueur.getNumJoueur());
                        IG.placerJoueurSurPlateau(joueur.getNumJoueur(), ints[0], ints[1]);
                    }
                    break;
                }
            }
        }
        IG.miseAJourAffichage();
    }
}