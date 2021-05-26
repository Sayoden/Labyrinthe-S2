package joueurs;
import composants.Objet;
import composants.Plateau;
import composants.Utils;
import partie.ElementsPartie;
/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T0.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */
public class JoueurOrdinateurT0 extends Joueur{
    /**
     *
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT0(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType0";
    }

    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
        return new int[0];
    }

    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        return new int[0];
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT0(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }

}
