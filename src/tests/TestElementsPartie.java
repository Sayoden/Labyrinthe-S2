package tests;

import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import partie.ElementsPartie;

public class TestElementsPartie {

    public static void main(String[] args) {
        Object[] parametresJeu;
        parametresJeu= IG.saisirParametres();
        int  nbJoueurs= (Integer) parametresJeu[0];
        IG.creerFenetreJeu("- TestElementsPartie",nbJoueurs);
        IG.rendreVisibleFenetreJeu();
        Joueur[] joueurs =Joueur.nouveauxJoueurs(parametresJeu);
        ElementsPartie elementsPartie=new ElementsPartie(joueurs);
        String[] message = {
                "",
                "Cliquez pour commencer ...",
                ""
        };
        IG.afficherMessage(message);

        IG.miseAJourAffichage();
        IG.attendreClic();

        String[] message2 = {
                "",
                "Choisissez une entrée...",
                "(une des flèches)",
                ""
        };
        IG.afficherMessage(message2);
        IG.miseAJourAffichage();

        for(int i = 0; i < 4; i++) {
            int fleche = IG.attendreChoixEntree();
            System.out.println(fleche);
            elementsPartie.insertionPieceLibre(fleche);
        }

        String[] message3 = {
                "",
                "C'est terminé !",
                "Cliquer pour quitter",
                ""
        };
        IG.afficherMessage(message3);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
        System.exit(0);
    }
}
