package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

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