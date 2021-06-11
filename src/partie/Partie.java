package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

import java.util.Arrays;

public class Partie {
    static double version = 0.0;


    private ElementsPartie elementsPartie; // Les Ã©lÃ©ments de la partie.

    /**
     * A Faire (02/06/2021 LG Finalisée)
     * <p>
     * Constructeur permettant de crÃ©er et d'initialiser une nouvelle partie.
     */
    public Partie() {
        // Initialisation de la partie
        parametrerEtInitialiser();

        IG.placerJoueurSurPlateau(0, 0, 0);
        IG.placerJoueurSurPlateau(1, 0, 6);
        if (this.elementsPartie.getNombreJoueurs() == 3) {
            IG.placerJoueurSurPlateau(2, 6, 6);
        }
        for (Joueur joueur : this.elementsPartie.getJoueurs()) {
            IG.changerImageJoueur(joueur.getNumJoueur(), joueur.getNumeroImagePersonnage());
            IG.changerNomJoueur(joueur.getNumJoueur(), joueur.getNomJoueur() + " (" + joueur.getCategorie() + ")");
            int i = 0;
            for (Objet objet : joueur.getObjetsJoueur()) {
                IG.changerObjetJoueur(joueur.getNumJoueur(), objet.getNumeroObjet(), i);
                i++;
            }
        }
        Plateau plateau = this.elementsPartie.getPlateau();
        Piece pieceHorsPlateau = this.elementsPartie.getPieceLibre();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();

        System.out.println(elementsPartie.toString());

        IG.rendreVisibleFenetreJeu();
    }

    /**
     * MÃ©thode permettant de paramÃ¨trer et initialiser les Ã©lÃ©ments de la partie.
     */
    private void parametrerEtInitialiser() {
        // Saisie des diffÃ©rents paramÃ¨tres
        Object parametresJeu[];
        parametresJeu = IG.saisirParametres();
        int nombreJoueurs = ((Integer) parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- Version " + version, nombreJoueurs);
        // CrÃ©ation des joueurs
        Joueur joueurs[] = Joueur.nouveauxJoueurs(parametresJeu);
        // CrÃ©ation des Ã©lÃ©ments de la partie
        elementsPartie = new ElementsPartie(joueurs);
    }


    /**
     * A Faire (02/06/2021 LG Finalisée)
     * <p>
     * MÃ©thode permettant de lancer une partie.
     */
    public void lancer() {
        String[] messageOrientation = {
                "",
                "%joueur% : Vous pouvez orienter",
                "la pièce hors du plateau",
                "Ensuite selectionnez une flèche"
        };

        String[] messageChoixChemin = {
                "",
                "%joueur% : Choisissez un",
                "chemin"
        };
        int intJoueurs = 0;

        while (true) {
            //On récupère le joueur
            Joueur joueur = this.elementsPartie.getJoueurs()[intJoueurs];
            IG.changerObjetSelectionne(joueur.getProchainObjet().getNumeroObjet());

            //On modifie %joueur% par le nom du joueur actuel
            String[] modifMessage = messageOrientation.clone();
            modifMessage[1] = modifMessage[1].replace("%joueur%", joueur.getNomJoueur());

            String[] modifMessage2 = messageChoixChemin.clone();
            modifMessage2[1] = modifMessage2[1].replace("%joueur%", joueur.getNomJoueur());

            IG.afficherMessage(modifMessage);
            IG.changerJoueurSelectionne(intJoueurs);
            IG.miseAJourAffichage();
            int[] choixOrientation = joueur.choisirOrientationEntree(elementsPartie);
            elementsPartie.insertionPieceLibre(choixOrientation[1]);
            IG.afficherMessage(modifMessage2);
            IG.miseAJourAffichage();

            //On oblige le joueur a choisir un chemin possible
            int[] caseTmp;
            while (true) {
                caseTmp = joueur.choisirCaseArrivee(elementsPartie);
                if (caseTmp == null) {
                    caseTmp = new int[]{joueur.getPosLigne(), joueur.getPosColonne()};
                }
                int[][] chemin = elementsPartie.getPlateau().calculeChemin(joueur.getPosLigne(), joueur.getPosColonne(), caseTmp[0], caseTmp[1]);
                if (chemin != null) {
                    for (int[] ints : chemin) {
                        IG.miseAJourAffichage();
                        IG.placerJoueurSurPlateau(joueur.getNumJoueur(), ints[0], ints[1]);
                        joueur.setPosition(ints[0], ints[1]);
                        IG.pause(150);
                    }
                    break;
                }
            }

            Objet objet = elementsPartie.objetIci(caseTmp[0], caseTmp[1]);
            if (objet != null && joueur.getProchainObjet() != null) {
                if (joueur.getProchainObjet().equals(objet)) {
                    if (objet.surPlateau()) {
                        IG.changerObjetJoueurAvecTransparence(joueur.getNumJoueur(), joueur.getProchainObjet().getNumeroObjet(), joueur.getNombreObjetsRecuperes());
                        objet.enleveDuPlateau();
                        joueur.recupererObjet();
                    }
                }
            }
            IG.miseAJourAffichage();

            if (joueur.getNombreObjetsRecuperes() == (18 / elementsPartie.getNombreJoueurs())) {
                IG.afficherGagnant(joueur.getNumJoueur());
                String[] messageGagnant = {
                        "",
                        "%joueur% : Félicitation !",
                        "Vous avez gagné ! :o"
                };
                modifMessage2 = messageGagnant.clone();
                modifMessage2[1] = modifMessage2[1].replace("%joueur%", joueur.getNomJoueur());
                IG.afficherMessage(modifMessage2);
                IG.miseAJourAffichage();
                break;
            }
            intJoueurs = (intJoueurs + 1) % elementsPartie.getNombreJoueurs();
        }
    }

    /**
     * Programme principal permettant de lancer le jeu.
     *
     * @param args Les arguments du programmes.
     */
    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.lancer();
    }

}