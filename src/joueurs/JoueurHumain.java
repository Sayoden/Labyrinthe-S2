package joueurs;

import composants.Objet;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur humain.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */
public class JoueurHumain extends Joueur {

    /**
     * Constructeur permettant de crÃ©er un joueur Ã  partir de son nom, son type et
     * le numÃ©ro de l'image le reprÃ©sentant.
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©s le joueur.

     */
    public JoueurHumain(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "Humain";
    }


    /**
     * Saisies de l'orientation de la piÃ¨ce hors plateau et de l'entrÃ©e dans le plateau rÃ©alisÃ©es Ã  l'aide de l'interface graphique.
     */
    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
        int resultat[]=new int[2];
        resultat[1]=IG.attendreChoixEntree();
        resultat[0]=IG.recupererOrientationPieceHorsPlateau();
        return resultat;
    }


    /**
     * Saisie de la case d'arrivÃ©e rÃ©alisÃ©e Ã  l'aide de l'interface graphique.
     */
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        return IG.attendreChoixPiece();
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurHumain(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }



}