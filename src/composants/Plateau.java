package composants;

import grafix.interfaceGraphique.IG;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Cette classe permet de gÃ©rer un plateau de jeu constituÃ© d'une grille de piÃ¨ces (grille de 7 lignes sur 7 colonnes).
 */
public class Plateau {

    private Piece plateau[][]; // La grille des piÃ¨ces.

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * Constructeur permettant de construire un plateau vide (sans piÃ¨ces) et d'une taille de 7 lignes sur 7 colonnes.
     */
    public Plateau() {
        this.plateau = new Piece[7][7];
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de placer une piÃ¨ce sur le plateau.
     *
     * @param piece          La piÃ¨ce Ã  placer.
     * @param lignePlateau   La ligne du plateau sur laquelle sera placÃ©e la piÃ¨ce (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau sur laquelle sera placÃ©e la piÃ¨ce (une entier entre 0 et 6).
     */
    public void positionnePiece(Piece piece, int lignePlateau, int colonnePlateau) {
        this.plateau[lignePlateau][colonnePlateau] = piece;
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode retournant une piÃ¨ce se trouvant sur le plateau Ã  un emplacement spÃ©cifique.
     *
     * @param lignePlateau   La ligne du plateau  (un entier entre 0 et 6).
     * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
     * @return La piÃ¨ce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de piÃ¨ce, null est retournÃ©.
     */
    public Piece getPiece(int lignePlateau, int colonnePlateau) {
        return this.plateau[lignePlateau][colonnePlateau];
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de placer alÃ©atoirment 49 piÃ¨ces du jeu sur le plateau.
     * L'orientation des piÃ¨ces est alÃ©atoire. Les piÃ¨ces utilisÃ©es doivent Ãªtre des nouvelles piÃ¨ces gÃ©nÃ©rÃ©es Ã  partir de la mÃ©thode Piece.nouvellesPieces.
     * Parmi les 50 piÃ¨ces du jeu, la piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau est retournÃ©e par la mÃ©thode.
     *
     * @return La seule piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau
     */
    public Piece placerPiecesAleatoierment() {
        Piece[] pieces = Piece.nouvellesPieces();
        int[] randomTab = Utils.genereTabIntAleatoirement(49);

        for (int i = 0; i < randomTab.length; i++) {
            int ligne = i / 7;
            int colonne = i % 7;
            this.plateau[ligne][colonne] = pieces[i];
        }

        return pieces[49];
    }

    /**
     * MÃ©thode utilitaire permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
     *
     * @param posLigCase1 Un entier quelconque.
     * @param posColCase1 Un entier quelconque.
     * @param posLigCase2 Un entier quelconque.
     * @param posColCase2 Un entier quelconque.
     * @return true si les les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
     */
    private static boolean casesAdjacentes(int posLigCase1, int posColCase1, int posLigCase2, int posColCase2) {
        if ((posLigCase1 < 0) || (posLigCase2 < 0) || (posLigCase1 > 6) || (posLigCase2 > 6)) return false;
        if ((posColCase1 < 0) || (posColCase2 < 0) || (posColCase1 > 6) || (posColCase2 > 6)) return false;
        int distLigne = posLigCase1 - posLigCase2;
        if (distLigne < 0) distLigne = -distLigne;
        int distColonne = posColCase1 - posColCase2;
        if (distColonne < 0) distColonne = -distColonne;
        if ((distLigne > 1) || (distColonne > 1) || ((distColonne + distLigne) != 1))
            return false;
        return true;
    }

    /**
     * A Faire (08/05/21 LG Finalisée)
     * <p>
     * MÃ©thode permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes
     * de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau.
     *
     * @param posLigCase1 Un entier quelconque.
     * @param posColCase1 Un entier quelconque.
     * @param posLigCase2 Un entier quelconque.
     * @param posColCase2 Un entier quelconque.
     * @return true si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau, false sinon.
     */
    private boolean passageEntreCases(int posLigCase1, int posColCase1, int posLigCase2, int posColCase2) {
        if (casesAdjacentes(posLigCase1, posColCase1, posLigCase2, posColCase2)) {

            Piece piece1 = getPiece(posLigCase1, posColCase1);
            Piece piece2 = getPiece(posLigCase2, posColCase2);

            if (posLigCase1 == posLigCase2) {
                if (posColCase1 < posColCase2) {
                    return piece1.getPointEntree(1) && piece2.getPointEntree(3);
                } else {
                    return piece1.getPointEntree(3) && piece2.getPointEntree(1);
                }
            } else {
                if (posLigCase1 < posLigCase2) {
                    return piece1.getPointEntree(2) && piece2.getPointEntree(0);
                } else {
                    return piece1.getPointEntree(0) && piece2.getPointEntree(2);
                }
            }
        }
        return false;
    }

    /**
     * A Faire (07/05/2021 LG,CD,LI Finalisée)
     * <p>
     * MÃ©thode permettant de retourner un Ã©ventuel chemin entre deux cases du plateau compte tenu des piÃ¨ces posÃ©es sur le plateau.
     * Dans le cas oÃ¹ il n'y a pas de chemin entre les deux cases, la valeur null est retournÃ©e.
     * Dans le cas oÃ¹ il existe un chemin, un chemin possible est retournÃ© sous forme d'un tableau d'entiers Ã  deux dimensions.
     * La premiÃ¨re dimension correspond aux cases du plateau Ã  emprunter pour aller de la case de dÃ©part Ã  la case d'arrivÃ©e.
     * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond Ã  la ligne de la case et
     * le second entier qui correspond Ã  la colonne de la case. La premiÃ¨re case d'un chemin retournÃ© correspond toujours
     * Ã  la case (posLigCaseDep,posColCaseDep) et la derniÃ¨re case correspond toujours Ã  la case (posLigCaseArr,posColCaseArr).
     *
     * @param posLigCaseDep La ligne de la case de dÃ©part (un entier compris entre 0 et 6).
     * @param posColCaseDep La colonne de la case de dÃ©part (un entier compris entre 0 et 6).
     * @param posLigCaseArr La ligne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
     * @param posColCaseArr La colonne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
     * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
     */
    public int[][] calculeChemin(int posLigCaseDep, int posColCaseDep, int posLigCaseArr, int posColCaseArr) {
        if (posLigCaseDep == posLigCaseArr && posColCaseDep == posColCaseArr) {
            return new int[][]{new int[]{posLigCaseArr, posColCaseArr}};
        }
        List<int[]> listExecution = new ArrayList<>();
        List<int[]> exclusion = new ArrayList<>();
        HashMap<List<Integer>, int[][]> cheminsMap = new HashMap<>();

        listExecution.add(new int[]{posLigCaseDep, posColCaseDep});
        boolean cheminPossible = true;

        while (cheminPossible) {
            for (int[] ints : new ArrayList<>(listExecution)) {
                exclusion.add(new int[]{ints[0], ints[1]});
                ArrayList<int[]> possibilites = possibiliteCasesAdjacentes(ints[0], ints[1], exclusion);
                possibilites.forEach(possible -> {
                    if (exclusion.stream().noneMatch(x -> x[0] == possible[0] && x[1] == possible[1])) {
                        exclusion.add(possible);
                    }
                });
                listExecution.addAll(possibilites);
                listExecution.removeIf(remove -> remove[0] == ints[0] && remove[1] == ints[1]);

                if (cheminsMap.size() == 0) {
                    cheminsMap.put(Arrays.asList(ints[0], ints[1]), new int[][]{new int[]{ints[0], ints[1]}});
                } else {
                    ArrayList<int[]> cheminsAvant = possibiliteCasesAdjacentes(ints[0], ints[1], null);
                    ArrayList<int[][]> cheminsTemp = new ArrayList<>();
                    cheminsAvant.forEach(chemin -> {
                        if (cheminsMap.containsKey(Arrays.asList(chemin[0], chemin[1]))) {
                            cheminsTemp.add(new int[][]{chemin});
                            int[][] test = cheminsMap.get(Arrays.asList(cheminsTemp.get(0)[0][0], cheminsTemp.get(0)[0][1]));
                            int[][] test2 = new int[test.length + 1][2];
                            for (int i = 0; i < test2.length; i++) {
                                if (i >= test.length) {
                                    test2[i][0] = ints[0];
                                    test2[i][1] = ints[1];
                                } else {
                                    test2[i][0] = test[i][0];
                                    test2[i][1] = test[i][1];
                                }
                            }
                            cheminsMap.put(Arrays.asList(ints[0], ints[1]), test2);
                        }
                    });
                    if (ints[0] == posLigCaseArr && ints[1] == posColCaseArr) {
                        int[][] cheminFinal = new int[0][];
                        for (Map.Entry<List<Integer>, int[][]> entry : cheminsMap.entrySet()) {
                            if (entry.getValue()[entry.getValue().length - 1][0] == posLigCaseArr && entry.getValue()[entry.getValue().length - 1][1] == posColCaseArr) {
                                if (cheminFinal.length <= entry.getValue().length) {
                                    cheminFinal = entry.getValue();
                                }
                            }
                        }
                        return cheminFinal;
                    }
                }
            }
            if (listExecution.size() == 0) {
                cheminPossible = false;
            }
        }
        return null;
    }

    /**
     * A Faire (14/05/2021 LG En cours)
     * <p>
     * Méthode permettant de retourner les différents chemins possibles sous forme de tableau de coordonnées vers les cases
     * adjacentes en fonction de la piece donnée en argument.
     *
     * @param posLigCase La ligne de la case (un entier compris entre 0 et 6).
     * @param posColCase La colonne de la case (un entier compris entre 0 et 6).
     * @return le tableau des coordonnées des possibilités de déplacement vers les cases adjacentes
     */
    public ArrayList<int[]> possibiliteCasesAdjacentes(int posLigCase, int posColCase, List<int[]> exclude) {
        ArrayList<int[]> possibilites = new ArrayList<>();

        int[] posNow;
        List<int[]> passage = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            switch (i) {
                case 0 -> {
                    posNow = new int[]{posLigCase - 1, posColCase};
                    if (passageEntreCases(posLigCase, posColCase, posNow[0], posNow[1])) {
                        passage.add(posNow);
                    }
                }
                case 1 -> {
                    posNow = new int[]{posLigCase, posColCase + 1};
                    if (passageEntreCases(posLigCase, posColCase, posNow[0], posNow[1])) {
                        passage.add(posNow);
                    }
                }
                case 2 -> {
                    posNow = new int[]{posLigCase + 1, posColCase};
                    if (passageEntreCases(posLigCase, posColCase, posNow[0], posNow[1])) {
                        passage.add(posNow);
                    }
                }
                case 3 -> {
                    posNow = new int[]{posLigCase, posColCase - 1};
                    if (passageEntreCases(posLigCase, posColCase, posNow[0], posNow[1])) {
                        passage.add(posNow);
                    }
                }
            }
        }
        for (int[] ints : passage) {
            if (exclude == null || exclude.size() == 0) {
                possibilites.add(ints);
            } else {
                if (exclude.stream().noneMatch(x -> x[0] == ints[0] && x[1] == ints[1])) {
                    possibilites.add(ints);
                }
            }
        }
        return possibilites;
    }


    /**
     * MÃ©thode permettant de calculer un chemin dÃ©taillÃ© (chemin entre sous-cases) Ã  partir d'un chemin entre cases.
     *
     * @param chemin    Un tableau reprÃ©sentant un chemin de cases.
     * @param numJoueur Le numÃ©ro du joueur pour lequel nous souaitons construire un chemin dÃ©taillÃ©.
     * @return Le chemin dÃ©taillÃ© correspondant au chemin de positions de piÃ¨ces donnÃ©es en paramÃ¨tre et pour le numÃ©ro de joueur donnÃ©.
     */
    public int[][] calculeCheminDetaille(int[][] chemin, int numJoueur) {
        if (chemin.length == 1)
            return new int[0][0];
        int[][] cheminDetaille = new int[chemin.length * 5][4];
        int pos = 0;
        int col, lig, colS, ligS;
        for (int i = 0; i < chemin.length - 1; i++) {
            lig = chemin[i][0];
            col = chemin[i][1];
            ligS = chemin[i + 1][0];
            colS = chemin[i + 1][1];
            if (ligS < lig) {
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 0;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = ligS;
                cheminDetaille[pos][1] = colS;
                cheminDetaille[pos][2] = 2;
                cheminDetaille[pos++][3] = 1;
            } else if (ligS > lig) {
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 2;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = ligS;
                cheminDetaille[pos][1] = colS;
                cheminDetaille[pos][2] = 0;
                cheminDetaille[pos++][3] = 1;
            } else if (colS < col) {
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 0;
                cheminDetaille[pos][0] = ligS;
                cheminDetaille[pos][1] = colS;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 2;
            } else if (colS > col) {
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 1;
                cheminDetaille[pos][0] = lig;
                cheminDetaille[pos][1] = col;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 2;
                cheminDetaille[pos][0] = ligS;
                cheminDetaille[pos][1] = colS;
                cheminDetaille[pos][2] = 1;
                cheminDetaille[pos++][3] = 0;
            }
        }
        cheminDetaille[pos][0] = chemin[chemin.length - 1][0];
        cheminDetaille[pos][1] = chemin[chemin.length - 1][1];
        cheminDetaille[pos][2] = 1;
        cheminDetaille[pos++][3] = 1;

        int debut = 0;
        if ((numJoueur == 0) && ((cheminDetaille[pos - 2][2] == 0) || (cheminDetaille[pos - 2][3] == 2))) pos--;
        if ((numJoueur == 1) && ((cheminDetaille[pos - 2][2] == 2) || (cheminDetaille[pos - 2][3] == 2))) pos--;
        if ((numJoueur == 2) && ((cheminDetaille[pos - 2][2] == 2) || (cheminDetaille[pos - 2][3] == 0))) pos--;
        if ((numJoueur == 0) && ((cheminDetaille[1][2] == 0) || (cheminDetaille[0][3] == 2))) debut++;
        if ((numJoueur == 1) && ((cheminDetaille[1][2] == 2) || (cheminDetaille[0][3] == 2))) debut++;
        if ((numJoueur == 2) && ((cheminDetaille[1][2] == 2) || (cheminDetaille[0][3] == 0))) debut++;

        int[][] resultat = new int[pos - debut][4];
        for (int i = debut; i < pos; i++)
            for (int j = 0; j < 4; j++)
                resultat[i - debut][j] = cheminDetaille[i][j];
        return resultat;
    }

    /**
     * MÃ©thode retournant une copie du plateau avec des copies de ses piÃ¨ces.
     *
     * @return Une copie du plateau avec une copie de toutes ses piÃ¨ces.
     */
    public Plateau copy() throws CloneNotSupportedException {
        Plateau plateau = new Plateau();
        for (int ligne = 0; ligne < 7; ligne++)
            for (int colonne = 0; colonne < 7; colonne++)
                plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
        return plateau;
    }

}
