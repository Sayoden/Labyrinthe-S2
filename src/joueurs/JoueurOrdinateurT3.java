package joueurs;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T3.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT3 extends JoueurOrdinateur {

    /**
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT3(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    /**
     * Pour cette IA, elle va essayer de se libérer des opportunités
     * en priorité
     * Sinon -> l'IA va essayer d'empecher le joueur dans la partie
     * ayant le plus d'objet de continuer son avancé pour essayer
     * de reprendre l'avantage
     *
     * @param elementsPartie
     * @return
     */
    @Override
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie)  {
        // On génère un entier aléatoire pour savoir quelle côté l'IA va choisir
        int[] tableau = new int[]{6, 5, 4, 3, 2, 1, 0};
        int resultat[] = new int[2];
        resultat[0] = IG.recupererOrientationPieceHorsPlateau();

        //On vérifie si le joueur peut se déplacer pour essayer de créer
        // une fenêtre de déplacement pour lui
        List<int[]> chemins = obtenisCheminsACote(1, elementsPartie);
        Joueur joueur = null;
        if (chemins.size() == 0) {
            // On va essayer de créer des opportunités pour l'IA
            // en le faisant bouger$
            joueur = this;
        } else {
            // On va essayer de créer des opportunités pour l'IA
            // en le faisant bouger
            Joueur joueurMaxObjet = elementsPartie.getJoueurs()[0];
            for (Joueur joueur2 : elementsPartie.getJoueurs()) {
                if (joueur2.getNombreObjetsRecuperes() > joueurMaxObjet.getNombreObjetsRecuperes()) {
                    joueurMaxObjet = joueur2;
                }
            }
            joueur = joueurMaxObjet;
        }

        List<Integer> listPosFleche = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            if (i == 0) {
                listPosFleche.add(joueur.getPosColonne());
            } else if (i == 2) {
                listPosFleche.add(i * 7 + tableau[joueur.getPosColonne()]);
            } else if (i == 1) {
                listPosFleche.add(joueur.getPosLigne());
            } else {
                listPosFleche.add(i * 7 * tableau[joueur.getPosLigne()]);
            }
        }
        Collections.shuffle(listPosFleche);
        resultat[1] = listPosFleche.get(0);
        return resultat;
    }

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
    public String getCategorie() {
        return "OrdiType3";
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT3(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }

}