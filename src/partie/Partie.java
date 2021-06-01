package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;

public class Partie {
    static double version=0.0;


    private ElementsPartie elementsPartie; // Les Ã©lÃ©ments de la partie.

    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * Constructeur permettant de crÃ©er et d'initialiser une nouvelle partie.
     */
    public Partie(){
        // Initialisation de la partie
        parametrerEtInitialiser();

        // On affiche l'ensemble des Ã©lÃ©ments

        // A ComplÃ©ter

        IG.rendreVisibleFenetreJeu();
    }

    /**
     * MÃ©thode permettant de paramÃ¨trer et initialiser les Ã©lÃ©ments de la partie.
     */
    private void parametrerEtInitialiser(){
        // Saisie des diffÃ©rents paramÃ¨tres
        Object parametresJeu[];
        parametresJeu=IG.saisirParametres();
        int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
        IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
        // CrÃ©ation des joueurs
        Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
        // CrÃ©ation des Ã©lÃ©ments de la partie
        elementsPartie=new ElementsPartie(joueurs);
    }


    /**
     *
     * A Faire (Quand Qui Statut)
     *
     * MÃ©thode permettant de lancer une partie.
     */
    public void lancer(){
        // A ComplÃ©ter
    }

    /**
     *
     * Programme principal permettant de lancer le jeu.
     *
     * @param args Les arguments du programmes.
     */
    public static void main(String[] args) {
        while(true){
            Partie partie=new Partie();
            partie.lancer();
        }
    }

}