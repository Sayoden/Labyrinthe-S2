package tests;

import joueurs.Joueur;
import grafix.interfaceGraphique.IG;
import composants.Piece;
import composants.Plateau;
import partie.ElementsPartie;

public class TestJoueur {
    public static void main(String[] args){

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
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);

        IG.placerJoueurSurPlateau(0,0,0);
        IG.placerJoueurSurPlateau(1,0,6);
        IG.placerJoueurSurPlateau(2,6,6);
        IG.miseAJourAffichage();
        IG.attendreClic();

        IG.attendreChoixPiece();

    }
}
