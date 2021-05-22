package joueurs;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur gÃ©nÃ©rique et basique.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */
public class JoueurOrdinateur extends Joueur{
    /**
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur (un entier compris entre 0 et 2).
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.
     */
    public JoueurOrdinateur(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "Ordinateur";
    }

    /**
     * Saisies de l'orientation de la piÃ¨ce hors plateau et de l'entrÃ©e dans le plateau : on retourne toujours 3 pour l'entrÃ©e du plateau (la flÃªche du haut)
     * et l'orientation courante de la piÃ¨ce hors plateau de l'interface graphique.
     */
    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
        int resultat[]=new int[2];
        resultat[1]=3;
        resultat[0]=IG.recupererOrientationPieceHorsPlateau();
        return resultat;
    }


    /**
     * Saisie de la case d'arrivÃ©e rÃ©alisÃ©e : retourne toujours la position du joueur.
     */
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        int resultat[]=new int[2];
        resultat[0]=this.getPosLigne();
        resultat[1]=this.getPosColonne();
        return resultat;
    }


}
