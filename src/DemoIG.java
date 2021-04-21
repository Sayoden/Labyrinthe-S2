import grafix.interfaceGraphique.IG;

public class DemoIG {

    public static void main(String[] args) {
        // Une petite dÃ©monstration conernant l'interface graphique


        // Saisie des diffÃ©rents paramÃ¨tres
        Object parametres[];
        parametres=IG.saisirParametres(); // On ouvre la fenÃªtre de paramÃ©trage pour la saisie

        // CrÃ©ation de la fenÃªtre de jeu et affichage de la fenÃªtre
        int nbJoueurs=((Integer)parametres[0]).intValue(); // RÃ©cupÃ©ration du nombre de joueurs
        IG.creerFenetreJeu("DÃ©mo Librairie IG version 1.9",nbJoueurs); // On crÃ©e la fenÃªtre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fenÃªtre de jeu

        IG.jouerUnSon(2); // On joue le son numÃ©ro 2
        IG.pause(300); // On attend 300 ms
        IG.jouerUnSon(2); // On joue de nouveau le son numÃ©ro 2

        // Affichage d'un message
        String message[]={
                "",
                "DÃ©mo Librairie Graphique 1.9 ...	",
                "Cliquer pour continuer ...",
                "C'est toi le master ?",
                ""
        };
        IG.afficherMessage(message); // On change de message de la fenÃªtre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenÃªtre de jeu
        IG.attendreClic();  // On attend un clic de souris

        // Changement d'une piÃ¨ce sur le plateau
        IG.changerPiecePlateau(3,4,2,1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Changement des caractÃ©ristiques du premier joueur avec les paramÃ¨tres saisis
        int numImageJoueur0=((Integer)parametres[3]).intValue();
        String nomJoueur0=(String)parametres[1];
        String categorieJoueur0=(String)parametres[2];
        IG.attendreClic();
        IG.changerNomJoueur(0, nomJoueur0+" ("+categorieJoueur0+")");
        IG.changerImageJoueur(0,numImageJoueur0);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Changement d'objets au premier joueur et second joueur

        for (int i=0;i<4;i++){
            IG.changerObjetJoueur(0,i,i);
            IG.changerObjetJoueur(1,i+7,i);
        }
        for (int i=4;i<6;i++){
            IG.changerObjetJoueurAvecTransparence(0,i,i);
            IG.changerObjetJoueurAvecTransparence(1,i+7,i);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place des objets sur le plateau
        int numObjet=0;
        for (int i=0;i<7;i++)
            for (int j=0;j<7;j++)
                IG.placerObjetPlateau((numObjet++)%18,i,j);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Mettre en sÃ©lection l'objet 2
        IG.changerObjetSelectionne(2);

        // Place les joueurs sur le plateau

        for (int i=0;i<nbJoueurs;i++)
            IG.placerJoueurSurPlateau(i,1+i,1+i);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place tous les joueurs sur la mÃªme piÃ¨ce

        for (int i=0;i<nbJoueurs;i++)
            IG.placerJoueurSurPlateau(i,1,1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // Place des billes sur les lignes 2 et 3
        for (int i=0;i<7;i++)
            for (int j=0;j<3;j++){
                IG.placerBilleSurPlateau(2,i,1,j,j);
                IG.placerBilleSurPlateau(3,i,1,j,j+1);
            }
        IG.miseAJourAffichage();
        IG.attendreClic();

        // SÃ©lectionne le joueur 1
        IG.changerJoueurSelectionne(1);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // SÃ©lectionne la flÃªche 3
        IG.selectionnerFleche(3);
        IG.miseAJourAffichage();
        IG.attendreClic();

        // SÃ©lectionne la piÃ¨ce sur le plateau Ã  la ligne 2 colonne 3.
        IG.selectionnerPiecePlateau(2,3);
        IG.miseAJourAffichage();
        IG.attendreClic();



        // Change la piÃ¨ce hors du plateau
        IG.changerPieceHorsPlateau(2,2);
        IG.miseAJourAffichage();
        IG.attendreClic();
        message[0]="";
        message[1]="Rotationner la piÃ¨ce qui se ";
        message[2]="trouve hors du plateau ... ";
        message[3]="Puis sÃ©lectionner une flÃªche ...";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        // Attente d'un clic sur une entrÃ©e
        int entree=IG.attendreChoixEntree();

        System.out.println("EntrÃ©e : "+entree);
        System.out.println("Modele : "+IG.recupererModelePieceHorsPlateau());
        System.out.println("Orienation : "+IG.recupererOrientationPieceHorsPlateau());

        // Attente d'un clic sur une piÃ¨ce
        message[0]="";
        message[1]="SÃ©lectionner une piÃ¨ce sur ";
        message[2]="le plateau ... ";
        message[3]="";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        int choixPiece[]=IG.attendreChoixPiece();
        System.out.println("PiÃ¨ce sÃ©lectionnÃ©e : ("+choixPiece[0]+","+choixPiece[1]+")");


        message[0]="";
        message[1]="C'est terminÃ© !";
        message[2]="Cliquer pour quitter ...";
        message[3]="";
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
        System.exit(0);


    }

}