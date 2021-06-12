package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;


/**
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T0.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 */

public class JoueurOrdinateurT0 extends JoueurOrdinateur {

    /**
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur             Le numÃ©ro du joueur.
     * @param nomJoueur             Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau       La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau     La colonne du plateau sur laquelle est positionnÃ©e le joueur.
     */
    public JoueurOrdinateurT0(int numJoueur, String nomJoueur, int numeroImagePersonnage, int posLignePlateau, int posColonnePlateau) {
        super(numJoueur, nomJoueur, numeroImagePersonnage, posLignePlateau, posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType0";
    }

    /**
     * Cette IA choisira une insertion piece libre aléatoirement
     * comme pourrait le faire un humain ne sachant pas quoi faire
     *
     * @param elementsPartie
     * @return
     */
    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
        int resultat[] = new int[2];
        resultat[1] = Utils.genererEntier(27);
        resultat[0] = IG.recupererOrientationPieceHorsPlateau();
        return resultat;
    }

    /**
     * 1er test -> Vérifie si l'ordinateur peut aller sur la case
     * ou il doit récuperer son objet.
     *
     * @param elementsPartie
     * @return
     */
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        Objet prochainObjet = getProchainObjet();

        int[][] cheminPossible = elementsPartie.getPlateau().calculeChemin(getPosLigne(), getPosColonne(), prochainObjet.getPosLignePlateau(), prochainObjet.getPosColonnePlateau());
        if (cheminPossible != null) {
            // On inclu une chance que l'IA n'arrive pas a trouver le chemin possible (5%)
            if (Utils.genererEntier(100) > 5) {
                return cheminPossible[cheminPossible.length - 1];
            }
        }
        return null;
    }

    @Override
    public Joueur copy(Objet objets[]) {
        Joueur nouveauJoueur = new JoueurOrdinateurT0(getNumJoueur(), getNomJoueur(), getNumeroImagePersonnage(), getPosLigne(), getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes() != this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }


}