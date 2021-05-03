package composants;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Classe contenant quelques fonctions utilitaires.
 */
public class Utils {

    private static Random generateur = new Random((new Date().getTime()));

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de gÃ©nÃ©rer alÃ©atoirement un nombre entier.
     *
     * @param max Le nombre entier maximal pouvant Ãªtre retournÃ©.
     * @return Un nombre entier compris entre 0 et max (inclus).
     */
    public static int genererEntier(int max) {
        return generateur.nextInt(max);
    }

    /**
     * A Faire (23/04/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de gÃ©nÃ©rer un tableau d'entiers dont la longueur longTab est donnÃ©e en paramÃ¨tre.
     * Le tableau gÃ©nÃ©rÃ© doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
     * dans le tableau doit Ãªtre alÃ©atoire.
     *
     * @param longTab La longueur du tableau.
     * @return Un tableau contenant les entiers 0,...,longTab-1 placÃ©s alÃ©atoirement dans le tableau.
     */
    public static int[] genereTabIntAleatoirement(int longTab) {
        int tab[] = new int[longTab];

        ArrayList<Integer> exclude = new ArrayList<>();
        for (int i = 0; i < longTab; i++) {
            int x = genererEntier(longTab);
            while (exclude.contains(x)) {
                x = genererEntier(longTab);
            }
            exclude.add(x);
            tab[i] = x;
        }

        return tab;
    }

    /**
     * Programme testant la mÃ©thode genereTabIntAleatoirement.
     *
     * @param args arguments du programme
     */
    public static void main(String[] args) {
        // Un petit test ...
        int tab[] = genereTabIntAleatoirement(18);
        for (int i = 0; i < tab.length; i++)
            System.out.print(tab[i] + " ");

    }

}