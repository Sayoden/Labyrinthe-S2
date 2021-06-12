package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T1.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT1 extends JoueurOrdinateur {

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
    public JoueurOrdinateurT1(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType1";
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
        int resultat[]=new int[2];
        resultat[1]= Utils.genererEntier(27);
        resultat[0]= IG.recupererOrientationPieceHorsPlateau();
        return resultat;
    }

    /**
     * En plus de trouver le chemin ou son prochain objet est situé
     *
     * Il cherchera a avancer dans un perimetre limité
     *
     * @param elementsPartie
     * @return
     */
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
        Objet prochainObjet = getProchainObjet();

        int[][] cheminPossible = elementsPartie.getPlateau().calculeChemin(getPosLigne(), getPosColonne(), prochainObjet.getPosLignePlateau(), prochainObjet.getPosColonnePlateau());
        if (cheminPossible != null) {
            // On inclu une chance que l'IA n'arrive pas a trouver le chemin possible (3%)
            if (Utils.genererEntier(100) > 3) {
                return cheminPossible[cheminPossible.length - 1];
            }
        }  else {
            List<int[]> chemins = obtenisCheminsACote(3, elementsPartie);
            if (chemins.size() > 0) {
                Collections.shuffle(chemins);
                return chemins.get(0);
            }
        }
        return null;
    }

    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT1(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }

}