package tests;

import composants.Objet;
import composants.Piece;
import grafix.interfaceGraphique.IG;

public class TestObjet {

    public static void main(String[] args) {

        String[] message = {
                "",
                "Cliquez pour quitter ...",
                ""
        };

        //Saisie des différents paramètres
        Object[] parametres;
        parametres = IG.saisirParametres();

        int nbJoueurs = (Integer) parametres[0];
        IG.creerFenetreJeu("Etape 3 - Projet S2", nbJoueurs);
        IG.rendreVisibleFenetreJeu();

        IG.jouerUnSon(2);
        IG.pause(300);
        IG.jouerUnSon(2);

        Objet[] objects = Objet.nouveauxObjets();

        for (Objet objet : objects) {
            System.out.println(objet.getPosLignePlateau() + " " + objet.getPosColonnePlateau());
            IG.placerObjetPlateau(objet.getNumeroObjet(), objet.getPosLignePlateau(), objet.getPosColonnePlateau());
        }

        IG.miseAJourAffichage();
    }
}
